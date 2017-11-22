package com.example.projects.ineedhelptesting.bluetooth;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.Set;
import com.example.projects.ineedhelptesting.C0156R;


public class DeviceListDialog implements OnItemClickListener {
    private static final boolean f13D = true;
    public static final int STATE_CONNECTED = 3;
    public static final int STATE_CONNECTING = 2;
    public static final int STATE_LISTEN = 1;
    public static final int STATE_NONE = 0;
    private static final String TAG = "CitiSens";
    private int REQUEST_ENABLE_BT = 1;
    private ArrayAdapter<BluetoothDevice> arrayAdapterNewDev;
    private ArrayAdapter<BluetoothDevice> arrayAdapterPairDev;
    private ArrayAdapter<String> arrayAdapterStringNewDev;
    private ArrayAdapter<String> arrayAdapterStringPairDev;
    private boolean bluetooth_connected = false;
    private Builder builder;
    private BluetoothAdapter mBluetoothAdapter;
    private Button mButtonScan;
    private Context mContext;
    private LayoutInflater mInflater;
    private ProgressBar mProgressBarScan;
    private int mState;
    private AlertDialog optionDialog;

    class C01571 implements OnClickListener {
        C01571() {
        }

        public void onClick(View v) {
            DeviceListDialog.this.mBluetoothAdapter.startDiscovery();
            DeviceListDialog.this.arrayAdapterStringNewDev.clear();
            DeviceListDialog.this.arrayAdapterNewDev.clear();
            DeviceListDialog.this.mProgressBarScan.setVisibility(0);
            DeviceListDialog.this.mButtonScan.setEnabled(false);
        }
    }

    public DeviceListDialog(Context context, LayoutInflater inflater) {
        this.mContext = context;
        this.mInflater = inflater;
    }

    public void selectDevice() {
        Set<BluetoothDevice> pairedDevices = this.mBluetoothAdapter.getBondedDevices();
        this.builder = new Builder(this.mContext);
        this.optionDialog = this.builder.create();
        View layoutView = this.mInflater.inflate(C0156R.layout.layout_list, null);
        this.optionDialog.setView(layoutView);
        ListView lv = (ListView) layoutView.findViewById(C0156R.id.lv);
        ListView lvnewdevices = (ListView) layoutView.findViewById(C0156R.id.lvnewdevices);
        lv.setOnItemClickListener(this);
        lvnewdevices.setOnItemClickListener(this);
        this.mProgressBarScan = (ProgressBar) layoutView.findViewById(C0156R.id.progressBarScan);
        lvnewdevices.setAdapter(this.arrayAdapterStringNewDev);
        this.arrayAdapterStringPairDev = new ArrayAdapter(this.mContext, 17367043);
        this.arrayAdapterPairDev = new ArrayAdapter(this.mContext, 17367043);
        for (BluetoothDevice device : pairedDevices) {
            this.arrayAdapterStringPairDev.add(device.getName() + "\n" + device.getAddress());
            this.arrayAdapterPairDev.add(device);
            Log.i("device", "BluetoothDevice = " + device.getName());
        }
        this.mButtonScan = new Button(this.mContext);
        this.mButtonScan.setText(C0156R.string.button_scan_device);
        this.mButtonScan.setOnClickListener(new C01571());
        lv.setAdapter(this.arrayAdapterStringPairDev);
        this.optionDialog.show();
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case C0156R.id.lv:
                Toast.makeText(this.mContext, "Paired in position " + position + " clicked", 1).show();
                break;
            case C0156R.id.lvnewdevices:
                Toast.makeText(this.mContext, "New Item in position " + position + " clicked", 1).show();
                break;
        }
        this.optionDialog.dismiss();
    }
}
