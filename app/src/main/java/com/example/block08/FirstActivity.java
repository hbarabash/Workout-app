package com.example.block08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }
    public void enter(View v){
        EditText num_exercises = (EditText) findViewById(R.id.editTextNumber);
        int number = Integer.parseInt(num_exercises.getText().toString());
        EditText time_input = findViewById(R.id.time);
        int time = Integer.parseInt(time_input.getText().toString());
        EditText rest_input = findViewById(R.id.rest);
        int rest = Integer.parseInt(rest_input.getText().toString());
        Intent goToSecond = new Intent();
        goToSecond.setClass(this, SecondActivity.class);
        goToSecond.putExtra("number", number);
        goToSecond.putExtra("time", time);
        goToSecond.putExtra("rest", rest);
        startActivity(goToSecond);
        finish();
    }
}