package com.example.projects.ineedhelptesting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Intro extends Activity {
    private RadioButton PST01;
    private RadioButton PST02;
    private RadioButton PST03;
    private RadioButton PST12;
    private Button button;
    private int id;
    private RadioGroup radioGroup;

    class C01011 implements OnClickListener {
        C01011() {
        }

        public void onClick(View arg0) {
            int selectedId = Intro.this.radioGroup.getCheckedRadioButtonId();
                Intro.this.startActivity(new Intent(Intro.this, MainActivity16.class));

        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0156R.layout.intro_screen);
        getWindow().addFlags(128);
        this.radioGroup = (RadioGroup) findViewById(C0156R.id.radioGroup1);
        this.button = (Button) findViewById(C0156R.id.button2);
        this.PST01 = (RadioButton) findViewById(C0156R.id.PST01);
        this.PST12 = (RadioButton) findViewById(C0156R.id.PST12);
        this.PST02 = (RadioButton) findViewById(C0156R.id.PST02);
        this.PST03 = (RadioButton) findViewById(C0156R.id.PST03);
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setTitle("PST Dev Kit");
        this.button.setOnClickListener(new C01011());
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
