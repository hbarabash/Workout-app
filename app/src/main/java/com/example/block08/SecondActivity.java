package com.example.block08;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent caller = getIntent();
        int number = caller.getIntExtra("number", 0);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(getResources().getString(R.string.text_number) + " " + number);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
        RelativeLayout exercise_layout = (RelativeLayout) findViewById(R.id.space_layout);


        for(int i = 0; i< number; i++){
            //create editTexts for exercise names
            RelativeLayout.LayoutParams editTextParam = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            //editTextParam.addRule(RelativeLayout.TEXT_ALIGNMENT_CENTER);
            EditText exercise = new EditText(SecondActivity.this);
            exercise.setId(i+1);
            exercise.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
            editTextParam.setMargins(0, i*250, 0, 0);
            editTextParam.addRule(RelativeLayout.CENTER_HORIZONTAL);
            exercise.setLayoutParams(editTextParam);
            exercise.setHint("exercise name " + exercise.getId());
            exercise_layout.addView(exercise);
            // create number input for time doing exercise
            /*
            RelativeLayout.LayoutParams timeParam = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            timeParam.addRule(RelativeLayout.RIGHT_OF, i+1);
            EditText time = new EditText(SecondActivity.this);
            time.setId((i+1)*100);
            timeParam.setMargins(200, i*100, 0, 0);
            time.setLayoutParams(timeParam);
            time.setHint("time");
            time.setInputType(InputType.TYPE_CLASS_NUMBER);
            exercise_layout.addView(time);
            // create number input for rest time

            RelativeLayout.LayoutParams restParam = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            restParam.addRule(RelativeLayout.RIGHT_OF, (i+1)*100);
            EditText rest = new EditText(SecondActivity.this);
            rest.setId((i+1)*10000);
            restParam.setMargins(200, i*100, 0, 0);
            rest.setLayoutParams(restParam);
            rest.setHint("rest time");
            rest.setInputType(InputType.TYPE_CLASS_NUMBER);
            exercise_layout.addView(rest);

             */
        }
    }
    public void start(View v){
        Intent caller = getIntent();
        int number = caller.getIntExtra("number", 0);
        int rest = caller.getIntExtra("rest", 0);
        int time = caller.getIntExtra("time", 0);
        //EditText num_exercises = (EditText) findViewById(R.id.editTextNumber);
        //int number = Integer.parseInt(num_exercises.getText().toString());
        Intent goToThird = new Intent();
        goToThird.setClass(this, ThirdActivity.class);
        goToThird.putExtra("number", number);
        goToThird.putExtra("total", number);
        goToThird.putExtra("rest", rest);
        goToThird.putExtra("time", time);
        goToThird.putExtra("current", 0);
        String exercise_name = null;
        //int time;
        //int rest;
        ArrayList<String> exercises = new ArrayList<String>();

        for(int i =0; i<number; i++){
            //adding exercise names into third activity
            exercise_name = ((EditText) findViewById(i+1)).getText().toString();
            //exercises.add(exercise_name);
            goToThird.putExtra(String.valueOf(i+1), exercise_name);

        }
        //goToThird.putStringArrayListExtra(exercise_name, exercises);

        startActivity(goToThird);
        finish();
    }
}