package edu.cs.birzeit.myapplication2;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;


import android.os.Handler;
import android.view.View;

import android.widget.EditText;
import android.widget.TextView;


import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {
    private EditText entermins;
    private int seconds = 0;
    private boolean running = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        entermins=findViewById(R.id.entermins);
        checkSavedInstance(savedInstanceState);

        runTimer();

    }


    private void checkSavedInstance(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
    }

    //for changes phone orientation.
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
    }


    private void runTimer() {
        final TextView txtTimer = (TextView) findViewById(R.id.txtTimer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = seconds % 3600 / 60;
                int snds = seconds % 60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, snds);
                txtTimer.setText(time);
                if (running ) {
                    --seconds;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    public void onClickStart(View view)
    {
        seconds=Integer.parseInt(entermins.getText().toString());


        running = true;

    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickReset(View view) {
        seconds = 0;
        running = false;

    }



}