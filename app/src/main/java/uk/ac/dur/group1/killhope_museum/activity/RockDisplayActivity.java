package uk.ac.dur.group1.killhope_museum.activity;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import uk.ac.dur.group1.killhope_museum.R;
import uk.ac.dur.group1.killhope_museum.dto.RockDTO;

public class RockDisplayActivity extends ActionBarActivity {

    public static void launchActivity(Context context, RockDTO rock)
    {
        Toast.makeText(context, "Show: " + rock.getID() + " Not implemented", Toast.LENGTH_SHORT).show();
        //TODO: Not implemented.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rock_display);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rock_display, menu);
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
