package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
{

    private Button buttonStart, buttonStop, buttonReset, buttonResume;


    private long lastPause = 0; // used for memorize the time when the chronometer stopped.

    private Chronometer chronometer; // class for manage the chronometer.


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer = (Chronometer) findViewById(R.id.Chronometer);
        chronometer.setBase(SystemClock.elapsedRealtime());
    }



    /* Logic of the buttons:
    * buttonStart: when it's clicked, it will start the chronometer. Additionally it will show up the buttonStop and hide himself.
    * buttonStop: when it's clicked, it will stop the chronometer. Additionally it will show up the buttonResume and the buttonReset.
    * buttonResume: when it's clicked, it will restart the chronometer after the user clicked the buttonStop. Additionally it will show up the buttonStop and hide the buttonReset, himself.
    * buttonReset: when it's clicked, it will reset the chronometer. Additionally it will show up the buttonStart and hide the buttonResume and himself.
    * */



    public void onClickButtonReset(View view)
    {


        chronometer = (Chronometer) findViewById(R.id.Chronometer);

        chronometer.stop();

        chronometer.setBase(SystemClock.elapsedRealtime());

        buttonStart = (Button) findViewById(R.id.button_start);

        buttonStart.setVisibility(View.VISIBLE);

        buttonStop = (Button) findViewById(R.id.button_stop);

        buttonStop.setVisibility(View.GONE);

        buttonResume = (Button) findViewById(R.id.button_resume);

        buttonResume.setVisibility(View.GONE);

        buttonReset = (Button) findViewById(R.id.button_reset);

        buttonReset.setVisibility(View.GONE);
    }

    public void onClickButtonStop(View view)
    {


        chronometer = (Chronometer) findViewById(R.id.Chronometer);

        lastPause = SystemClock.elapsedRealtime();

        chronometer.stop();

        buttonStop = (Button) findViewById(R.id.button_stop);

        buttonStop.setVisibility(View.GONE);

        buttonResume = (Button) findViewById(R.id.button_resume);

        buttonResume.setVisibility(View.VISIBLE);

        buttonReset = (Button) findViewById(R.id.button_reset);

        buttonReset.setVisibility(View.VISIBLE);

    }

    public void onClickButtonStart(View view)
    {

        chronometer = (Chronometer) findViewById(R.id.Chronometer);

        chronometer.setBase(SystemClock.elapsedRealtime());

        chronometer.start();

        buttonStart = (Button) findViewById(R.id.button_start);

        buttonStart.setVisibility(View.GONE);

        buttonStop = (Button) findViewById(R.id.button_stop);

        buttonStop.setVisibility(View.VISIBLE);


    }



    public void onClickButtonResume(View view)
    {

        super.onResume();

        chronometer = (Chronometer) findViewById(R.id.Chronometer);

        chronometer.setBase(chronometer.getBase() + SystemClock.elapsedRealtime() - lastPause);

        chronometer.start();

        buttonStop = (Button) findViewById(R.id.button_stop);

        buttonStop.setVisibility(View.VISIBLE);

        buttonResume = (Button) findViewById(R.id.button_resume);

        buttonResume.setVisibility(View.GONE);

        buttonReset = (Button) findViewById(R.id.button_reset);

        buttonReset.setVisibility(View.GONE);


    }


}