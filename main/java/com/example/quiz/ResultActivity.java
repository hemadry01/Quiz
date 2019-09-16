package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultlable = findViewById(R.id.resultlabel);
        TextView totalScorelable = findViewById(R.id.totalScorelabel);

        int score =getIntent().getIntExtra("Right_answer_count",0);

        SharedPreferences setting = getSharedPreferences("quizeApp", Context.MODE_PRIVATE);
        int totalScore = setting.getInt("totalScore",0);

        resultlable.setText(score+"/5");
        totalScorelable.setText("Total Score :"+totalScore);
        totalScore+=score;

        SharedPreferences.Editor editor = setting.edit();
        editor.putInt("totalScore",+totalScore);
        editor.commit();
    }

    public void returnTop(View view) {
    }
}
