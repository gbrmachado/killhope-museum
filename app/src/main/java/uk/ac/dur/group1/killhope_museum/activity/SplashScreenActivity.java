package uk.ac.dur.group1.killhope_museum.activity;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import uk.ac.dur.group1.killhope_museum.MainMenuIcon;
import uk.ac.dur.group1.killhope_museum.R;


public class SplashScreenActivity extends ActionBarActivity {

    //Wait 3 seconds before running the next activity.
    private static final int TIMEOUT_MILLISECONDS = 3000;
    private TextView textView;
    private ImageView headlogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        textView = (TextView)findViewById(R.id.centra_view);

        headlogo = (ImageView)findViewById(R.id.imageView);
        headlogo.setImageResource(R.drawable.killhope_logo);
        final SplashScreenActivity self = this;
        textView.setText(getString(R.string.splash_screen_first_message));
        new Handler().postDelayed(new Runnable() {
            public void run() {
                textView.setText(getString(R.string.splash_screen_second_message));
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        MainMenuActivity.launchActivity(self);
                        finish();
                    }
                }, TIMEOUT_MILLISECONDS + 1000);
            }
        }, TIMEOUT_MILLISECONDS);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash_screen, menu);
        return true;
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
}
