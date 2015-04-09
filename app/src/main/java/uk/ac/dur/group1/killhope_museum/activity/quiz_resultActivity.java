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
public class quiz_resultActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);
        int num = getIntent().getExtras().getInt("Score");
        // first text
        TextView textView_one = (TextView) findViewById(R.id.text_one);
        String text = String.format(getString(R.string.quiz_score_explanation), num);
        textView_one.setText(text);
        // second text
        TextView textView_two = (TextView) findViewById(R.id.text_two);
        if(num >= 2){
            textView_two.setText(getString(R.string.quiz_try_harder_level));
        }else{
            textView_two.setText(getString(R.string.quiz_try_again));
        }
        // third text, display correct answers
        ArrayList<String> answer = getIntent().getExtras().getStringArrayList("selectedWrong");
        String display = getString(R.string.quiz_incorrect_answer_display) +"\n\n";
        int count = 0;
        for(String item:answer){
            display = display + item + "\n";
            // make space between each questions
            count++;
            if(count == 2){
                display = display + "\n";
                count = 0;
            }
        }
        TextView textView_three = (TextView) findViewById(R.id.text_three);
        textView_three.setText(display);
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
