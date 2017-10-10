package com.example.michael.technotreck_hw_01;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        new CountDownTimer(2000, 2000) {
            public void onTick(long millisUntilFinished) {
                // do nothing
            }

            public void onFinish() {
                startSecond();
            }
        }.start();
    }

    private void startSecond(){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
