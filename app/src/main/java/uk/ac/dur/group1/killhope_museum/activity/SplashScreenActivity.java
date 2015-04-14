package uk.ac.dur.group1.killhope_museum.activity;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import uk.ac.dur.group1.killhope_museum.KillhopeApplication;
import uk.ac.dur.group1.killhope_museum.R;


public class SplashScreenActivity extends ActionBarActivity {

    //Wait 3 seconds before running the next activity.
    private static final int TIMEOUT_MILLISECONDS = 3000;

    private static State currentState = State.Initial;

    private static SplashScreenTask thread;


    private KillhopeApplication getKillhopeApplication()
    {
        return (KillhopeApplication) getApplication();
    }

    private void setSplashScreenImage()
    {
        ImageView headlogo = (ImageView)findViewById(R.id.imageView);
        headlogo.setImageResource(R.drawable.killhope_logo);
    }

    private void setText(final String text)
    {

        final TextView textView = (TextView)findViewById(R.id.centra_view);
        textView.post(new Runnable() {
            @Override
            public void run() {
                textView.setText(text);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        setSplashScreenImage();

        //Not perfect, but doesn't matter, the entire thread's based around a lock, so multiple threads should
        //just wait and then finish instantly.
        new SetupImageCache(getKillhopeApplication()).run();

        update(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(thread != null)
            thread.activity = null;
    }

    private class SplashScreenTask extends AsyncTask<Void, Void, Void> {

        private final int time;
        private final State newState;
        private SplashScreenActivity activity;

        public SplashScreenTask(int time, SplashScreenActivity activity, State newState)
        {
            this.time = time;
            this.activity = activity;
            this.newState = newState;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            SplashScreenActivity.currentState = this.newState;

            if(this.activity != null) {
                this.activity.update(true); //reset if active.
            }

            if(SplashScreenActivity.thread == this)
                SplashScreenActivity.thread = null;
            this.activity = null;

            return null;
        }

        public void setActivity(SplashScreenActivity activity)
        {
            this.activity = activity;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(thread != null && thread.activity == this)
        {
            thread.activity = null;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        update(false);
    }

    private void update(boolean resetIfActive)
    {
        updateUI();
        if(thread != null) {
            thread.activity = this;
            if(!resetIfActive)
                return;
        }

        switch (currentState)
        {
            case Initial:
                thread = new SplashScreenTask(TIMEOUT_MILLISECONDS, this, State.Second);
                break;
            case Second:
                thread = new SplashScreenTask(TIMEOUT_MILLISECONDS + 1000, this, State.Done);
                break;
            case Done:
                MainMenuActivity.launchActivity(this);
                finish();
                break;
        }

        if(thread != null && thread.getStatus() == AsyncTask.Status.PENDING)
            thread.execute();
    }

    private void updateUI()
    {
        switch (currentState) {
            case Initial:
                setText(getString(R.string.splash_screen_first_message));
                break;
            case Second:
                setText(getString(R.string.splash_screen_second_message));
                break;
            case Done:
        }
    }

    private enum State
    {
        Initial,
        Second,
        Done,
    }

    private class SetupImageCache implements Runnable
    {
        private final KillhopeApplication app;

        public SetupImageCache(KillhopeApplication application) {
            this.app = application;
        }

        @Override
        public void run() {
            app.getRockList();
        }
    }
}
