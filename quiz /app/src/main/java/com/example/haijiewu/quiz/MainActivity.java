package com.example.haijiewu.quiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;


public class MainActivity extends Activity {
    private String[] question_array;
    private String[] answer_array;
    private String[] correctAnswer_array;

    // check how many question get right and check current question
    private int getCorrectAnswer;
    private int currentQuestion;

    // change text
    TextView textView;
    RadioButton radio_one;
    RadioButton radio_two;
    RadioButton radio_three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question_array = new String[]{"What is the hardest mineral on display at Killhope?","radio_one.setText(answer_array[0]);"};
        answer_array = new String[]{"Quartz","Baryte","Galena","Siderite","Galena","Sphalerite"};
        correctAnswer_array = new String[]{"Quartz","Galena"};
        getCorrectAnswer = 3;
        currentQuestion = 1;

        // initialise question textview strings
        textView = (TextView)findViewById(R.id.question);
        textView.setText(question_array[0]);
        // initialise radio button strings
        radio_one = (RadioButton)findViewById(R.id.answer_one);
        radio_one.setText(answer_array[0]);
        radio_two = (RadioButton)findViewById(R.id.answer_two);
        radio_two.setText(answer_array[1]);
        radio_three = (RadioButton)findViewById(R.id.answer_three);
        radio_three.setText(answer_array[2]);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void SelectAnswer(View view){

    }
}
