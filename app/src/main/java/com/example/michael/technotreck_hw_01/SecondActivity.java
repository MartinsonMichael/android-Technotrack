package com.example.michael.technotreck_hw_01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends Activity{

    private Integer _current_time;
    private Thread _timer;
    private Boolean _is_alive;
    private Button _but;
    private TextView _text;
    static final String STATE_TIME = "current_time";
    static final String STATE_DEAMON = "is_alive";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        _text = (TextView)findViewById(R.id.time_text);
        _but = (Button)findViewById(R.id.timer_button);
        _but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTimerButtonClick();
            }
        });
        _current_time = 1;
        _is_alive = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_TIME, _current_time);
        outState.putBoolean(STATE_DEAMON, _is_alive);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        _current_time = savedInstanceState.getInt(STATE_TIME);
        _is_alive = savedInstanceState.getBoolean(STATE_DEAMON);
        tryStartTimer();
        setButtonName();
    }

    private void tryStartTimer(){
        _timer = new Thread(new Runnable() {
            @Override
            public void run() {
                _current_time = 1;
                for (; _current_time < 1000; _current_time++){
                    if (_is_alive == false){
                        break;
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            onTimerTick();
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onTimerTick();
                    }
                });
            }
        });
        _timer.start();
    }

    private void onTimerButtonClick(){
        _is_alive = !_is_alive;
        setButtonName();
        tryStartTimer();
    }

    private void setButtonName(){
        if (_is_alive){
            _but.setText("Stop");
            tryStartTimer();
        }
        if (!_is_alive){
            _but.setText("Start");
        }
    }

    private void onTimerTick(){
        _text.setText(getRusStringFromNum(_current_time));
    }

    private static String getRusStringFromNum(Integer number){
        String ans = "";
        if (number % 1000 == 0){
            return "ноль";
        }
        switch (number  / 100){
            case 1:
                ans += "сто";
                break;
            case 2:
                ans += "двести";
                break;
            case 3:
                ans += "тристо";
                break;
            case 4:
                ans += "четыресто";
                break;
            case 5:
                ans += "пятьсот";
                break;
            case 6:
                ans += "шестьсот";
                break;
            case 7:
                ans += "семьсот";
                break;
            case 8:
                ans += "восемьсот";
                break;
            case 9:
                ans += "девятсот";
                break;
            default:
                ans += "";
        }
        if (ans.length() != 0){
            ans += " ";
        }
        switch ((number % 100) / 10){
            case 1:
                break;
            case 2:
                ans += "двадцать";
                break;
            case 3:
                ans += "тридцать";
                break;
            case 4:
                ans += "сорок";
                break;
            case 5:
                ans += "пятьдесят";
                break;
            case 6:
                ans += "шестьдесят";
                break;
            case 7:
                ans += "семьдесят";
                break;
            case 8:
                ans += "восемдесят";
                break;
            case 9:
                ans += "девяносто";
                break;
            default:
                ans += "";
        }
        if (ans.length() != 0){
            ans += " ";
        }
        if ((number % 100) / 10 == 1){
            switch (number % 100){
                case 10:
                    ans += "десять";
                    break;
                case 11:
                    ans += "одинадцать";
                    break;
                case 12:
                    ans += "двенадцать";
                    break;
                case 13:
                    ans += "тринадцать";
                    break;
                case 14:
                    ans += "четырнадцать";
                    break;
                case 15:
                    ans += "пятнадцать";
                    break;
                case 16:
                    ans += "шестнадцать";
                    break;
                case 17:
                    ans += "семнадцать";
                    break;
                case 18:
                    ans += "восемнадцать";
                    break;
                case 19:
                    ans += "девятнадцать";
                    break;
                default:
                    ans += "";
            }
            return ans;
        }
        switch (number % 10){
            case 1:
                ans += "один";
                break;
            case 2:
                ans += "два";
                break;
            case 3:
                ans += "три";
                break;
            case 4:
                ans += "четыре";
                break;
            case 5:
                ans += "пять";
                break;
            case 6:
                ans += "шесть";
                break;
            case 7:
                ans += "семь";
                break;
            case 8:
                ans += "восемь";
                break;
            case 9:
                ans += "девять";
                break;
            default:
                ans += "";
        }
        return ans;
    }
}
