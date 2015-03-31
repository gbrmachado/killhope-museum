package uk.ac.dur.group1.killhope_museum.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import uk.ac.dur.group1.killhope_museum.R;

/**
 * Created by haijiewu on 28/03/15.
 */
public class quiz_processActivity extends Activity{
    private String[] question_array;
    private String[] answer_array;
    private int[] correctAnswer_array;

    //array to store checked radio button
    private ArrayList<Integer> checked_array;
    // array to store wrong answer
    private ArrayList<String> wrong_answer;
    // check how many question get correct and check current question
    private int currentQuestion;
    // variable to record how many pages going back
    private int checkPrevious;
    // check textview
    private TextView textView;
    private RadioButton radio_one;
    private RadioButton radio_two;
    private RadioButton radio_three;
    private RadioGroup radio_group;
    private Button button_one;
    private Button button_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_process);
        // retrieve data from main activity class
        question_array = new String[]{};
        question_array = getIntent().getExtras().getStringArray("Question");
        answer_array = new String[]{};
        answer_array = getIntent().getExtras().getStringArray("Answer");
        correctAnswer_array = new int[]{};
        correctAnswer_array = getIntent().getExtras().getIntArray("Correct");

        checked_array = new ArrayList<Integer>(10);
        wrong_answer = new ArrayList<String>(10);
        currentQuestion = 1;
        checkPrevious = 0;

        // initialise question textview strings
        textView = (TextView) findViewById(R.id.question);
        textView.setText(question_array[0]);
        // initialise radio button strings
        radio_group = (RadioGroup) findViewById(R.id.radioGroup);
        radio_one = (RadioButton) findViewById(R.id.answer_one);
        radio_one.setText(answer_array[0]);
        radio_two = (RadioButton) findViewById(R.id.answer_two);
        radio_two.setText(answer_array[1]);
        radio_three = (RadioButton) findViewById(R.id.answer_three);
        radio_three.setText(answer_array[2]);
        button_one = (Button) findViewById(R.id.button_one);
        button_two = (Button) findViewById(R.id.button_two);
        button_two.setEnabled(false);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // click method for button next
    public void onClickNext(View view) {
        button_two.setEnabled(true);
        // to check whether has been go back to the previous page, if yes, dont change.
        int num = radio_group.getCheckedRadioButtonId();
        if(checkPrevious == 0) {
            if (num != -1) {
                checked_array.add(num);
                // check current question, if isat the last question, jump to other activity
                if(currentQuestion  == question_array.length){
                    int score = check_score();
                    Intent intent = new Intent(quiz_processActivity.this,quiz_resultActivity.class);
                    intent.putExtra("Score",score);
                    intent.putStringArrayListExtra("selectedWrong",wrong_answer);
                    this.finish();
                    startActivity(intent);
                }
                else{
                    currentQuestion++;
                    display();
                    radio_group.clearCheck();
                    // if is on last question, change text "next" to "Done"
                    if(currentQuestion  == question_array.length){
                        button_one.setText("Done");
                    }
                }
            }
        }
        else{
            checkPrevious--;
            check_choose(num);
            radio_group.clearCheck();
            currentQuestion++;
            display();
            if(checked_array.size() == currentQuestion){
                int pos = checked_array.get(currentQuestion - 1);
                radio_group.check(pos);
            }
        }

    }

    // for previous page
    public void onClickPrevious(View view){
        if(currentQuestion != 1){
            // check select radio before going back, if changed, swap it
            if(checkPrevious != 0){
                int num = radio_group.getCheckedRadioButtonId();
                check_choose(num);
            }
            //
            currentQuestion--;
            checkPrevious ++;
            display();
            int pos = checked_array.get(currentQuestion-1);
            radio_group.check(pos);
            if(currentQuestion == 1){
                button_two.setEnabled(false);
            }
        }
    }


    //counting scores
    public int check_score(){
        // first convert question_array which is button id to corresponding answer 1 2 3
        ArrayList<Integer> choose= new ArrayList<Integer>(10);
        for(int item :checked_array){
            if(item == R.id.answer_one){
                choose.add(1);
            }
            else if(item == R.id.answer_two){
                choose.add(2);

            }
            else if(item == R.id.answer_three){
                choose.add(3);
            }
        }
        // compare selected with correct answer
        int score = 0;
        for(int i=0;i<correctAnswer_array.length;i++){
            if(correctAnswer_array[i] == choose.get(i)){
                score++;
            }else{
                wrong_answer.add(question_array[i]);
                wrong_answer.add(answer_array[correctAnswer_array[i] + 2 * i]);
            }
        }
        return score;
    }


    // display textview
    public void display(){
        // -1 because array start from 0
        textView.setText(question_array[(currentQuestion - 1)]);
        radio_one.setText(answer_array[(currentQuestion -1) * 3]);
        radio_two.setText(answer_array[(currentQuestion -1) * 3 + 1]);
        radio_three.setText(answer_array[(currentQuestion -1) * 3 + 2]);
    }

    // check checked_array, to see whether user has re-choose radio
    public void check_choose(int x){
        int pos = checked_array.get(currentQuestion-1);
        if(pos != x){
            checked_array.set(currentQuestion-1,x);
        }
    }

}
