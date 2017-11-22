package com.example.projects.ineedhelptesting.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class BluetoothCommService {
    private static final boolean f12D = true;
    public static final int STATE_CONNECTED = 3;
    public static final int STATE_CONNECTING = 2;
    public static final int STATE_LISTEN = 1;
    public static final int STATE_NONE = 0;
    private static final String TAG = "BluetoothService";
    private static BluetoothAdapter mBluetoothAdapter;
    private static ConnectThread mConnectThread;
    private static ConnectedThread mConnectedThread;
    private static Handler mHandler;
    private static int mState;
    private UUID localUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private class ConnectThread extends Thread {
        private final BluetoothDevice mmDevice;
        private final BluetoothSocket mmSocket;

        public ConnectThread(BluetoothDevice device) {
            BluetoothSocket tmp = null;
            this.mmDevice = device;
            try {
                tmp = device.createRfcommSocketToServiceRecord(BluetoothCommService.this.localUUID);
            } catch (IOException e) {
                Log.e(BluetoothCommService.TAG, "create() failed", e);
            }
            this.mmSocket = tmp;
        }

        public void run() {
            BluetoothCommService.mBluetoothAdapter.cancelDiscovery();
            try {
                this.mmSocket.connect();
                Log.i(BluetoothCommService.TAG, "mmSocket.connect()");
                synchronized (this) {
                    BluetoothCommService.mConnectThread = null;
                }
                BluetoothCommService.this.connected(this.mmSocket, this.mmDevice);
            } catch (IOException connectException) {
                Log.w(BluetoothCommService.TAG, "IOException = " + connectException);
                try {
                    this.mmSocket.close();
                } catch (IOException closeException) {
                    Log.e(BluetoothCommService.TAG, " socket during connection failure" + closeException);
                }
                BluetoothCommService.this.connectionFailed();
            }
        }

        public void cancel() {
            try {
                this.mmSocket.close();
            } catch (IOException e) {
                Log.e(BluetoothCommService.TAG, "socket failed", e);
            }
        }
    }

    private static class ConnectedThread extends Thread {
        private static byte[] buffer = new byte[512];
        private static InputStream mmInStream;
        private static OutputStream mmOutStream;
        private static BluetoothSocket mmSocket;
        private int bytes;

        public ConnectedThread(BluetoothSocket socket) {
            Log.d(BluetoothCommService.TAG, "create ConnectedThread");
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Log.e(BluetoothCommService.TAG, "temp sockets not created", e);
            }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            this.bytes = 0;
            while (true) {
                try {
                    this.bytes = mmInStream.read(buffer);
                    if (this.bytes > 0) {
                        BluetoothCommService.mHandler.obtainMessage(2, this.bytes, -1, buffer).sendToTarget();
                        this.bytes = 0;
                    }
                    buffer = new byte[512];
                } catch (IOException e) {
                    Log.e(BluetoothCommService.TAG, "disconnected", e);
                    BluetoothCommService.connectionLost();
                    BluetoothCommService.start();
                    return;
                } catch (IndexOutOfBoundsException e2) {
                    Log.e(BluetoothCommService.TAG, "OutOfbondexception", e2);
                }
            }
        }

        public void write(String sbuffer) {
            try {
                mmOutStream.write(sbuffer.getBytes());
                BluetoothCommService.mHandler.obtainMessage(3, -1, -1, sbuffer.getBytes()).sendToTarget();
            } catch (IOException e) {
                Log.e(BluetoothCommService.TAG, "Exception during write", e);
            }
        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Log.e(BluetoothCommService.TAG, "close() of connect socket failed", e);
            }
        }
    }

    public BluetoothCommService(Context context, Handler handler) {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mState = 0;
        mHandler = handler;
    }

    private static synchronized void setState(int state) {
        synchronized (BluetoothCommService.class) {
            Log.d(TAG, "setState() " + mState + " -> " + state);
            mState = state;
            mHandler.obtainMessage(1, state, -1).sendToTarget();
        }
    }

    public synchronized int getState() {
        return mState;
    }

    public static synchronized void start() {
        synchronized (BluetoothCommService.class) {
            Log.d(TAG, "start");
            if (mConnectThread != null) {
                mConnectThread.cancel();
                mConnectThread = null;
            }
            if (mConnectedThread != null) {
                mConnectedThread.cancel();
                mConnectedThread = null;
            }
            setState(1);
        }
    }

    public synchronized void connect(BluetoothDevice device) {
        Log.d(TAG, "connect to: " + device);
        if (mState == 2 && mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }
        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }
        mConnectThread = new ConnectThread(device);
        mConnectThread.start();
        setState(2);
    }

    public void connected(BluetoothSocket socket, BluetoothDevice device) {
        Log.d(TAG, "connected");
        if (mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }
        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }
        mConnectedThread = new ConnectedThread(socket);
        mConnectedThread.start();
        Message msg = mHandler.obtainMessage(4);
        Bundle bundle = new Bundle();
        bundle.putString("device_name", device.getName());
        msg.setData(bundle);
        mHandler.sendMessage(msg);
        setState(3);
    }

    public synchronized void stop() {
        Log.d(TAG, "stop");
        if (mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }
        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }
        setState(0);
    }

    public void write(String out) {
        synchronized (this) {
            if (mState != 3) {
                return;
            }
            ConnectedThread r = mConnectedThread;
            r.write(out);
        }
    }

    private void connectionFailed() {
        Message msg = mHandler.obtainMessage(5);
        Bundle bundle = new Bundle();
        bundle.putString("toast", "Unable to connect device");
        msg.setData(bundle);
        mHandler.sendMessage(msg);
        start();
    }

    private static void connectionLost() {
        Message msg = mHandler.obtainMessage(5);
        Bundle bundle = new Bundle();
        bundle.putString("toast", "Device connection was lost");
        msg.setData(bundle);
        mHandler.sendMessage(msg);
        start();
    }
}
