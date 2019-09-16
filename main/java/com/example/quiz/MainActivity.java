package com.example.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView countlable,questionlable;
    private Button answer1,answer2,answer3,answer4;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;

    static final private int QUIZ_COUNT = 5;

    ArrayList<ArrayList<String>>quizArray = new ArrayList<>();
    String quizData[][]={
            //{"country Name","Right Answer","Choice1","Choice2","Choices3"}
            {"China","Beijing","Jakarta","Manila","Stockholm"},
            {"India","New Delhi","Beijing","Bangkok","Seoul"},
            {"Japan","Tokyo","Beijing","Bangkok","Seoul"},
            {"Italy","Rome","London","Paris","Athens"},
            {"Spain","Madrid","Mexico City","Bangkok","Stockholm"},
            {"Germany","Berlin","Copenhagen","Bangkok","Seoul"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countlable = findViewById(R.id.countlable);
        questionlable = findViewById(R.id.questionlable);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);

        //Create quizArray from quizData
        for (int i=0;i<quizData.length;i++){

            //Prepare array
            ArrayList<String>tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]); //country name
            tmpArray.add(quizData[i][1]); //right answer
            tmpArray.add(quizData[i][2]); //choice1
            tmpArray.add(quizData[i][3]); //choice2
            tmpArray.add(quizData[i][4]); //choice3

            //Add tmpArray to quizArray
            quizArray.add(tmpArray);
        }

        showNextQuiz();

    }

    private void showNextQuiz() {

        //Update quizCountlabel
        countlable.setText("Q"+quizCount);

        //Generate random number between 0 and 7 (quizeArray size = 1)
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        //Pick one quiz set
        ArrayList<String> quiz = quizArray.get(randomNum);

        //Set question and right answer
        //Array format:{"country Name","Right Answer","Choice1","Choice2","Choices3"}
        questionlable.setText(quiz.get(0));
        rightAnswer = quiz.get(1);

        //Remove "Country" from quiz and Shuffle Choices

        quiz.remove(0);
        Collections.shuffle(quiz);
        //Set Chices
        answer1.setText(quiz.get(0));
        answer2.setText(quiz.get(1));
        answer3.setText(quiz.get(2));
        answer4.setText(quiz.get(3));

        //Remove this quiz from quizArray
        quizArray.remove(randomNum);



    }

    public void checkAnswer(View view) {

        //Get pushed button
        Button answerBtn = findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;
        if (btnText.equals(rightAnswer)){
            //Correct!
            alertTitle ="Correct";
            rightAnswerCount++;
        }
        else {
            //wrong
            alertTitle = "Wrong...";
        }
        //Create dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Answer :" +rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (quizCount == QUIZ_COUNT){

                    //show result
                    Intent intent  = new Intent(getApplicationContext(),ResultActivity.class);
                    intent.putExtra("Right_answer_count",rightAnswerCount);
                    startActivity(intent);
                }
                else {

                    quizCount++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}
