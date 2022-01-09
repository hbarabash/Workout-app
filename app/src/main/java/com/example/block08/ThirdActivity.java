package com.example.block08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        final Intent caller = getIntent();
        final int number = caller.getIntExtra("number", 0);
        final int time = caller.getIntExtra("time", 0);
        final int rest = caller.getIntExtra("rest", 0);
        //ArrayList<String> exercise_name = caller.getStringArrayListExtra("exercise_name");
        final int current = caller.getIntExtra("current", 0);
        TextView rest_countdown = findViewById (R.id.textView3);
        rest_countdown.setText("Rest time left: ");
        final TextView textView = findViewById(R.id.textView);

        final MediaPlayer dingSound = MediaPlayer.create(this, R.raw.ding);

        View next_exercise = findViewById(R.id.next);
        /*next_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dingSound.start();
            }
        });
        */
        final CountDownTimer rest_timer = new CountDownTimer(rest * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                textView.setText("Rest!");
                TextView rest_countdown = findViewById (R.id.textView3);
                    rest_countdown.setText("Rest time left: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                dingSound.start();
                if (number > 1){
                    View next_exercise = findViewById(R.id.next);
                    next_exercise.performClick();
                }
                else {
                    textView.setText("Done!");
                }
            }
        };
        CountDownTimer countdown = null;
        final CountDownTimer finalCountdown = countdown;
        final CountDownTimer finalCountdown1 = countdown;
        final CountDownTimer finalCountdown2 = countdown;
        countdown = new CountDownTimer(time * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                TextView textView = findViewById(R.id.textView);
                textView.setText("Exercise #" + (current + 1) + ": " + caller.getStringExtra(String.valueOf(current + 1)));
                TextView countdown = (TextView) findViewById(R.id.textView2);
                countdown.setText("time left: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                rest_timer.start();

            }
        }.start();
    }


    public void start(View v){
        Intent caller = getIntent();
        int number = caller.getIntExtra("number", 0);
        int rest = caller.getIntExtra("rest", 0);
        int time = caller.getIntExtra("time", 0);
        int total = caller.getIntExtra("total", 0);
        final int current = caller.getIntExtra("current", 0);

        Intent goToThird = new Intent();
        goToThird.setClass(this, ThirdActivity.class);
        goToThird.putExtra("number", number - 1);
        goToThird.putExtra("rest", rest);
        goToThird.putExtra("time", time);
        goToThird.putExtra("total", total);
        goToThird.putExtra("current", current + 1);
        for (int i = 0; i < total; i++) {
            String exercise_name = caller.getStringExtra(String.valueOf(i + 1));
            goToThird.putExtra(String.valueOf(i+1), exercise_name);
        }

        startActivity(goToThird);
        finish();
    }
}
