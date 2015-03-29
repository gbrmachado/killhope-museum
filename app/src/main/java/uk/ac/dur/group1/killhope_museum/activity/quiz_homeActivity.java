package uk.ac.dur.group1.killhope_museum.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import uk.ac.dur.group1.killhope_museum.R;

/**
 * Created by haijiewu on 28/03/15.
 */
public class quiz_homeActivity extends Activity{

    public static void launchActivity(Context context)
    {
        if(context == null)
            throw new IllegalArgumentException("context is null");

        Intent i = new Intent(context, quiz_homeActivity.class);
        context.startActivity(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_home);

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

    public void onClickPageOne(View view){
        String[] question_array = new String[]{"What is the hardest mineral on display at Killhope?", "Which grey mineral is an ore of lead?","What mineral was predominantly mined at Killhope?"};
        String [] answer_array = new String[]{"Quartz", "Baryte", "Galena", "Siderite", "Galena", "Sphalerite","Galena","Fluorite","Siderite"};
        int [] correctAnswer_array = new int[]{1,2,1};
        Intent intent = new Intent(quiz_homeActivity.this,quiz_processActivity.class);
        intent.putExtra("Question",question_array);
        intent.putExtra("Answer",answer_array);
        intent.putExtra("Correct",correctAnswer_array);
        this.finish();
        startActivity(intent);
    }

    public void onClickPageTwo(View view){
        String[] question_array = new String[]{"Which mineral has the chemical formula CaCO3?","Smithsonite is an ore of which metal?","How long ago did the Whin Sill Intrusion form?"};
        String [] answer_array = new String[]{"Aragonite","Ankerite","Barytocalcite","Lead","Iron","Zinc","200 Million years ago","300 Million years ago","400 Million years ago"};
        int [] correctAnswer_array = new int[]{1,3,2};
        Intent intent = new Intent(quiz_homeActivity.this,quiz_processActivity.class);
        intent.putExtra("Question",question_array);
        intent.putExtra("Answer",answer_array);
        intent.putExtra("Correct",correctAnswer_array);
        this.finish();
        startActivity(intent);
    }

    public void onClickPageThree(View view){
        String[] question_array = new String[]{"Which mineral is a pseudomorph (no crystal structure)?","Which mineral was used to fabricate World War II weapon sights?","What lustre does baryte exhibit?"};
        String [] answer_array = new String[]{"Cerussite","Limonite","Smithsonite","Barytocalcite","Baryte","Calcite","Resinous","Earthy","Vitreous"};
        int [] correctAnswer_array = new int[]{2,3,3};
        Intent intent = new Intent(quiz_homeActivity.this,quiz_processActivity.class);
        intent.putExtra("Question",question_array);
        intent.putExtra("Answer",answer_array);
        intent.putExtra("Correct",correctAnswer_array);
        this.finish();
        startActivity(intent);
    }

}
