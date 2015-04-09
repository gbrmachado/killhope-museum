package uk.ac.dur.group1.killhope_museum.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import uk.ac.dur.group1.killhope_museum.R;

/**
 * Created by haijiewu on 28/03/15.
 */
public class quiz_resultActivity extends Activity
{
    public static final String INTENT_PARAM_SCORE = "Score";
    public static final String INTENT_PARAM_CORRECT_ANSWERS = "selectedWrong";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        int score = getIntent().getExtras().getInt(INTENT_PARAM_SCORE);

        displayScoreAmount(score);
        displayScoreComment(score);
        displayCorrectAnswers();
    }

    private void displayCorrectAnswers() {
        ArrayList<String> answer = getIntent().getExtras().getStringArrayList(INTENT_PARAM_CORRECT_ANSWERS);
        String display = getString(R.string.quiz_incorrect_answer_display) +"\n\n";
        int count = 0;
        for(String item:answer){
            display += item + "\n";
            // Display a blank line between every answer
            // Answer contains 2 items per question, the question and the correct item.
            count++;
            if(count == 2){
                display += "\n";
                count = 0;
            }
        }
        TextView textView_three = (TextView) findViewById(R.id.text_three);
        textView_three.setText(display);
    }

    private void displayScoreAmount(int score) {
        TextView textView_one = (TextView) findViewById(R.id.text_one);
        String text = String.format(getString(R.string.quiz_score_explanation), score);
        textView_one.setText(text);
    }

    private void displayScoreComment(int score) {
        TextView textView_two = (TextView) findViewById(R.id.text_two);
        if(score >= 2){
            textView_two.setText(getString(R.string.quiz_try_harder_level));
        }else{
            textView_two.setText(getString(R.string.quiz_try_again));
        }
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

    public void backHome(View view){
        Intent intent = new Intent(quiz_resultActivity.this,quiz_homeActivity.class);
        this.finish();
        startActivity(intent);
    }
}
