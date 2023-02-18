package com.binyam.timercountdown;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView countDownText;
    private Button countDownButton;

    private CountDownTimer countDownTimer;
    private long timeLeftInMilliSeconds = 600_000; //10 minutes
    private boolean isTimerRunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize our variables
        countDownText = findViewById(R.id.countdown_text);
        countDownButton = findViewById(R.id.countdown_button);

        countDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
            }
        });
        updateTimer();
    }

    public void startStop(){
        if(isTimerRunning){
            stopTimer();
        } else{
            startTimer();
        }

    }
    public void startTimer() {
        // a new object is instantiated with parameters of seconds left every 1000 milliseconds onTick is updated
        countDownTimer = new CountDownTimer(timeLeftInMilliSeconds,1000) {
            @Override
            public void onTick(long millisUntilFinished) {  // the remaining time left
                timeLeftInMilliSeconds = millisUntilFinished; //
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();

        countDownButton.setText("PAUSE");

        isTimerRunning = true;
    }
    public void stopTimer() {
        countDownTimer.cancel();

        countDownButton.setText("START");
        isTimerRunning = false;
    }
    public void updateTimer(){
        int minutes = (int) timeLeftInMilliSeconds / 60000;
        int seconds = (int) timeLeftInMilliSeconds % 60000 / 1000; // remaining modulus

        String timeLeftText;

        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if( seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        countDownText.setText(timeLeftText);


    }

}