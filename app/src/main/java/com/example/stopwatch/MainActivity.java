package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
{

    private TextView textViewStopwatch;

    private Button buttonStart, buttonStop, buttonReset, buttonResume;

    private int seconds = 0;

    private boolean running, wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null)
        {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    @Override
    public void onSaveInstanceState(
            Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState
                .putInt("seconds", seconds);
        savedInstanceState
                .putBoolean("running", running);
        savedInstanceState
                .putBoolean("wasRunning", wasRunning);
    }




    public void onClickButtonReset(View view)
    {
        running = false;

        seconds = 0;
    }

    public void onClickButtonStop(View view)
    {
        wasRunning = running;
        running = false;
    }

    public void onClickButtonStart(View view)
    {
        running = true;

    }



    public void onClickButtonResume(View view)
    {

        super.onResume();
        if (wasRunning) {
            running = true;
        }

    }
    private void runTimer()
    {
        textViewStopwatch = findViewById(R.id.textView_stopwatch);

        Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {

                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String time = String.format(Locale.getDefault(),"%02d:%02d:%02d", hours, minutes, secs);

                textViewStopwatch.setText(time);

                if (running)
                {
                    seconds++;
                }

                handler.postDelayed(this, 1000);
            }
        });
    }

}