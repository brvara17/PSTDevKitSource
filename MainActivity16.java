package com.example.projects.ineedhelptesting;

/**
 * Created by Projects on 11/21/2017.
 */



        import android.app.Activity;
        import android.app.AlertDialog;
        import android.app.AlertDialog.Builder;
        import android.bluetooth.BluetoothAdapter;
        import android.bluetooth.BluetoothDevice;
        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.content.res.Resources;
        import android.graphics.Bitmap;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.drawable.Drawable;
        import android.graphics.drawable.GradientDrawable;
        import android.os.Bundle;
        import android.os.Environment;
        import android.os.Handler;
        import android.os.Message;
        import android.util.Log;
        import android.view.KeyEvent;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemClickListener;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.ProgressBar;
        import android.widget.SeekBar;
        import android.widget.SeekBar.OnSeekBarChangeListener;
        import android.widget.TextView;
        import android.widget.TextView.OnEditorActionListener;
        import android.widget.Toast;
        import java.io.File;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.OutputStreamWriter;
        import java.lang.reflect.Array;
        import java.text.SimpleDateFormat;
        import java.util.Calendar;
        import java.util.Date;
        import java.util.Set;
        import com.example.projects.ineedhelptesting.bluetooth.BluetoothCommService;

public class MainActivity16 extends Activity implements OnItemClickListener, OnEditorActionListener {
    public static int Rows = 16;
    public static int Columns = 16;
    public static int[] ArrayValores = new int[Rows];
    public static int[][] ArrayValores2 = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{Rows, Columns}));

    private static final boolean f2D = true;
    public static final String DEVICE_NAME = "device_name";
    public static int Frequency = 100;
    private static final int MENU_ID_CONNECT = 4;
    private static final int MENU_ID_GRAVITY = 0;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_TOAST = 5;
    public static final int MESSAGE_WRITE = 3;
    public static int[][] Matrix = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{Rows, Columns}));
    private static final int REQUEST_ENABLE_BT = 3;

    public static String Sflexion = "";
    public static int SongNumber = 0;
    private static final String TAG = "Bluetooth";
    private static final String TIME_STAMP = "dd/MM/yy HH:mm:ss";
    public static final String TOAST = "toast";
    public static int[][] Temp = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{Rows, Columns}));
    public static Bitmap bitmap;
    public static Canvas canvas;
    public static int count = 3;
    public static int count2 = 0;
    public static int count3 = 0;
    public static int[] counter = new int[256];
    public static String msgreceived2 = "";
    public static Bitmap mutableBitmap;
    public static Paint paint;
    public static float px;
    public static Resources f3r;
    public static int[] readBuf = new int[256];
    public static byte[] subarr = new byte[2];
    public static float volumeSong = 0.2f;
    public static Bitmap workingBitmap;
    private boolean Comp = false;
    public int FILTER = 1;
    int Position = 0;
    private ArrayAdapter<BluetoothDevice> arrayAdapterNewDev;
    private ArrayAdapter<BluetoothDevice> arrayAdapterPairDev;
    private ArrayAdapter<String> arrayAdapterStringNewDev;
    private ArrayAdapter<String> arrayAdapterStringPairDev;
    float bent = 0.0f;
    private Builder builder;
    private Button[][] button = ((Button[][]) Array.newInstance(Button.class, new int[]{16, 16}));
    public Button button1;
    private Button compensate;
    FileOutputStream fOut = null;
    private Button filter;
    float flexion = 0.0f;
    private boolean gravit = false;
    public ImageView gravity;
    public ImageView imageBluetooth;
    private boolean intro = false;
    private boolean isConnected = false;
    private BluetoothAdapter mBluetoothAdapter = null;
    private Button mButtonScan;
    private BluetoothCommService mCommService = null;
    private String mConnectedDeviceName = null;
    private final Handler mHandler = new C01122();
    private StringBuffer mOutStringBuffer;
    private ProgressBar mProgressBarScan;
    private final BroadcastReceiver mReceiver = new C01111();
    File myFile;
    private Button number;
    private AlertDialog optionDialog;
    OutputStreamWriter osw = null;
    private ImageView save;
    int savecount = 0;
    private boolean savedata = false;
    private boolean showText = f2D;
    public TextView thres_value;
    int threshold = 0;
    private SeekBar threshold_seek;
    public TextView tv1;
    public EditText txtBrazoDx;
    public EditText txtBrazoSx;
    public EditText txtBusto;
    public TextView txtFlexion;

    class C01111 extends BroadcastReceiver {
        C01111() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.bluetooth.device.action.FOUND".equals(action)) {
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                boolean isDevicePaired = false;
                for (int i = 0; i < MainActivity16.this.arrayAdapterPairDev.getCount(); i++) {
                    if (((BluetoothDevice) MainActivity16.this.arrayAdapterPairDev.getItem(i)).equals(device)) {
                        isDevicePaired = MainActivity16.f2D;
                    }
                }
                if (!isDevicePaired) {
                    MainActivity16.this.arrayAdapterStringNewDev.add(device.getName() + "\n" + device.getAddress());
                    MainActivity16.this.arrayAdapterNewDev.add(device);
                }
                Log.i(MainActivity16.TAG, "Device: " + device.getName() + ", " + device);
            } else if ("android.bluetooth.device.action.UUID".equals(action)) {
                Log.i(MainActivity16.TAG, "device = " + ((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")));
            } else if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(action)) {
                MainActivity16.this.mProgressBarScan.setVisibility(4);
                MainActivity16.this.mButtonScan.setEnabled(MainActivity16.f2D);
            }
        }
    }

    class C01122 extends Handler {
        String tempmsg = new String("");

        C01122() {
        }

        public void handleMessage(Message msg) {
            String text = "";
            switch (msg.what) {
                case 1:
                    Log.i(MainActivity16.TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
                    switch (msg.arg1) {
                        case 0:
                        case 1:
                            MainActivity16.this.setStatus((int) C0156R.string.info_not_connected);
                            MainActivity16.this.isConnected = false;
                            MainActivity16.this.imageBluetooth.setImageResource(C0156R.drawable.bluetooth_disconnected);
                            return;
                        case 2:
                            MainActivity16.this.setStatus((int) C0156R.string.info_connecting);
                            return;
                        case 3:
                            MainActivity16.this.setStatus(MainActivity16.this.getString(C0156R.string.info_connected_to, new Object[]{MainActivity16.this.mConnectedDeviceName}));
                            MainActivity16.this.imageBluetooth.setImageResource(C0156R.drawable.bluetooth_connected);
                            MainActivity16.this.isConnected = MainActivity16.f2D;
                            MainActivity16.this.Provador();
                            return;
                        default:
                            return;
                    }
                case 2:
                    this.tempmsg += new String(msg.obj, 0, msg.arg1);
                    if (this.tempmsg.indexOf("\n") > 0) {
                        System.out.println("Value received :" + this.tempmsg);
                        if (this.tempmsg.indexOf("X") == 0 && this.tempmsg.length() > 511 && this.tempmsg.contains("W")) {
                            MainActivity16.this.setTextViewRX(text, this.tempmsg);
                        }
                        this.tempmsg = "";
                        return;
                    }
                    return;
                case 3:
                    String writeMessage = new String(msg.obj);
                    text = writeMessage;
                    Log.d("Bluetooth WRITE", writeMessage);
                    return;
                case 4:
                    MainActivity16.this.mConnectedDeviceName = msg.getData().getString("device_name");
                    Toast.makeText(MainActivity16.this.getApplicationContext(), "Connected to " + MainActivity16.this.mConnectedDeviceName, 0).show();
                    return;
                case 5:
                    Toast.makeText(MainActivity16.this.getApplicationContext(), msg.getData().getString("toast"), 0).show();
                    return;
                default:
                    return;
            }
        }
    }

    class C01133 implements OnClickListener {
        C01133() {
        }

        public void onClick(View arg0) {
            String hourc;
            String minc;
            String secc;
            String dayc;
            String monthc;
            String yeahc;
            Calendar c = Calendar.getInstance();
            int hour = c.get(11);
            int min = c.get(12);
            int seconds = c.get(13);
            int day = c.get(5);
            int month = c.get(2);
            int year = c.get(1);
            if (hour < 10) {
                hourc = new StringBuilder(String.valueOf('0')).append(Integer.toString(hour)).toString();
            } else {
                hourc = Integer.toString(hour);
            }
            if (min < 10) {
                minc = new StringBuilder(String.valueOf('0')).append(Integer.toString(min)).toString();
            } else {
                minc = Integer.toString(min);
            }
            if (seconds < 10) {
                secc = new StringBuilder(String.valueOf('0')).append(Integer.toString(seconds)).toString();
            } else {
                secc = Integer.toString(seconds);
            }
            if (day < 10) {
                dayc = new StringBuilder(String.valueOf('0')).append(Integer.toString(day)).toString();
            } else {
                dayc = Integer.toString(day);
            }
            if (month < 10) {
                monthc = new StringBuilder(String.valueOf('0')).append(Integer.toString(month)).toString();
            } else {
                monthc = Integer.toString(month);
            }
            if (year < 10) {
                yeahc = new StringBuilder(String.valueOf('0')).append(Integer.toString(year)).toString();
            } else {
                yeahc = Integer.toString(year);
            }
            System.out.println("Value received :" + Integer.toString(((((hour + min) + seconds) + day) + month) + year));
            if (MainActivity16.this.savedata) {
                try {
                    MainActivity16.this.osw.flush();
                    MainActivity16.this.osw.close();
                    MainActivity16.this.fOut.flush();
                    MainActivity16.this.fOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MainActivity16.this.savedata = false;
                MainActivity16.this.save.setImageResource(C0156R.drawable.rec2);
                return;
            }
            try {
                MainActivity16.this.myFile = new File("/sdcard/SensingTexData_" + dayc + monthc + yeahc + "_" + hourc + minc + secc + ".csv");
                MainActivity16.this.myFile.createNewFile();
                MainActivity16.this.fOut = new FileOutputStream(MainActivity16.this.myFile);
                MainActivity16.this.fOut.write("TIMESTAMP,R01C01,R01C02,R01C03,R01C04,R01C05,R01C06,R01C07,R01C08,R01C09,R01C10,R01C11,R01C12,R01C13,R01C14,R01C15,R01C16,R02C01,R02C02,R02C03,R02C04,R02C05,R02C06,R02C07,R02C08,R02C09,R02C10,R02C11,R02C12,R02C13,R02C14,R02C15,R02C16,R03C01,R03C02,R03C03,R03C04,R03C05,R03C06,R03C07,R03C08,R03C09,R03C10,R03C11,R03C12,R03C13,R03C14,R03C15,R03C16,R04C01,R04C02,R04C03,R04C04,R04C05,R04C06,R04C07,R04C08,R04C09,R04C10,R04C11,R04C12,R04C13,R04C14,R04C15,R04C16,R05C01,R05C02,R05C03,R05C04,R05C05,R05C06,R05C07,R05C08,R05C09,R05C10,R05C11,R05C12,R05C13,R05C14,R05C15,R05C16,R06C01,R06C02,R06C03,R06C04,R06C05,R06C06,R06C07,R06C08,R06C09,R06C10,R06C11,R06C12,R06C13,R06C14,R06C15,R06C16,R07C01,R07C02,R07C03,R07C04,R07C05,R07C06,R07C07,R07C08,R07C09,R07C10,R07C11,R07C12,R07C13,R07C14,R07C15,R07C16,R08C01,R08C02,R08C03,R08C04,R08C05,R08C06,R08C07,R08C08,R08C09,R08C10,R08C11,R08C12,R08C13,R08C14,R08C15,R08C16,R09C01,R09C02,R09C03,R09C04,R09C05,R09C06,R09C07,R09C08,R09C09,R09C10,R09C11,R09C12,R09C13,R09C14,R09C15,R09C16,R10C01,R10C02,R10C03,R10C04,R10C05,R10C06,R10C07,R10C08,R10C09,R10C10,R10C11,R10C12,R10C13,R10C14,R10C15,R10C16,R11C01,R11C02,R11C03,R11C04,R11C05,R11C06,R11C07,R11C08,R11C09,R11C10,R11C11,R11C12,R11C13,R11C14,R11C15,R11C16,R12C01,R12C02,R12C03,R12C04,R12C05,R12C06,R12C07,R12C08,R12C09,R12C10,R12C11,R12C12,R12C13,R12C14,R12C15,R12C16,R13C01,R13C02,R13C03,R13C04,R13C05,R13C06,R13C07,R13C08,R13C09,R13C10,R13C11,R13C12,R13C13,R13C14,R13C15,R13C16,R14C01,R14C02,R14C03,R14C04,R14C05,R14C06,R14C07,R14C08,R14C09,R14C10,R14C11,R14C12,R14C13,R14C14,R14C15,R14C16,R15C01,R15C02,R15C03,R15C04,R15C05,R15C06,R15C07,R15C08,R15C09,R15C10,R15C11,R15C12,R15C13,R15C14,R15C15,R15C16,R16C01,R16C02,R16C03,R16C04,R16C05,R16C06,R16C07,R16C08,R16C09,R16C10,R16C11,R16C12,R16C13,R16C14,R16C15,R16C16".getBytes());
                MainActivity16.this.osw = new OutputStreamWriter(MainActivity16.this.fOut);
                MainActivity16.this.osw.append("\r\n");
            } catch (Exception e2) {
                Toast.makeText(MainActivity16.this.getBaseContext(), e2.getMessage(), 0).show();
            }
            MainActivity16.this.savedata = MainActivity16.f2D;
        }
    }

    class C01144 implements OnClickListener {
        C01144() {
        }

        public void onClick(View arg0) {
            if (MainActivity16.this.FILTER == 8) {
                MainActivity16.this.FILTER = 0;
            }
            MainActivity16 mainActivity16 = MainActivity16.this;
            mainActivity16.FILTER++;
            Toast.makeText(MainActivity16.this, "Filter = " + MainActivity16.this.FILTER, 0).show();
        }
    }

    class C01155 implements OnClickListener {
        C01155() {
        }

        public void onClick(View arg0) {
            if (MainActivity16.this.showText) {
                MainActivity16.this.showText = false;
            } else {
                MainActivity16.this.showText = MainActivity16.f2D;
            }
        }
    }

    class C01166 implements OnClickListener {
        C01166() {
        }

        public void onClick(View arg0) {
            if (MainActivity16.this.Comp) {
                MainActivity16.this.Comp = false;
            } else {
                MainActivity16.this.Comp = MainActivity16.f2D;
            }
        }
    }

    class C01177 implements OnSeekBarChangeListener {
        C01177() {
        }

        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MainActivity16.this.threshold = (progress * 1000) / 100;
            MainActivity16.this.thres_value.setText(String.valueOf(MainActivity16.this.threshold));
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    class C01188 implements OnClickListener {
        C01188() {
        }

        public void onClick(View v) {
            MainActivity16.this.mBluetoothAdapter.startDiscovery();
            MainActivity16.this.arrayAdapterStringNewDev.clear();
            MainActivity16.this.arrayAdapterNewDev.clear();
            MainActivity16.this.mProgressBarScan.setVisibility(0);
            MainActivity16.this.mButtonScan.setEnabled(false);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "+++ ON CREATE +++");
        getWindow().addFlags(128);
        getActionBar().setTitle("PST Dev Kit 16x16");
        setContentView(C0156R.layout.activity_main16);
        this.imageBluetooth = (ImageView) findViewById(C0156R.id.imageBluetooth);
        this.gravity = (ImageView) findViewById(C0156R.id.gravity);
        this.tv1 = (TextView) findViewById(C0156R.id.textView1);
        this.txtFlexion = (TextView) findViewById(C0156R.id.txtFlexion);
        this.thres_value = (TextView) findViewById(C0156R.id.thres_value);
        this.button[0][0] = (Button) findViewById(C0156R.id.button11);
        this.button[0][1] = (Button) findViewById(C0156R.id.button12);
        this.button[0][2] = (Button) findViewById(C0156R.id.button13);
        this.button[0][3] = (Button) findViewById(C0156R.id.button14);
        this.button[0][4] = (Button) findViewById(C0156R.id.button15);
        this.button[0][5] = (Button) findViewById(C0156R.id.button16);
        this.button[0][6] = (Button) findViewById(C0156R.id.button17);
        this.button[0][7] = (Button) findViewById(C0156R.id.button18);
        this.button[0][8] = (Button) findViewById(C0156R.id.button19);
        this.button[0][9] = (Button) findViewById(C0156R.id.button1A);
        this.button[0][10] = (Button) findViewById(C0156R.id.button1B);
        this.button[0][11] = (Button) findViewById(C0156R.id.button1C);
        this.button[0][12] = (Button) findViewById(C0156R.id.button1D);
        this.button[0][13] = (Button) findViewById(C0156R.id.button1E);
        this.button[0][14] = (Button) findViewById(C0156R.id.button1F);
        this.button[0][15] = (Button) findViewById(C0156R.id.button1G);
        this.button[1][0] = (Button) findViewById(C0156R.id.button21);
        this.button[1][1] = (Button) findViewById(C0156R.id.button22);
        this.button[1][2] = (Button) findViewById(C0156R.id.button23);
        this.button[1][3] = (Button) findViewById(C0156R.id.button24);
        this.button[1][4] = (Button) findViewById(C0156R.id.button25);
        this.button[1][5] = (Button) findViewById(C0156R.id.button26);
        this.button[1][6] = (Button) findViewById(C0156R.id.button27);
        this.button[1][7] = (Button) findViewById(C0156R.id.button28);
        this.button[1][8] = (Button) findViewById(C0156R.id.button29);
        this.button[1][9] = (Button) findViewById(C0156R.id.button2A);
        this.button[1][10] = (Button) findViewById(C0156R.id.button2B);
        this.button[1][11] = (Button) findViewById(C0156R.id.button2C);
        this.button[1][12] = (Button) findViewById(C0156R.id.button2D);
        this.button[1][13] = (Button) findViewById(C0156R.id.button2E);
        this.button[1][14] = (Button) findViewById(C0156R.id.button2F);
        this.button[1][15] = (Button) findViewById(C0156R.id.button2G);
        this.button[2][0] = (Button) findViewById(C0156R.id.button31);
        this.button[2][1] = (Button) findViewById(C0156R.id.button32);
        this.button[2][2] = (Button) findViewById(C0156R.id.button33);
        this.button[2][3] = (Button) findViewById(C0156R.id.button34);
        this.button[2][4] = (Button) findViewById(C0156R.id.button35);
        this.button[2][5] = (Button) findViewById(C0156R.id.button36);
        this.button[2][6] = (Button) findViewById(C0156R.id.button37);
        this.button[2][7] = (Button) findViewById(C0156R.id.button38);
        this.button[2][8] = (Button) findViewById(C0156R.id.button39);
        this.button[2][9] = (Button) findViewById(C0156R.id.button3A);
        this.button[2][10] = (Button) findViewById(C0156R.id.button3B);
        this.button[2][11] = (Button) findViewById(C0156R.id.button3C);
        this.button[2][12] = (Button) findViewById(C0156R.id.button3D);
        this.button[2][13] = (Button) findViewById(C0156R.id.button3E);
        this.button[2][14] = (Button) findViewById(C0156R.id.button3F);
        this.button[2][15] = (Button) findViewById(C0156R.id.button3G);
        this.button[3][0] = (Button) findViewById(C0156R.id.button41);
        this.button[3][1] = (Button) findViewById(C0156R.id.button42);
        this.button[3][2] = (Button) findViewById(C0156R.id.button43);
        this.button[3][3] = (Button) findViewById(C0156R.id.button44);
        this.button[3][4] = (Button) findViewById(C0156R.id.button45);
        this.button[3][5] = (Button) findViewById(C0156R.id.button46);
        this.button[3][6] = (Button) findViewById(C0156R.id.button47);
        this.button[3][7] = (Button) findViewById(C0156R.id.button48);
        this.button[3][8] = (Button) findViewById(C0156R.id.button49);
        this.button[3][9] = (Button) findViewById(C0156R.id.button4A);
        this.button[3][10] = (Button) findViewById(C0156R.id.button4B);
        this.button[3][11] = (Button) findViewById(C0156R.id.button4C);
        this.button[3][12] = (Button) findViewById(C0156R.id.button4D);
        this.button[3][13] = (Button) findViewById(C0156R.id.button4E);
        this.button[3][14] = (Button) findViewById(C0156R.id.button4F);
        this.button[3][15] = (Button) findViewById(C0156R.id.button4G);
        this.button[4][0] = (Button) findViewById(C0156R.id.button51);
        this.button[4][1] = (Button) findViewById(C0156R.id.button52);
        this.button[4][2] = (Button) findViewById(C0156R.id.button53);
        this.button[4][3] = (Button) findViewById(C0156R.id.button54);
        this.button[4][4] = (Button) findViewById(C0156R.id.button55);
        this.button[4][5] = (Button) findViewById(C0156R.id.button56);
        this.button[4][6] = (Button) findViewById(C0156R.id.button57);
        this.button[4][7] = (Button) findViewById(C0156R.id.button58);
        this.button[4][8] = (Button) findViewById(C0156R.id.button59);
        this.button[4][9] = (Button) findViewById(C0156R.id.button5A);
        this.button[4][10] = (Button) findViewById(C0156R.id.button5B);
        this.button[4][11] = (Button) findViewById(C0156R.id.button5C);
        this.button[4][12] = (Button) findViewById(C0156R.id.button5D);
        this.button[4][13] = (Button) findViewById(C0156R.id.button5E);
        this.button[4][14] = (Button) findViewById(C0156R.id.button5F);
        this.button[4][15] = (Button) findViewById(C0156R.id.button5G);
        this.button[5][0] = (Button) findViewById(C0156R.id.button61);
        this.button[5][1] = (Button) findViewById(C0156R.id.button62);
        this.button[5][2] = (Button) findViewById(C0156R.id.button63);
        this.button[5][3] = (Button) findViewById(C0156R.id.button64);
        this.button[5][4] = (Button) findViewById(C0156R.id.button65);
        this.button[5][5] = (Button) findViewById(C0156R.id.button66);
        this.button[5][6] = (Button) findViewById(C0156R.id.button67);
        this.button[5][7] = (Button) findViewById(C0156R.id.button68);
        this.button[5][8] = (Button) findViewById(C0156R.id.button69);
        this.button[5][9] = (Button) findViewById(C0156R.id.button6A);
        this.button[5][10] = (Button) findViewById(C0156R.id.button6B);
        this.button[5][11] = (Button) findViewById(C0156R.id.button6C);
        this.button[5][12] = (Button) findViewById(C0156R.id.button6D);
        this.button[5][13] = (Button) findViewById(C0156R.id.button6E);
        this.button[5][14] = (Button) findViewById(C0156R.id.button6F);
        this.button[5][15] = (Button) findViewById(C0156R.id.button6G);
        this.button[6][0] = (Button) findViewById(C0156R.id.button71);
        this.button[6][1] = (Button) findViewById(C0156R.id.button72);
        this.button[6][2] = (Button) findViewById(C0156R.id.button73);
        this.button[6][3] = (Button) findViewById(C0156R.id.button74);
        this.button[6][4] = (Button) findViewById(C0156R.id.button75);
        this.button[6][5] = (Button) findViewById(C0156R.id.button76);
        this.button[6][6] = (Button) findViewById(C0156R.id.button77);
        this.button[6][7] = (Button) findViewById(C0156R.id.button78);
        this.button[6][8] = (Button) findViewById(C0156R.id.button79);
        this.button[6][9] = (Button) findViewById(C0156R.id.button7A);
        this.button[6][10] = (Button) findViewById(C0156R.id.button7B);
        this.button[6][11] = (Button) findViewById(C0156R.id.button7C);
        this.button[6][12] = (Button) findViewById(C0156R.id.button7D);
        this.button[6][13] = (Button) findViewById(C0156R.id.button7E);
        this.button[6][14] = (Button) findViewById(C0156R.id.button7F);
        this.button[6][15] = (Button) findViewById(C0156R.id.button7G);
        this.button[7][0] = (Button) findViewById(C0156R.id.button81);
        this.button[7][1] = (Button) findViewById(C0156R.id.button82);
        this.button[7][2] = (Button) findViewById(C0156R.id.button83);
        this.button[7][3] = (Button) findViewById(C0156R.id.button84);
        this.button[7][4] = (Button) findViewById(C0156R.id.button85);
        this.button[7][5] = (Button) findViewById(C0156R.id.button86);
        this.button[7][6] = (Button) findViewById(C0156R.id.button87);
        this.button[7][7] = (Button) findViewById(C0156R.id.button88);
        this.button[7][8] = (Button) findViewById(C0156R.id.button89);
        this.button[7][9] = (Button) findViewById(C0156R.id.button8A);
        this.button[7][10] = (Button) findViewById(C0156R.id.button8B);
        this.button[7][11] = (Button) findViewById(C0156R.id.button8C);
        this.button[7][12] = (Button) findViewById(C0156R.id.button8D);
        this.button[7][13] = (Button) findViewById(C0156R.id.button8E);
        this.button[7][14] = (Button) findViewById(C0156R.id.button8F);
        this.button[7][15] = (Button) findViewById(C0156R.id.button8G);
        this.button[8][0] = (Button) findViewById(C0156R.id.button91);
        this.button[8][1] = (Button) findViewById(C0156R.id.button92);
        this.button[8][2] = (Button) findViewById(C0156R.id.button93);
        this.button[8][3] = (Button) findViewById(C0156R.id.button94);
        this.button[8][4] = (Button) findViewById(C0156R.id.button95);
        this.button[8][5] = (Button) findViewById(C0156R.id.button96);
        this.button[8][6] = (Button) findViewById(C0156R.id.button97);
        this.button[8][7] = (Button) findViewById(C0156R.id.button98);
        this.button[8][8] = (Button) findViewById(C0156R.id.button99);
        this.button[8][9] = (Button) findViewById(C0156R.id.button9A);
        this.button[8][10] = (Button) findViewById(C0156R.id.button9B);
        this.button[8][11] = (Button) findViewById(C0156R.id.button9C);
        this.button[8][12] = (Button) findViewById(C0156R.id.button9D);
        this.button[8][13] = (Button) findViewById(C0156R.id.button9E);
        this.button[8][14] = (Button) findViewById(C0156R.id.button9F);
        this.button[8][15] = (Button) findViewById(C0156R.id.button9G);
        this.button[9][0] = (Button) findViewById(C0156R.id.buttonA1);
        this.button[9][1] = (Button) findViewById(C0156R.id.buttonA2);
        this.button[9][2] = (Button) findViewById(C0156R.id.buttonA3);
        this.button[9][3] = (Button) findViewById(C0156R.id.buttonA4);
        this.button[9][4] = (Button) findViewById(C0156R.id.buttonA5);
        this.button[9][5] = (Button) findViewById(C0156R.id.buttonA6);
        this.button[9][6] = (Button) findViewById(C0156R.id.buttonA7);
        this.button[9][7] = (Button) findViewById(C0156R.id.buttonA8);
        this.button[9][8] = (Button) findViewById(C0156R.id.buttonA9);
        this.button[9][9] = (Button) findViewById(C0156R.id.buttonAA);
        this.button[9][10] = (Button) findViewById(C0156R.id.buttonAB);
        this.button[9][11] = (Button) findViewById(C0156R.id.buttonAC);
        this.button[9][12] = (Button) findViewById(C0156R.id.buttonAD);
        this.button[9][13] = (Button) findViewById(C0156R.id.buttonAE);
        this.button[9][14] = (Button) findViewById(C0156R.id.buttonAF);
        this.button[9][15] = (Button) findViewById(C0156R.id.buttonAG);
        this.button[10][0] = (Button) findViewById(C0156R.id.buttonB1);
        this.button[10][1] = (Button) findViewById(C0156R.id.buttonB2);
        this.button[10][2] = (Button) findViewById(C0156R.id.buttonB3);
        this.button[10][3] = (Button) findViewById(C0156R.id.buttonB4);
        this.button[10][4] = (Button) findViewById(C0156R.id.buttonB5);
        this.button[10][5] = (Button) findViewById(C0156R.id.buttonB6);
        this.button[10][6] = (Button) findViewById(C0156R.id.buttonB7);
        this.button[10][7] = (Button) findViewById(C0156R.id.buttonB8);
        this.button[10][8] = (Button) findViewById(C0156R.id.buttonB9);
        this.button[10][9] = (Button) findViewById(C0156R.id.buttonBA);
        this.button[10][10] = (Button) findViewById(C0156R.id.buttonBB);
        this.button[10][11] = (Button) findViewById(C0156R.id.buttonBC);
        this.button[10][12] = (Button) findViewById(C0156R.id.buttonBD);
        this.button[10][13] = (Button) findViewById(C0156R.id.buttonBE);
        this.button[10][14] = (Button) findViewById(C0156R.id.buttonBF);
        this.button[10][15] = (Button) findViewById(C0156R.id.buttonBG);
        this.button[11][0] = (Button) findViewById(C0156R.id.buttonC1);
        this.button[11][1] = (Button) findViewById(C0156R.id.buttonC2);
        this.button[11][2] = (Button) findViewById(C0156R.id.buttonC3);
        this.button[11][3] = (Button) findViewById(C0156R.id.buttonC4);
        this.button[11][4] = (Button) findViewById(C0156R.id.buttonC5);
        this.button[11][5] = (Button) findViewById(C0156R.id.buttonC6);
        this.button[11][6] = (Button) findViewById(C0156R.id.buttonC7);
        this.button[11][7] = (Button) findViewById(C0156R.id.buttonC8);
        this.button[11][8] = (Button) findViewById(C0156R.id.buttonC9);
        this.button[11][9] = (Button) findViewById(C0156R.id.buttonCA);
        this.button[11][10] = (Button) findViewById(C0156R.id.buttonCB);
        this.button[11][11] = (Button) findViewById(C0156R.id.buttonCC);
        this.button[11][12] = (Button) findViewById(C0156R.id.buttonCD);
        this.button[11][13] = (Button) findViewById(C0156R.id.buttonCE);
        this.button[11][14] = (Button) findViewById(C0156R.id.buttonCF);
        this.button[11][15] = (Button) findViewById(C0156R.id.buttonCG);
        this.button[12][0] = (Button) findViewById(C0156R.id.buttonD1);
        this.button[12][1] = (Button) findViewById(C0156R.id.buttonD2);
        this.button[12][2] = (Button) findViewById(C0156R.id.buttonD3);
        this.button[12][3] = (Button) findViewById(C0156R.id.buttonD4);
        this.button[12][4] = (Button) findViewById(C0156R.id.buttonD5);
        this.button[12][5] = (Button) findViewById(C0156R.id.buttonD6);
        this.button[12][6] = (Button) findViewById(C0156R.id.buttonD7);
        this.button[12][7] = (Button) findViewById(C0156R.id.buttonD8);
        this.button[12][8] = (Button) findViewById(C0156R.id.buttonD9);
        this.button[12][9] = (Button) findViewById(C0156R.id.buttonDA);
        this.button[12][10] = (Button) findViewById(C0156R.id.buttonDB);
        this.button[12][11] = (Button) findViewById(C0156R.id.buttonDC);
        this.button[12][12] = (Button) findViewById(C0156R.id.buttonDD);
        this.button[12][13] = (Button) findViewById(C0156R.id.buttonDE);
        this.button[12][14] = (Button) findViewById(C0156R.id.buttonDF);
        this.button[12][15] = (Button) findViewById(C0156R.id.buttonDG);
        this.button[13][0] = (Button) findViewById(C0156R.id.buttonE1);
        this.button[13][1] = (Button) findViewById(C0156R.id.buttonE2);
        this.button[13][2] = (Button) findViewById(C0156R.id.buttonE3);
        this.button[13][3] = (Button) findViewById(C0156R.id.buttonE4);
        this.button[13][4] = (Button) findViewById(C0156R.id.buttonE5);
        this.button[13][5] = (Button) findViewById(C0156R.id.buttonE6);
        this.button[13][6] = (Button) findViewById(C0156R.id.buttonE7);
        this.button[13][7] = (Button) findViewById(C0156R.id.buttonE8);
        this.button[13][8] = (Button) findViewById(C0156R.id.buttonE9);
        this.button[13][9] = (Button) findViewById(C0156R.id.buttonEA);
        this.button[13][10] = (Button) findViewById(C0156R.id.buttonEB);
        this.button[13][11] = (Button) findViewById(C0156R.id.buttonEC);
        this.button[13][12] = (Button) findViewById(C0156R.id.buttonED);
        this.button[13][13] = (Button) findViewById(C0156R.id.buttonEE);
        this.button[13][14] = (Button) findViewById(C0156R.id.buttonEF);
        this.button[13][15] = (Button) findViewById(C0156R.id.buttonEG);
        this.button[14][0] = (Button) findViewById(C0156R.id.buttonF1);
        this.button[14][1] = (Button) findViewById(C0156R.id.buttonF2);
        this.button[14][2] = (Button) findViewById(C0156R.id.buttonF3);
        this.button[14][3] = (Button) findViewById(C0156R.id.buttonF4);
        this.button[14][4] = (Button) findViewById(C0156R.id.buttonF5);
        this.button[14][5] = (Button) findViewById(C0156R.id.buttonF6);
        this.button[14][6] = (Button) findViewById(C0156R.id.buttonF7);
        this.button[14][7] = (Button) findViewById(C0156R.id.buttonF8);
        this.button[14][8] = (Button) findViewById(C0156R.id.buttonF9);
        this.button[14][9] = (Button) findViewById(C0156R.id.buttonFA);
        this.button[14][10] = (Button) findViewById(C0156R.id.buttonFB);
        this.button[14][11] = (Button) findViewById(C0156R.id.buttonFC);
        this.button[14][12] = (Button) findViewById(C0156R.id.buttonFD);
        this.button[14][13] = (Button) findViewById(C0156R.id.buttonFE);
        this.button[14][14] = (Button) findViewById(C0156R.id.buttonFF);
        this.button[14][15] = (Button) findViewById(C0156R.id.buttonFG);
        this.button[15][0] = (Button) findViewById(C0156R.id.buttonG1);
        this.button[15][1] = (Button) findViewById(C0156R.id.buttonG2);
        this.button[15][2] = (Button) findViewById(C0156R.id.buttonG3);
        this.button[15][3] = (Button) findViewById(C0156R.id.buttonG4);
        this.button[15][4] = (Button) findViewById(C0156R.id.buttonG5);
        this.button[15][5] = (Button) findViewById(C0156R.id.buttonG6);
        this.button[15][6] = (Button) findViewById(C0156R.id.buttonG7);
        this.button[15][7] = (Button) findViewById(C0156R.id.buttonG8);
        this.button[15][8] = (Button) findViewById(C0156R.id.buttonG9);
        this.button[15][9] = (Button) findViewById(C0156R.id.buttonGA);
        this.button[15][10] = (Button) findViewById(C0156R.id.buttonGB);
        this.button[15][11] = (Button) findViewById(C0156R.id.buttonGC);
        this.button[15][12] = (Button) findViewById(C0156R.id.buttonGD);
        this.button[15][13] = (Button) findViewById(C0156R.id.buttonGE);
        this.button[15][14] = (Button) findViewById(C0156R.id.buttonGF);
        this.button[15][15] = (Button) findViewById(C0156R.id.buttonGG);
        this.filter = (Button) findViewById(C0156R.id.filter);
        this.number = (Button) findViewById(C0156R.id.number);
        this.compensate = (Button) findViewById(C0156R.id.comp);
        this.save = (ImageView) findViewById(C0156R.id.rec);
        this.gravity.setVisibility(4);
        getActionBar().setDisplayShowHomeEnabled(false);
        paint = new Paint();
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.threshold_seek = (SeekBar) findViewById(C0156R.id.seekBar1);
        if (this.mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", 1).show();
            finish();
            return;
        }
        this.save.setOnClickListener(new C01133());
        this.filter.setOnClickListener(new C01144());
        this.number.setOnClickListener(new C01155());
        this.compensate.setOnClickListener(new C01166());
        this.threshold_seek.setOnSeekBarChangeListener(new C01177());
    }

    public static boolean canWriteOnExternalStorage() {
        if ("mounted".equals(Environment.getExternalStorageState())) {
            return f2D;
        }
        return false;
    }

    public void resetBtnColor() {
    }

    protected void onStart() {
        super.onStart();
        Log.d(TAG, "++ ON START ++");
        if (!this.mBluetoothAdapter.isEnabled()) {
            startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 3);
        } else if (this.mCommService == null) {
            setupCommunication();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.bluetooth.device.action.FOUND");
        filter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        registerReceiver(this.mReceiver, filter);
    }

    private void setupCommunication() {
        this.arrayAdapterStringNewDev = new ArrayAdapter(this, 17367043);
        this.arrayAdapterNewDev = new ArrayAdapter(this, 17367043);
        this.mCommService = new BluetoothCommService(this, this.mHandler);
        this.mOutStringBuffer = new StringBuffer("");
    }

    public synchronized void onResume() {
        super.onResume();
        Log.d(TAG, "+ ON RESUME +");
        if (this.mCommService != null && this.mCommService.getState() == 0) {
            BluetoothCommService.start();
        }
    }

    public synchronized void onPause() {
        super.onPause();
        Log.d(TAG, "- ON PAUSE -");
    }

    public void onStop() {
        super.onStop();
        Log.d(TAG, "-- ON STOP --");
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.mCommService != null) {
            this.mCommService.stop();
        }
        unregisterReceiver(this.mReceiver);
        Log.d(TAG, "--- ON DESTROY ---");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult " + resultCode);
        switch (requestCode) {
            case 3:
                if (resultCode == -1) {
                    setupCommunication();
                    return;
                }
                Log.d(TAG, "BT not enabled");
                Toast.makeText(this, C0156R.string.bt_not_enabled_leaving, 0).show();
                finish();
                return;
            default:
                return;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 4, 0, "Connect Device");
        menu.add(0, 0, 0, "Set centre of gravity");
        return f2D;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                if (this.gravit) {
                    this.gravit = false;
                    return f2D;
                }
                this.gravit = f2D;
                return f2D;
            case 4:
                if (this.isConnected) {
                    this.mCommService.stop();
                    return f2D;
                }
                selectDevice();
                return f2D;
            default:
                return false;
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (this.isConnected) {
            menu.findItem(4).setTitle(C0156R.string.action_disconnect_device);
        } else if (!this.isConnected) {
            menu.findItem(4).setTitle(C0156R.string.action_connect_device);
        }
        if (this.gravit) {
            menu.findItem(0).setTitle("Disable centre of gravity");
        } else {
            menu.findItem(0).setTitle("Set centre of gravity");
        }
        return f2D;
    }

    private void selectDevice() {
        Set<BluetoothDevice> pairedDevices = this.mBluetoothAdapter.getBondedDevices();
        this.builder = new Builder(this);
        this.optionDialog = this.builder.create();
        View layoutView = getLayoutInflater().inflate(C0156R.layout.layout_list, null);
        this.optionDialog.setView(layoutView);
        ListView lv = (ListView) layoutView.findViewById(C0156R.id.lv);
        ListView lvnewdevices = (ListView) layoutView.findViewById(C0156R.id.lvnewdevices);
        lv.setOnItemClickListener(this);
        lvnewdevices.setOnItemClickListener(this);
        this.mProgressBarScan = (ProgressBar) layoutView.findViewById(C0156R.id.progressBarScan);
        lvnewdevices.setAdapter(this.arrayAdapterStringNewDev);
        this.arrayAdapterStringPairDev = new ArrayAdapter(this, 17367043);
        this.arrayAdapterPairDev = new ArrayAdapter(this, 17367043);
        for (BluetoothDevice device : pairedDevices) {
            this.arrayAdapterStringPairDev.add(device.getName() + "\n" + device.getAddress());
            this.arrayAdapterPairDev.add(device);
            Log.i("device", "BluetoothDevice = " + device.getName());
        }
        this.mButtonScan = new Button(this);
        this.mButtonScan.setText(C0156R.string.button_scan_device);
        this.mButtonScan.setOnClickListener(new C01188());
        lv.addFooterView(this.mButtonScan);
        lv.setAdapter(this.arrayAdapterStringPairDev);
        this.optionDialog.show();
    }

    private final void setStatus(int resId) {
    }

    private final void setStatus(CharSequence subTitle) {
    }

    public String getTime() {
        return new SimpleDateFormat(TIME_STAMP).format(new Date());
    }

    public void setTextViewRX(String text, final String Valore1) {
        runOnUiThread(new Runnable() {
            public void run() {
                int[][] red = (int[][]) Array.newInstance(Integer.TYPE, new int[]{16, 16});
                int[][] green = (int[][]) Array.newInstance(Integer.TYPE, new int[]{16, 16});
                int[][] blue = (int[][]) Array.newInstance(Integer.TYPE, new int[]{16, 16});
                int tensionNumerator = 1024;
                if (!Valore1.contains("X")) {
                    return;
                }
                if (Valore1.length() > 200 || MainActivity16.this.intro) {
                    int i;
                    int j;
                    MainActivity16.this.intro = MainActivity16.f2D;
                    String[] separateF = Valore1.split("\n")[0].split("E");
                    String[] separateE = separateF[1].split("D");
                    String[] separateD = separateE[1].split("C");
                    String[] separateC = separateD[1].split("B");
                    String[] separateB = separateC[1].split("A");
                    String[] separateA = separateB[1].split("V");
                    String[] separateV = separateA[1].split("U");
                    String[] separateU = separateV[1].split("T");
                    String[] separateT = separateU[1].split("S");
                    String[] separateS = separateT[1].split("R");
                    String[] separateR = separateS[1].split("Q");
                    String[] separateQ = separateR[1].split("P");
                    String[] separateP = separateQ[1].split("O");
                    String[] separateO = separateP[1].split("N");
                    String[] separateN = separateO[1].split("M");
                    String[] separateM = separateN[1].split("F");
                    String fin = separateF[0].substring(1);
                    String[] separate2F = separateM[1].split("E");
                    String[] separate2E = separateF[2].split("D");
                    String[] separate2D = separate2E[1].split("C");
                    String[] separate2C = separate2D[1].split("B");
                    String[] separate2B = separate2C[1].split("A");
                    String[] separate2A = separate2B[1].split("V");
                    String[] separate2V = separate2A[1].split("U");
                    String[] separate2U = separate2V[1].split("T");
                    String[] separate2T = separate2U[1].split("S");
                    String[] separate2S = separate2T[1].split("R");
                    String[] separate2R = separate2S[1].split("Q");
                    String[] separate2Q = separate2R[1].split("P");
                    String[] separate2P = separate2Q[1].split("O");
                    String[] separate2O = separate2P[1].split("N");
                    String[] separate2N = separate2O[1].split("M");
                    String[] separate2M = separate2N[1].split("F");
                    String[] separate3F = separate2M[1].split("E");
                    String[] separate3E = separateF[3].split("D");
                    String[] separate3D = separate3E[1].split("C");
                    String[] separate3C = separate3D[1].split("B");
                    String[] separate3B = separate3C[1].split("A");
                    String[] separate3A = separate3B[1].split("V");
                    String[] separate3V = separate3A[1].split("U");
                    String[] separate3U = separate3V[1].split("T");
                    String[] separate3T = separate3U[1].split("S");
                    String[] separate3S = separate3T[1].split("R");
                    String[] separate3R = separate3S[1].split("Q");
                    String[] separate3Q = separate3R[1].split("P");
                    String[] separate3P = separate3Q[1].split("O");
                    String[] separate3O = separate3P[1].split("N");
                    String[] separate3N = separate3O[1].split("M");
                    String[] separate3M = separate3N[1].split("F");
                    String[] separate4F = separate3M[1].split("E");
                    String[] separate4E = separateF[4].split("D");
                    String[] separate4D = separate4E[1].split("C");
                    String[] separate4C = separate4D[1].split("B");
                    String[] separate4B = separate4C[1].split("A");
                    String[] separate4A = separate4B[1].split("V");
                    String[] separate4V = separate4A[1].split("U");
                    String[] separate4U = separate4V[1].split("T");
                    String[] separate4T = separate4U[1].split("S");
                    String[] separate4S = separate4T[1].split("R");
                    String[] separate4R = separate4S[1].split("Q");
                    String[] separate4Q = separate4R[1].split("P");
                    String[] separate4P = separate4Q[1].split("O");
                    String[] separate4O = separate4P[1].split("N");
                    String[] separate4N = separate4O[1].split("M");
                    String[] separate4M = separate4N[1].split("F");
                    String[] s2eparateF = separate4M[1].split("E");
                    String[] s2eparateE = separateF[5].split("D");
                    String[] s2eparateD = s2eparateE[1].split("C");
                    String[] s2eparateC = s2eparateD[1].split("B");
                    String[] s2eparateB = s2eparateC[1].split("A");
                    String[] s2eparateA = s2eparateB[1].split("V");
                    String[] s2eparateV = s2eparateA[1].split("U");
                    String[] s2eparateU = s2eparateV[1].split("T");
                    String[] s2eparateT = s2eparateU[1].split("S");
                    String[] s2eparateS = s2eparateT[1].split("R");
                    String[] s2eparateR = s2eparateS[1].split("Q");
                    String[] s2eparateQ = s2eparateR[1].split("P");
                    String[] s2eparateP = s2eparateQ[1].split("O");
                    String[] s2eparateO = s2eparateP[1].split("N");
                    String[] s2eparateN = s2eparateO[1].split("M");
                    String[] s2eparateM = s2eparateN[1].split("F");
                    String[] s2eparate2F = s2eparateM[1].split("E");
                    String[] s2eparate2E = separateF[6].split("D");
                    String[] s2eparate2D = s2eparate2E[1].split("C");
                    String[] s2eparate2C = s2eparate2D[1].split("B");
                    String[] s2eparate2B = s2eparate2C[1].split("A");
                    String[] s2eparate2A = s2eparate2B[1].split("V");
                    String[] s2eparate2V = s2eparate2A[1].split("U");
                    String[] s2eparate2U = s2eparate2V[1].split("T");
                    String[] s2eparate2T = s2eparate2U[1].split("S");
                    String[] s2eparate2S = s2eparate2T[1].split("R");
                    String[] s2eparate2R = s2eparate2S[1].split("Q");
                    String[] s2eparate2Q = s2eparate2R[1].split("P");
                    String[] s2eparate2P = s2eparate2Q[1].split("O");
                    String[] s2eparate2O = s2eparate2P[1].split("N");
                    String[] s2eparate2N = s2eparate2O[1].split("M");
                    String[] s2eparate2M = s2eparate2N[1].split("F");
                    String[] s2eparate3F = s2eparate2M[1].split("E");
                    String[] s2eparate3E = separateF[7].split("D");
                    String[] s2eparate3D = s2eparate3E[1].split("C");
                    String[] s2eparate3C = s2eparate3D[1].split("B");
                    String[] s2eparate3B = s2eparate3C[1].split("A");
                    String[] s2eparate3A = s2eparate3B[1].split("V");
                    String[] s2eparate3V = s2eparate3A[1].split("U");
                    String[] s2eparate3U = s2eparate3V[1].split("T");
                    String[] s2eparate3T = s2eparate3U[1].split("S");
                    String[] s2eparate3S = s2eparate3T[1].split("R");
                    String[] s2eparate3R = s2eparate3S[1].split("Q");
                    String[] s2eparate3Q = s2eparate3R[1].split("P");
                    String[] s2eparate3P = s2eparate3Q[1].split("O");
                    String[] s2eparate3O = s2eparate3P[1].split("N");
                    String[] s2eparate3N = s2eparate3O[1].split("M");
                    String[] s2eparate3M = s2eparate3N[1].split("F");
                    String[] s2eparate4F = s2eparate3M[1].split("E");
                    String[] s2eparate4E = separateF[8].split("D");
                    String[] s2eparate4D = s2eparate4E[1].split("C");
                    String[] s2eparate4C = s2eparate4D[1].split("B");
                    String[] s2eparate4B = s2eparate4C[1].split("A");
                    String[] s2eparate4A = s2eparate4B[1].split("V");
                    String[] s2eparate4V = s2eparate4A[1].split("U");
                    String[] s2eparate4U = s2eparate4V[1].split("T");
                    String[] s2eparate4T = s2eparate4U[1].split("S");
                    String[] s2eparate4S = s2eparate4T[1].split("R");
                    String[] s2eparate4R = s2eparate4S[1].split("Q");
                    String[] s2eparate4Q = s2eparate4R[1].split("P");
                    String[] s2eparate4P = s2eparate4Q[1].split("O");
                    String[] s2eparate4O = s2eparate4P[1].split("N");
                    String[] s2eparate4N = s2eparate4O[1].split("M");
                    String[] s2eparate4M = s2eparate4N[1].split("F");
                    String[] s3eparateF = s2eparate4M[1].split("E");
                    String[] s3eparateE = separateF[9].split("D");
                    String[] s3eparateD = s3eparateE[1].split("C");
                    String[] s3eparateC = s3eparateD[1].split("B");
                    String[] s3eparateB = s3eparateC[1].split("A");
                    String[] s3eparateA = s3eparateB[1].split("V");
                    String[] s3eparateV = s3eparateA[1].split("U");
                    String[] s3eparateU = s3eparateV[1].split("T");
                    String[] s3eparateT = s3eparateU[1].split("S");
                    String[] s3eparateS = s3eparateT[1].split("R");
                    String[] s3eparateR = s3eparateS[1].split("Q");
                    String[] s3eparateQ = s3eparateR[1].split("P");
                    String[] s3eparateP = s3eparateQ[1].split("O");
                    String[] s3eparateO = s3eparateP[1].split("N");
                    String[] s3eparateN = s3eparateO[1].split("M");
                    String[] s3eparateM = s3eparateN[1].split("F");
                    String[] s3eparate2F = s3eparateM[1].split("E");
                    String[] s3eparate2E = separateF[10].split("D");
                    String[] s3eparate2D = s3eparate2E[1].split("C");
                    String[] s3eparate2C = s3eparate2D[1].split("B");
                    String[] s3eparate2B = s3eparate2C[1].split("A");
                    String[] s3eparate2A = s3eparate2B[1].split("V");
                    String[] s3eparate2V = s3eparate2A[1].split("U");
                    String[] s3eparate2U = s3eparate2V[1].split("T");
                    String[] s3eparate2T = s3eparate2U[1].split("S");
                    String[] s3eparate2S = s3eparate2T[1].split("R");
                    String[] s3eparate2R = s3eparate2S[1].split("Q");
                    String[] s3eparate2Q = s3eparate2R[1].split("P");
                    String[] s3eparate2P = s3eparate2Q[1].split("O");
                    String[] s3eparate2O = s3eparate2P[1].split("N");
                    String[] s3eparate2N = s3eparate2O[1].split("M");
                    String[] s3eparate2M = s3eparate2N[1].split("F");
                    String[] s3eparate3F = s3eparate2M[1].split("E");
                    String[] s3eparate3E = separateF[11].split("D");
                    String[] s3eparate3D = s3eparate3E[1].split("C");
                    String[] s3eparate3C = s3eparate3D[1].split("B");
                    String[] s3eparate3B = s3eparate3C[1].split("A");
                    String[] s3eparate3A = s3eparate3B[1].split("V");
                    String[] s3eparate3V = s3eparate3A[1].split("U");
                    String[] s3eparate3U = s3eparate3V[1].split("T");
                    String[] s3eparate3T = s3eparate3U[1].split("S");
                    String[] s3eparate3S = s3eparate3T[1].split("R");
                    String[] s3eparate3R = s3eparate3S[1].split("Q");
                    String[] s3eparate3Q = s3eparate3R[1].split("P");
                    String[] s3eparate3P = s3eparate3Q[1].split("O");
                    String[] s3eparate3O = s3eparate3P[1].split("N");
                    String[] s3eparate3N = s3eparate3O[1].split("M");
                    String[] s3eparate3M = s3eparate3N[1].split("F");
                    String[] s3eparate4F = s3eparate3M[1].split("E");
                    String[] s3eparate4E = separateF[12].split("D");
                    String[] s3eparate4D = s3eparate4E[1].split("C");
                    String[] s3eparate4C = s3eparate4D[1].split("B");
                    String[] s3eparate4B = s3eparate4C[1].split("A");
                    String[] s3eparate4A = s3eparate4B[1].split("V");
                    String[] s3eparate4V = s3eparate4A[1].split("U");
                    String[] s3eparate4U = s3eparate4V[1].split("T");
                    String[] s3eparate4T = s3eparate4U[1].split("S");
                    String[] s3eparate4S = s3eparate4T[1].split("R");
                    String[] s3eparate4R = s3eparate4S[1].split("Q");
                    String[] s3eparate4Q = s3eparate4R[1].split("P");
                    String[] s3eparate4P = s3eparate4Q[1].split("O");
                    String[] s3eparate4O = s3eparate4P[1].split("N");
                    String[] s3eparate4N = s3eparate4O[1].split("M");
                    String[] s3eparate4M = s3eparate4N[1].split("F");
                    String[] s4eparateF = s3eparate4M[1].split("E");
                    String[] s4eparateE = separateF[13].split("D");
                    String[] s4eparateD = s4eparateE[1].split("C");
                    String[] s4eparateC = s4eparateD[1].split("B");
                    String[] s4eparateB = s4eparateC[1].split("A");
                    String[] s4eparateA = s4eparateB[1].split("V");
                    String[] s4eparateV = s4eparateA[1].split("U");
                    String[] s4eparateU = s4eparateV[1].split("T");
                    String[] s4eparateT = s4eparateU[1].split("S");
                    String[] s4eparateS = s4eparateT[1].split("R");
                    String[] s4eparateR = s4eparateS[1].split("Q");
                    String[] s4eparateQ = s4eparateR[1].split("P");
                    String[] s4eparateP = s4eparateQ[1].split("O");
                    String[] s4eparateO = s4eparateP[1].split("N");
                    String[] s4eparateN = s4eparateO[1].split("M");
                    String[] s4eparateM = s4eparateN[1].split("F");
                    String[] s4eparate2F = s4eparateM[1].split("E");
                    String[] s4eparate2E = separateF[14].split("D");
                    String[] s4eparate2D = s4eparate2E[1].split("C");
                    String[] s4eparate2C = s4eparate2D[1].split("B");
                    String[] s4eparate2B = s4eparate2C[1].split("A");
                    String[] s4eparate2A = s4eparate2B[1].split("V");
                    String[] s4eparate2V = s4eparate2A[1].split("U");
                    String[] s4eparate2U = s4eparate2V[1].split("T");
                    String[] s4eparate2T = s4eparate2U[1].split("S");
                    String[] s4eparate2S = s4eparate2T[1].split("R");
                    String[] s4eparate2R = s4eparate2S[1].split("Q");
                    String[] s4eparate2Q = s4eparate2R[1].split("P");
                    String[] s4eparate2P = s4eparate2Q[1].split("O");
                    String[] s4eparate2O = s4eparate2P[1].split("N");
                    String[] s4eparate2N = s4eparate2O[1].split("M");
                    String[] s4eparate2M = s4eparate2N[1].split("F");
                    String[] s4eparate3F = s4eparate2M[1].split("E");
                    String[] s4eparate3E = separateF[15].split("D");
                    String[] s4eparate3D = s4eparate3E[1].split("C");
                    String[] s4eparate3C = s4eparate3D[1].split("B");
                    String[] s4eparate3B = s4eparate3C[1].split("A");
                    String[] s4eparate3A = s4eparate3B[1].split("V");
                    String[] s4eparate3V = s4eparate3A[1].split("U");
                    String[] s4eparate3U = s4eparate3V[1].split("T");
                    String[] s4eparate3T = s4eparate3U[1].split("S");
                    String[] s4eparate3S = s4eparate3T[1].split("R");
                    String[] s4eparate3R = s4eparate3S[1].split("Q");
                    String[] s4eparate3Q = s4eparate3R[1].split("P");
                    String[] s4eparate3P = s4eparate3Q[1].split("O");
                    String[] s4eparate3O = s4eparate3P[1].split("N");
                    String[] s4eparate3N = s4eparate3O[1].split("M");
                    String[] s4eparate3M = s4eparate3N[1].split("F");
                    String[] s4eparate4F = s4eparate3M[1].split("E");
                    String[] s4eparate4E = separateF[16].split("D");
                    String[] s4eparate4D = s4eparate4E[1].split("C");
                    String[] s4eparate4C = s4eparate4D[1].split("B");
                    String[] s4eparate4B = s4eparate4C[1].split("A");
                    String[] s4eparate4A = s4eparate4B[1].split("V");
                    String[] s4eparate4V = s4eparate4A[1].split("U");
                    String[] s4eparate4U = s4eparate4V[1].split("T");
                    String[] s4eparate4T = s4eparate4U[1].split("S");
                    String[] s4eparate4S = s4eparate4T[1].split("R");
                    String[] s4eparate4R = s4eparate4S[1].split("Q");
                    String[] s4eparate4Q = s4eparate4R[1].split("P");
                    String[] s4eparate4P = s4eparate4Q[1].split("O");
                    String[] s4eparate4O = s4eparate4P[1].split("N");
                    String[] s4eparate4N = s4eparate4O[1].split("M");
                    String[] s4eparate4M = s4eparate4N[1].split("W");
                    try {
                        MainActivity16.Matrix[0][0] = Integer.parseInt(fin.trim());
                        MainActivity16.Matrix[0][1] = Integer.parseInt(separateE[0].trim());
                        MainActivity16.Matrix[0][2] = Integer.parseInt(separateD[0].trim());
                        MainActivity16.Matrix[0][3] = Integer.parseInt(separateC[0].trim());
                        MainActivity16.Matrix[1][0] = Integer.parseInt(separateB[0].trim());
                        MainActivity16.Matrix[1][1] = Integer.parseInt(separateA[0].trim());
                        MainActivity16.Matrix[1][2] = Integer.parseInt(separateV[0].trim());
                        MainActivity16.Matrix[1][3] = Integer.parseInt(separateU[0].trim());
                        MainActivity16.Matrix[2][0] = Integer.parseInt(separateT[0].trim());
                        MainActivity16.Matrix[2][1] = Integer.parseInt(separateS[0].trim());
                        MainActivity16.Matrix[2][2] = Integer.parseInt(separateR[0].trim());
                        MainActivity16.Matrix[2][3] = Integer.parseInt(separateQ[0].trim());
                        MainActivity16.Matrix[3][0] = Integer.parseInt(separateP[0].trim());
                        MainActivity16.Matrix[3][1] = Integer.parseInt(separateO[0].trim());
                        MainActivity16.Matrix[3][2] = Integer.parseInt(separateN[0].trim());
                        MainActivity16.Matrix[3][3] = Integer.parseInt(separateM[0].trim());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        MainActivity16.Matrix[0][4] = Integer.parseInt(separate2F[0]);
                        MainActivity16.Matrix[0][5] = Integer.parseInt(separate2E[0]);
                        MainActivity16.Matrix[0][6] = Integer.parseInt(separate2D[0]);
                        MainActivity16.Matrix[0][7] = Integer.parseInt(separate2C[0]);
                        MainActivity16.Matrix[1][4] = Integer.parseInt(separate2B[0]);
                        MainActivity16.Matrix[1][5] = Integer.parseInt(separate2A[0]);
                        MainActivity16.Matrix[1][6] = Integer.parseInt(separate2V[0]);
                        MainActivity16.Matrix[1][7] = Integer.parseInt(separate2U[0]);
                        MainActivity16.Matrix[2][4] = Integer.parseInt(separate2T[0]);
                        MainActivity16.Matrix[2][5] = Integer.parseInt(separate2S[0]);
                        MainActivity16.Matrix[2][6] = Integer.parseInt(separate2R[0]);
                        MainActivity16.Matrix[2][7] = Integer.parseInt(separate2Q[0]);
                        MainActivity16.Matrix[3][4] = Integer.parseInt(separate2P[0]);
                        MainActivity16.Matrix[3][5] = Integer.parseInt(separate2O[0]);
                        MainActivity16.Matrix[3][6] = Integer.parseInt(separate2N[0]);
                        MainActivity16.Matrix[3][7] = Integer.parseInt(separate2M[0]);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    try {
                        MainActivity16.Matrix[4][4] = Integer.parseInt(separate4F[0]);
                        MainActivity16.Matrix[4][5] = Integer.parseInt(separate4E[0]);
                        MainActivity16.Matrix[4][6] = Integer.parseInt(separate4D[0]);
                        MainActivity16.Matrix[4][7] = Integer.parseInt(separate4C[0]);
                        MainActivity16.Matrix[5][4] = Integer.parseInt(separate4B[0]);
                        MainActivity16.Matrix[5][5] = Integer.parseInt(separate4A[0]);
                        MainActivity16.Matrix[5][6] = Integer.parseInt(separate4V[0]);
                        MainActivity16.Matrix[5][7] = Integer.parseInt(separate4U[0]);
                        MainActivity16.Matrix[6][4] = Integer.parseInt(separate4T[0]);
                        MainActivity16.Matrix[6][5] = Integer.parseInt(separate4S[0]);
                        MainActivity16.Matrix[6][6] = Integer.parseInt(separate4R[0]);
                        MainActivity16.Matrix[6][7] = Integer.parseInt(separate4Q[0]);
                        MainActivity16.Matrix[7][4] = Integer.parseInt(separate4P[0]);
                        MainActivity16.Matrix[7][5] = Integer.parseInt(separate4O[0]);
                        MainActivity16.Matrix[7][6] = Integer.parseInt(separate4N[0]);
                        MainActivity16.Matrix[7][7] = Integer.parseInt(separate4M[0]);
                    } catch (Exception e22) {
                        e22.printStackTrace();
                    }
                    try {
                        MainActivity16.Matrix[4][0] = Integer.parseInt(separate3F[0]);
                        MainActivity16.Matrix[4][1] = Integer.parseInt(separate3E[0]);
                        MainActivity16.Matrix[4][2] = Integer.parseInt(separate3D[0]);
                        MainActivity16.Matrix[4][3] = Integer.parseInt(separate3C[0]);
                        MainActivity16.Matrix[5][0] = Integer.parseInt(separate3B[0]);
                        MainActivity16.Matrix[5][1] = Integer.parseInt(separate3A[0]);
                        MainActivity16.Matrix[5][2] = Integer.parseInt(separate3V[0]);
                        MainActivity16.Matrix[5][3] = Integer.parseInt(separate3U[0]);
                        MainActivity16.Matrix[6][0] = Integer.parseInt(separate3T[0]);
                        MainActivity16.Matrix[6][1] = Integer.parseInt(separate3S[0]);
                        MainActivity16.Matrix[6][2] = Integer.parseInt(separate3R[0]);
                        MainActivity16.Matrix[6][3] = Integer.parseInt(separate3Q[0]);
                        MainActivity16.Matrix[7][0] = Integer.parseInt(separate3P[0]);
                        MainActivity16.Matrix[7][1] = Integer.parseInt(separate3O[0]);
                        MainActivity16.Matrix[7][2] = Integer.parseInt(separate3N[0]);
                        MainActivity16.Matrix[7][3] = Integer.parseInt(separate3M[0]);
                    } catch (Exception e222) {
                        e222.printStackTrace();
                    }
                    try {
                        MainActivity16.Matrix[0][8] = Integer.parseInt(s2eparateF[0]);
                        MainActivity16.Matrix[0][9] = Integer.parseInt(s2eparateE[0]);
                        MainActivity16.Matrix[0][10] = Integer.parseInt(s2eparateD[0]);
                        MainActivity16.Matrix[0][11] = Integer.parseInt(s2eparateC[0]);
                        MainActivity16.Matrix[1][8] = Integer.parseInt(s2eparateB[0]);
                        MainActivity16.Matrix[1][9] = Integer.parseInt(s2eparateA[0]);
                        MainActivity16.Matrix[1][10] = Integer.parseInt(s2eparateV[0]);
                        MainActivity16.Matrix[1][11] = Integer.parseInt(s2eparateU[0]);
                        MainActivity16.Matrix[2][8] = Integer.parseInt(s2eparateT[0]);
                        MainActivity16.Matrix[2][9] = Integer.parseInt(s2eparateS[0]);
                        MainActivity16.Matrix[2][10] = Integer.parseInt(s2eparateR[0]);
                        MainActivity16.Matrix[2][11] = Integer.parseInt(s2eparateQ[0]);
                        MainActivity16.Matrix[3][8] = Integer.parseInt(s2eparateP[0]);
                        MainActivity16.Matrix[3][9] = Integer.parseInt(s2eparateO[0]);
                        MainActivity16.Matrix[3][10] = Integer.parseInt(s2eparateN[0]);
                        MainActivity16.Matrix[3][11] = Integer.parseInt(s2eparateM[0]);
                    } catch (Exception e2222) {
                        e2222.printStackTrace();
                    }
                    try {
                        MainActivity16.Matrix[0][12] = Integer.parseInt(s2eparate2F[0]);
                        MainActivity16.Matrix[0][13] = Integer.parseInt(s2eparate2E[0].trim());
                        MainActivity16.Matrix[0][14] = Integer.parseInt(s2eparate2D[0].trim());
                        MainActivity16.Matrix[0][15] = Integer.parseInt(s2eparate2C[0].trim());
                        MainActivity16.Matrix[1][12] = Integer.parseInt(s2eparate2B[0].trim());
                        MainActivity16.Matrix[1][13] = Integer.parseInt(s2eparate2A[0].trim());
                        MainActivity16.Matrix[1][14] = Integer.parseInt(s2eparate2V[0].trim());
                        MainActivity16.Matrix[1][15] = Integer.parseInt(s2eparate2U[0].trim());
                        MainActivity16.Matrix[2][12] = Integer.parseInt(s2eparate2T[0].trim());
                        MainActivity16.Matrix[2][13] = Integer.parseInt(s2eparate2S[0].trim());
                        MainActivity16.Matrix[2][14] = Integer.parseInt(s2eparate2R[0].trim());
                        MainActivity16.Matrix[2][15] = Integer.parseInt(s2eparate2Q[0].trim());
                        MainActivity16.Matrix[3][12] = Integer.parseInt(s2eparate2P[0].trim());
                        MainActivity16.Matrix[3][13] = Integer.parseInt(s2eparate2O[0].trim());
                        MainActivity16.Matrix[3][14] = Integer.parseInt(s2eparate2N[0].trim());
                        MainActivity16.Matrix[3][15] = Integer.parseInt(s2eparate2M[0].trim());
                    } catch (Exception e22222) {
                        e22222.printStackTrace();
                    }
                    try {
                        MainActivity16.Matrix[4][8] = Integer.parseInt(s2eparate3F[0]);
                        MainActivity16.Matrix[4][9] = Integer.parseInt(s2eparate3E[0]);
                        MainActivity16.Matrix[4][10] = Integer.parseInt(s2eparate3D[0]);
                        MainActivity16.Matrix[4][11] = Integer.parseInt(s2eparate3C[0]);
                        MainActivity16.Matrix[5][8] = Integer.parseInt(s2eparate3B[0]);
                        MainActivity16.Matrix[5][9] = Integer.parseInt(s2eparate3A[0]);
                        MainActivity16.Matrix[5][10] = Integer.parseInt(s2eparate3V[0]);
                        MainActivity16.Matrix[5][11] = Integer.parseInt(s2eparate3U[0]);
                        MainActivity16.Matrix[6][8] = Integer.parseInt(s2eparate3T[0]);
                        MainActivity16.Matrix[6][9] = Integer.parseInt(s2eparate3S[0]);
                        MainActivity16.Matrix[6][10] = Integer.parseInt(s2eparate3R[0]);
                        MainActivity16.Matrix[6][11] = Integer.parseInt(s2eparate3Q[0]);
                        MainActivity16.Matrix[7][8] = Integer.parseInt(s2eparate3P[0]);
                        MainActivity16.Matrix[7][9] = Integer.parseInt(s2eparate3O[0]);
                        MainActivity16.Matrix[7][10] = Integer.parseInt(s2eparate3N[0]);
                        MainActivity16.Matrix[7][11] = Integer.parseInt(s2eparate3M[0]);
                    } catch (Exception e222222) {
                        e222222.printStackTrace();
                    }
                    try {
                        MainActivity16.Matrix[4][12] = Integer.parseInt(s2eparate4F[0]);
                        MainActivity16.Matrix[4][13] = Integer.parseInt(s2eparate4E[0]);
                        MainActivity16.Matrix[4][14] = Integer.parseInt(s2eparate4D[0]);
                        MainActivity16.Matrix[4][15] = Integer.parseInt(s2eparate4C[0]);
                        MainActivity16.Matrix[5][12] = Integer.parseInt(s2eparate4B[0]);
                        MainActivity16.Matrix[5][13] = Integer.parseInt(s2eparate4A[0]);
                        MainActivity16.Matrix[5][14] = Integer.parseInt(s2eparate4V[0]);
                        MainActivity16.Matrix[5][15] = Integer.parseInt(s2eparate4U[0]);
                        MainActivity16.Matrix[6][12] = Integer.parseInt(s2eparate4T[0]);
                        MainActivity16.Matrix[6][13] = Integer.parseInt(s2eparate4S[0]);
                        MainActivity16.Matrix[6][14] = Integer.parseInt(s2eparate4R[0]);
                        MainActivity16.Matrix[6][15] = Integer.parseInt(s2eparate4Q[0]);
                        MainActivity16.Matrix[7][12] = Integer.parseInt(s2eparate4P[0]);
                        MainActivity16.Matrix[7][13] = Integer.parseInt(s2eparate4O[0]);
                        MainActivity16.Matrix[7][14] = Integer.parseInt(s2eparate4N[0]);
                        MainActivity16.Matrix[7][15] = Integer.parseInt(s2eparate4M[0]);
                    } catch (Exception e2222222) {
                        e2222222.printStackTrace();
                    }
                    try {
                        MainActivity16.Matrix[8][0] = Integer.parseInt(s3eparateF[0]);
                        MainActivity16.Matrix[8][1] = Integer.parseInt(s3eparateE[0]);
                        MainActivity16.Matrix[8][2] = Integer.parseInt(s3eparateD[0]);
                        MainActivity16.Matrix[8][3] = Integer.parseInt(s3eparateC[0]);
                        MainActivity16.Matrix[9][0] = Integer.parseInt(s3eparateB[0]);
                        MainActivity16.Matrix[9][1] = Integer.parseInt(s3eparateA[0]);
                        MainActivity16.Matrix[9][2] = Integer.parseInt(s3eparateV[0]);
                        MainActivity16.Matrix[9][3] = Integer.parseInt(s3eparateU[0]);
                        MainActivity16.Matrix[10][0] = Integer.parseInt(s3eparateT[0]);
                        MainActivity16.Matrix[10][1] = Integer.parseInt(s3eparateS[0]);
                        MainActivity16.Matrix[10][2] = Integer.parseInt(s3eparateR[0]);
                        MainActivity16.Matrix[10][3] = Integer.parseInt(s3eparateQ[0]);
                        MainActivity16.Matrix[11][0] = Integer.parseInt(s3eparateP[0]);
                        MainActivity16.Matrix[11][1] = Integer.parseInt(s3eparateO[0]);
                        MainActivity16.Matrix[11][2] = Integer.parseInt(s3eparateN[0]);
                        MainActivity16.Matrix[11][3] = Integer.parseInt(s3eparateM[0]);
                    } catch (Exception e22222222) {
                        e22222222.printStackTrace();
                    }
                    try {
                        MainActivity16.Matrix[8][4] = Integer.parseInt(s3eparate2F[0]);
                        MainActivity16.Matrix[8][5] = Integer.parseInt(s3eparate2E[0].trim());
                        MainActivity16.Matrix[8][6] = Integer.parseInt(s3eparate2D[0].trim());
                        MainActivity16.Matrix[8][7] = Integer.parseInt(s3eparate2C[0].trim());
                        MainActivity16.Matrix[9][4] = Integer.parseInt(s3eparate2B[0].trim());
                        MainActivity16.Matrix[9][5] = Integer.parseInt(s3eparate2A[0].trim());
                        MainActivity16.Matrix[9][6] = Integer.parseInt(s3eparate2V[0].trim());
                        MainActivity16.Matrix[9][7] = Integer.parseInt(s3eparate2U[0].trim());
                        MainActivity16.Matrix[10][4] = Integer.parseInt(s3eparate2T[0].trim());
                        MainActivity16.Matrix[10][5] = Integer.parseInt(s3eparate2S[0].trim());
                        MainActivity16.Matrix[10][6] = Integer.parseInt(s3eparate2R[0].trim());
                        MainActivity16.Matrix[10][7] = Integer.parseInt(s3eparate2Q[0].trim());
                        MainActivity16.Matrix[11][4] = Integer.parseInt(s3eparate2P[0].trim());
                        MainActivity16.Matrix[11][5] = Integer.parseInt(s3eparate2O[0].trim());
                        MainActivity16.Matrix[11][6] = Integer.parseInt(s3eparate2N[0].trim());
                        MainActivity16.Matrix[11][7] = Integer.parseInt(s3eparate2M[0].trim());
                    } catch (Exception e222222222) {
                        e222222222.printStackTrace();
                    }
                    try {
                        MainActivity16.Matrix[12][0] = Integer.parseInt(s3eparate3F[0]);
                        MainActivity16.Matrix[12][1] = Integer.parseInt(s3eparate3E[0]);
                        MainActivity16.Matrix[12][2] = Integer.parseInt(s3eparate3D[0]);
                        MainActivity16.Matrix[12][3] = Integer.parseInt(s3eparate3C[0]);
                        MainActivity16.Matrix[13][0] = Integer.parseInt(s3eparate3B[0]);
                        MainActivity16.Matrix[13][1] = Integer.parseInt(s3eparate3A[0]);
                        MainActivity16.Matrix[13][2] = Integer.parseInt(s3eparate3V[0]);
                        MainActivity16.Matrix[13][3] = Integer.parseInt(s3eparate3U[0]);
                        MainActivity16.Matrix[14][0] = Integer.parseInt(s3eparate3T[0]);
                        MainActivity16.Matrix[14][1] = Integer.parseInt(s3eparate3S[0]);
                        MainActivity16.Matrix[14][2] = Integer.parseInt(s3eparate3R[0]);
                        MainActivity16.Matrix[14][3] = Integer.parseInt(s3eparate3Q[0]);
                        MainActivity16.Matrix[15][0] = Integer.parseInt(s3eparate3P[0]);
                        MainActivity16.Matrix[15][1] = Integer.parseInt(s3eparate3O[0]);
                        MainActivity16.Matrix[15][2] = Integer.parseInt(s3eparate3N[0]);
                        MainActivity16.Matrix[15][3] = Integer.parseInt(s3eparate3M[0]);
                    } catch (Exception e2222222222) {
                        e2222222222.printStackTrace();
                    }
                    try {
                        MainActivity16.Matrix[12][4] = Integer.parseInt(s3eparate4F[0]);
                        MainActivity16.Matrix[12][5] = Integer.parseInt(s3eparate4E[0]);
                        MainActivity16.Matrix[12][6] = Integer.parseInt(s3eparate4D[0]);
                        MainActivity16.Matrix[12][7] = Integer.parseInt(s3eparate4C[0]);
                        MainActivity16.Matrix[13][4] = Integer.parseInt(s3eparate4B[0]);
                        MainActivity16.Matrix[13][5] = Integer.parseInt(s3eparate4A[0]);
                        MainActivity16.Matrix[13][6] = Integer.parseInt(s3eparate4V[0]);
                        MainActivity16.Matrix[13][7] = Integer.parseInt(s3eparate4U[0]);
                        MainActivity16.Matrix[14][4] = Integer.parseInt(s3eparate4T[0]);
                        MainActivity16.Matrix[14][5] = Integer.parseInt(s3eparate4S[0]);
                        MainActivity16.Matrix[14][6] = Integer.parseInt(s3eparate4R[0]);
                        MainActivity16.Matrix[14][7] = Integer.parseInt(s3eparate4Q[0]);
                        MainActivity16.Matrix[15][4] = Integer.parseInt(s3eparate4P[0]);
                        MainActivity16.Matrix[15][5] = Integer.parseInt(s3eparate4O[0]);
                        MainActivity16.Matrix[15][6] = Integer.parseInt(s3eparate4N[0]);
                        MainActivity16.Matrix[15][7] = Integer.parseInt(s3eparate4M[0]);
                    } catch (Exception e22222222222) {
                        e22222222222.printStackTrace();
                    }
                    try {
                        MainActivity16.Matrix[8][8] = Integer.parseInt(s4eparateF[0]);
                        MainActivity16.Matrix[8][9] = Integer.parseInt(s4eparateE[0]);
                        MainActivity16.Matrix[8][10] = Integer.parseInt(s4eparateD[0]);
                        MainActivity16.Matrix[8][11] = Integer.parseInt(s4eparateC[0]);
                        MainActivity16.Matrix[9][8] = Integer.parseInt(s4eparateB[0]);
                        MainActivity16.Matrix[9][9] = Integer.parseInt(s4eparateA[0]);
                        MainActivity16.Matrix[9][10] = Integer.parseInt(s4eparateV[0]);
                        MainActivity16.Matrix[9][11] = Integer.parseInt(s4eparateU[0]);
                        MainActivity16.Matrix[10][8] = Integer.parseInt(s4eparateT[0]);
                        MainActivity16.Matrix[10][9] = Integer.parseInt(s4eparateS[0]);
                        MainActivity16.Matrix[10][10] = Integer.parseInt(s4eparateR[0]);
                        MainActivity16.Matrix[10][11] = Integer.parseInt(s4eparateQ[0]);
                        MainActivity16.Matrix[11][8] = Integer.parseInt(s4eparateP[0]);
                        MainActivity16.Matrix[11][9] = Integer.parseInt(s4eparateO[0]);
                        MainActivity16.Matrix[11][10] = Integer.parseInt(s4eparateN[0]);
                        MainActivity16.Matrix[11][11] = Integer.parseInt(s4eparateM[0]);
                    } catch (Exception e222222222222) {
                        e222222222222.printStackTrace();
                    }
                    try {
                        MainActivity16.Matrix[8][12] = Integer.parseInt(s4eparate2F[0]);
                        MainActivity16.Matrix[8][13] = Integer.parseInt(s4eparate2E[0].trim());
                        MainActivity16.Matrix[8][14] = Integer.parseInt(s4eparate2D[0].trim());
                        MainActivity16.Matrix[8][15] = Integer.parseInt(s4eparate2C[0].trim());
                        MainActivity16.Matrix[9][12] = Integer.parseInt(s4eparate2B[0].trim());
                        MainActivity16.Matrix[9][13] = Integer.parseInt(s4eparate2A[0].trim());
                        MainActivity16.Matrix[9][14] = Integer.parseInt(s4eparate2V[0].trim());
                        MainActivity16.Matrix[9][15] = Integer.parseInt(s4eparate2U[0].trim());
                        MainActivity16.Matrix[10][12] = Integer.parseInt(s4eparate2T[0].trim());
                        MainActivity16.Matrix[10][13] = Integer.parseInt(s4eparate2S[0].trim());
                        MainActivity16.Matrix[10][14] = Integer.parseInt(s4eparate2R[0].trim());
                        MainActivity16.Matrix[10][15] = Integer.parseInt(s4eparate2Q[0].trim());
                        MainActivity16.Matrix[11][12] = Integer.parseInt(s4eparate2P[0].trim());
                        MainActivity16.Matrix[11][13] = Integer.parseInt(s4eparate2O[0].trim());
                        MainActivity16.Matrix[11][14] = Integer.parseInt(s4eparate2N[0].trim());
                        MainActivity16.Matrix[11][15] = Integer.parseInt(s4eparate2M[0].trim());
                    } catch (Exception e2222222222222) {
                        e2222222222222.printStackTrace();
                    }
                    try {
                        MainActivity16.Matrix[12][8] = Integer.parseInt(s4eparate3F[0]);
                        MainActivity16.Matrix[12][9] = Integer.parseInt(s4eparate3E[0]);
                        MainActivity16.Matrix[12][10] = Integer.parseInt(s4eparate3D[0]);
                        MainActivity16.Matrix[12][11] = Integer.parseInt(s4eparate3C[0]);
                        MainActivity16.Matrix[13][8] = Integer.parseInt(s4eparate3B[0]);
                        MainActivity16.Matrix[13][9] = Integer.parseInt(s4eparate3A[0]);
                        MainActivity16.Matrix[13][10] = Integer.parseInt(s4eparate3V[0]);
                        MainActivity16.Matrix[13][11] = Integer.parseInt(s4eparate3U[0]);
                        MainActivity16.Matrix[14][8] = Integer.parseInt(s4eparate3T[0]);
                        MainActivity16.Matrix[14][9] = Integer.parseInt(s4eparate3S[0]);
                        MainActivity16.Matrix[14][10] = Integer.parseInt(s4eparate3R[0]);
                        MainActivity16.Matrix[14][11] = Integer.parseInt(s4eparate3Q[0]);
                        MainActivity16.Matrix[15][8] = Integer.parseInt(s4eparate3P[0]);
                        MainActivity16.Matrix[15][9] = Integer.parseInt(s4eparate3O[0]);
                        MainActivity16.Matrix[15][10] = Integer.parseInt(s4eparate3N[0]);
                        MainActivity16.Matrix[15][11] = Integer.parseInt(s4eparate3M[0]);
                    } catch (Exception e22222222222222) {
                        e22222222222222.printStackTrace();
                    }
                    try {
                        MainActivity16.Matrix[12][12] = Integer.parseInt(s4eparate4F[0]);
                        MainActivity16.Matrix[12][13] = Integer.parseInt(s4eparate4E[0]);
                        MainActivity16.Matrix[12][14] = Integer.parseInt(s4eparate4D[0]);
                        MainActivity16.Matrix[12][15] = Integer.parseInt(s4eparate4C[0]);
                        MainActivity16.Matrix[13][12] = Integer.parseInt(s4eparate4B[0]);
                        MainActivity16.Matrix[13][13] = Integer.parseInt(s4eparate4A[0]);
                        MainActivity16.Matrix[13][14] = Integer.parseInt(s4eparate4V[0]);
                        MainActivity16.Matrix[13][15] = Integer.parseInt(s4eparate4U[0]);
                        MainActivity16.Matrix[14][12] = Integer.parseInt(s4eparate4T[0]);
                        MainActivity16.Matrix[14][13] = Integer.parseInt(s4eparate4S[0]);
                        MainActivity16.Matrix[14][14] = Integer.parseInt(s4eparate4R[0]);
                        MainActivity16.Matrix[14][15] = Integer.parseInt(s4eparate4Q[0]);
                        MainActivity16.Matrix[15][12] = Integer.parseInt(s4eparate4P[0]);
                        MainActivity16.Matrix[15][13] = Integer.parseInt(s4eparate4O[0]);
                        MainActivity16.Matrix[15][14] = Integer.parseInt(s4eparate4N[0]);
                        MainActivity16.Matrix[15][15] = Integer.parseInt(s4eparate4M[0]);
                    } catch (Exception e222222222222222) {
                        e222222222222222.printStackTrace();
                    }
                    double Mx = 0.0d;
                    double My = 0.0d;
                    double Mass = 0.0d;
                    int[] CenterOfGravity = new int[2];
                    for (i = 0; i < 16; i++) {
                        for (j = 0; j < 16; j++) {
                            if (MainActivity16.Matrix[i][j] > MainActivity16.this.threshold) {
                                Mx += (double) ((i + 1) * MainActivity16.Matrix[i][j]);
                                My += (double) ((j + 1) * MainActivity16.Matrix[i][j]);
                                Mass += (double) MainActivity16.Matrix[i][j];
                                CenterOfGravity[0] = (int) (Mx / Mass);
                                CenterOfGravity[1] = (int) (My / Mass);
                            }
                        }
                    }
                    if (MainActivity16.this.Comp) {
                        for (j = 0; j < 16; j++) {
                            tensionNumerator = 1024;
                            for (i = 0; i < 16; i++) {
                                if (MainActivity16.Matrix[j][i] > 0) {
                                    tensionNumerator -= MainActivity16.Matrix[j][i];
                                }
                            }
                            System.out.println("tension:" + tensionNumerator);
                            for (i = 0; i < 16; i++) {
                                if (MainActivity16.Matrix[j][i] > 0) {
                                    double Vin = 1024000.0d / ((double) (((tensionNumerator * 1000) / MainActivity16.Matrix[j][i]) + 1000));
                                    MainActivity16.Matrix[j][i] = (int) Vin;
                                }
                            }
                        }
                    }
                    if (MainActivity16.this.showText) {
                        for (j = 0; j < 16; j++) {
                            for (i = 0; i < 16; i++) {
                                MainActivity16.this.button[i][j].setText(String.valueOf(MainActivity16.Matrix[i][j]));
                            }
                        }
                    } else {
                        for (j = 0; j < 16; j++) {
                            for (i = 0; i < 16; i++) {
                                MainActivity16.this.button[i][j].setText("");
                            }
                        }
                    }
                    if (MainActivity16.this.savedata) {
                        String hourc;
                        String minc;
                        String secc;
                        String dayc;
                        String monthc;
                        String yeahc;
                        Calendar c = Calendar.getInstance();
                        int hour = c.get(11);
                        int min = c.get(12);
                        int seconds = c.get(13);
                        int day = c.get(5);
                        int month = c.get(2);
                        int year = c.get(1);
                        if (hour < 10) {
                            hourc = new StringBuilder(String.valueOf('0')).append(Integer.toString(hour)).toString();
                        } else {
                            hourc = Integer.toString(hour);
                        }
                        if (min < 10) {
                            minc = new StringBuilder(String.valueOf('0')).append(Integer.toString(min)).toString();
                        } else {
                            minc = Integer.toString(min);
                        }
                        if (seconds < 10) {
                            secc = new StringBuilder(String.valueOf('0')).append(Integer.toString(seconds)).toString();
                        } else {
                            secc = Integer.toString(seconds);
                        }
                        if (day < 10) {
                            dayc = new StringBuilder(String.valueOf('0')).append(Integer.toString(day)).toString();
                        } else {
                            dayc = Integer.toString(day);
                        }
                        if (month < 10) {
                            monthc = new StringBuilder(String.valueOf('0')).append(Integer.toString(month)).toString();
                        } else {
                            monthc = Integer.toString(month);
                        }
                        if (year < 10) {
                            yeahc = new StringBuilder(String.valueOf('0')).append(Integer.toString(year)).toString();
                        } else {
                            yeahc = Integer.toString(year);
                        }
                        try {
                            MainActivity16.this.osw.append(new StringBuilder(String.valueOf(dayc)).append("/").append(monthc).append("/").append(yeahc).append(" ").append(hourc).append(":").append(minc).append(":").append(secc).append(",").toString());
                            for (i = 0; i < MainActivity16.Rows; i++) {
                                j = 0;
                                while (true) {
                                    if (j >= MainActivity16.Columns) {
                                        break;
                                    }
                                    MainActivity16.this.osw.append(new StringBuilder(String.valueOf(Integer.toString(MainActivity16.Matrix[i][j]))).append(",").toString());
                                    j++;
                                }
                            }
                            MainActivity16.this.osw.append("\r\n");
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                        System.out.println("Saved");
                        MainActivity16 mainActivity16 = MainActivity16.this;
                        mainActivity16.savecount++;
                        if (MainActivity16.this.savecount == 8) {
                            MainActivity16.this.save.setImageResource(C0156R.drawable.rec2off);
                        } else if (MainActivity16.this.savecount == 16) {
                            MainActivity16.this.save.setImageResource(C0156R.drawable.rec2);
                            MainActivity16.this.savecount = 0;
                        }
                    }
                    for (i = 0; i < 16; i++) {
                        for (j = 0; j < 16; j++) {
                            int[] iArr = MainActivity16.Matrix[i];
                            int[] iArr2 = iArr;
                            iArr2[j] = MainActivity16.Matrix[i][j] * MainActivity16.this.FILTER;
                            if (MainActivity16.Matrix[i][j] > 1020) {
                                MainActivity16.Matrix[i][j] = 1020;
                            }
                        }
                    }
                    i = 0;
                    while (i < 16) {
                        j = 0;
                        while (j < 16) {
                            if (MainActivity16.Matrix[i][j] <= MainActivity16.this.threshold * MainActivity16.this.FILTER) {
                                red[i][j] = 0;
                                blue[i][j] = 0;
                                green[i][j] = 0;
                            } else {
                                if (MainActivity16.Matrix[i][j] > MainActivity16.this.threshold * MainActivity16.this.FILTER && MainActivity16.Matrix[i][j] <= 255) {
                                    red[i][j] = 0;
                                    blue[i][j] = 255;
                                    green[i][j] = MainActivity16.Matrix[i][j];
                                } else if (MainActivity16.Matrix[i][j] > 255 && MainActivity16.Matrix[i][j] <= 510) {
                                    red[i][j] = 0;
                                    blue[i][j] = 510 - MainActivity16.Matrix[i][j];
                                    green[i][j] = 255;
                                } else if (MainActivity16.Matrix[i][j] > 510 && MainActivity16.Matrix[i][j] <= 765) {
                                    red[i][j] = MainActivity16.Matrix[i][j] - 510;
                                    blue[i][j] = 0;
                                    green[i][j] = 255;
                                } else if (MainActivity16.Matrix[i][j] > 765) {
                                    red[i][j] = 255;
                                    blue[i][j] = 0;
                                    green[i][j] = 1020 - MainActivity16.Matrix[i][j];
                                } else {
                                    red[i][j] = red[i][j];
                                    blue[i][j] = blue[i][j];
                                    green[i][j] = green[i][j];
                                }
                            }
                            j++;
                        }
                        i++;
                    }
                    for (i = 0; i < 16; i++) {
                        for (j = 0; j < 16; j++) {
                            MainActivity16.this.button[i][j].setBackgroundColor(Color.rgb(red[i][j], green[i][j], blue[i][j]));
                        }
                    }
                    if (MainActivity16.this.gravit) {
                        MainActivity16.this.gravity.setVisibility(0);
                        MainActivity16.this.gravity.setTranslationX((float) ((((MainActivity16.this.button[0][0].getWidth() - MainActivity16.this.gravity.getWidth()) * 1) / 2) + (MainActivity16.this.button[0][0].getWidth() * (CenterOfGravity[1] - 3))));
                        MainActivity16.this.gravity.setTranslationY((float) ((((MainActivity16.this.button[0][0].getWidth() - MainActivity16.this.gravity.getWidth()) * 4) / 9) + (MainActivity16.this.button[0][0].getWidth() * (CenterOfGravity[0] - 3))));
                        if (CenterOfGravity[0] == 0 && CenterOfGravity[1] == 0) {
                            MainActivity16.this.gravity.setTranslationX((float) ((((MainActivity16.this.button[0][0].getWidth() - MainActivity16.this.gravity.getWidth()) * 1) / 2) + (MainActivity16.this.button[0][0].getWidth() * 5)));
                            MainActivity16.this.gravity.setTranslationY((float) ((((MainActivity16.this.button[0][0].getWidth() - MainActivity16.this.gravity.getWidth()) * 4) / 9) + (MainActivity16.this.button[0][0].getWidth() * 5)));
                        }
                        Drawable gd;
                        if ((CenterOfGravity[0] == 0 && CenterOfGravity[1] == 0) || (CenterOfGravity[0] == 8 && CenterOfGravity[1] == 8)) {
                            gd = new GradientDrawable();
                            gd.setColor(Color.rgb(red[7][7], green[7][7], blue[7][7]));
                            gd.setCornerRadius(10.0f);
                            gd.setStroke(4, Color.rgb(0, 255, 0));
                            MainActivity16.this.button[7][7].setBackgroundDrawable(gd);
                            return;
                        }
                        gd = new GradientDrawable();
                        gd.setColor(Color.rgb(red[7][7], green[7][7], blue[7][7]));
                        gd.setCornerRadius(10.0f);
                        gd.setStroke(4, Color.rgb(255, 0, 0));
                        MainActivity16.this.button[7][7].setBackgroundDrawable(gd);
                        return;
                    }
                    MainActivity16.this.gravity.setVisibility(4);
                    return;
                }
                Toast.makeText(MainActivity16.this.getApplicationContext(), "Wrong sensor connected", 0).show();
                MainActivity16.this.mCommService.stop();
            }
        });
    }

    public void Provador() {
        sendMessage("1");
    }

    public void setTextViewTX(String text) {
        runOnUiThread(new Runnable() {
            public void run() {
            }
        });
    }

    public void setInitTextView(String text) {
        runOnUiThread(new Runnable() {
            public void run() {
            }
        });
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case C0156R.id.lv:
                this.mCommService.connect((BluetoothDevice) this.arrayAdapterPairDev.getItem(position));
                break;
            case C0156R.id.lvnewdevices:
                this.mCommService.connect((BluetoothDevice) this.arrayAdapterNewDev.getItem(position));
                break;
        }
        this.optionDialog.dismiss();
    }

    private void sendMessage(String message) {
        if (this.mCommService.getState() == 3 && message.length() > 0) {
            this.mCommService.write(message);
            this.mOutStringBuffer.setLength(0);
        }
    }

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        v.getId();
        return false;
    }
}

