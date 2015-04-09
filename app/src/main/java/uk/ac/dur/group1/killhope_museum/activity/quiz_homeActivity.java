package uk.ac.dur.group1.killhope_museum.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import uk.ac.dur.group1.killhope_museum.KillhopeApplication;
import uk.ac.dur.group1.killhope_museum.R;
import uk.ac.dur.group1.killhope_museum.dto.QuizDTO;

/**
 * Created by haijiewu on 28/03/15.
 */
public class quiz_homeActivity extends Activity{

    private static final int ANSWERS_PER_QUESTION = 3;

    public static void launchActivity(Context context)
    {
        if(context == null)
            throw new IllegalArgumentException("context is null");

        Intent i = new Intent(context, quiz_homeActivity.class);
        context.startActivity(i);
    }

    private KillhopeApplication getKillhopeApplication()
    {
        return (KillhopeApplication) getApplication();
    }

    private QuizDTO getQuiz()
    {
        return getKillhopeApplication().getQuiz();
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
        launchQuiz(getQuiz().getEasyQuestions());
    }

    public void onClickPageTwo(View view){
        launchQuiz(getQuiz().getMediumQuestions());
    }

    public void onClickPageThree(View view){
        launchQuiz(getQuiz().getHardQuestions());
    }

    /**
     * Launches a quiz with the specified questions.
     */
    private void launchQuiz(List<QuizDTO.QuizQuestion> quiz)
    {
        int size = quiz.size();
        String[] questions = new String[size];
        String[] answers = new String[size * ANSWERS_PER_QUESTION];
        int[] correctAnswers = new int[size];
        for(int i = 0; i < size; i++)
        {
            QuizDTO.QuizQuestion question = quiz.get(i);
            //Set the question text, answers and correct answer from the specific question,
            questions[i] = question.getQuestion();
            List<String> questionAnswers =  question.getAnswers();
            //Answers
            for(int j = 0; j < ANSWERS_PER_QUESTION; j++)
                answers[i * ANSWERS_PER_QUESTION + j] = questionAnswers.get(j);
            //set the correct index of the answer.
            //Note: The quiz activity accepts the correct answers as a 1-based index, so we need to add 1.
            correctAnswers[i] = question.getCorrectAnswerIndex() + 1;
        }
        startQuizActivity(questions, answers, correctAnswers);
    }

    private void startQuizActivity(String[] question_array,  String[] answer_array, int[] correctAnswer_array)
    {
        Intent intent = new Intent(quiz_homeActivity.this,quiz_processActivity.class);
        intent.putExtra("Question",question_array);
        intent.putExtra("Answer",answer_array);
        intent.putExtra("Correct",correctAnswer_array);
        this.finish();
        startActivity(intent);
    }

}
