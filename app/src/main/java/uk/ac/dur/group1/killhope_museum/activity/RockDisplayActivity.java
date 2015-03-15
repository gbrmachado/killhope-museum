package uk.ac.dur.group1.killhope_museum.activity;

import android.app.ActionBar;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import uk.ac.dur.group1.killhope_museum.R;


public class RockDisplayActivity extends ActionBarActivity {
//simulating the public class:

    String title = "Ankerite";
    String formula = "CaCO3";
    String info = "Ankerite is a calcium, iron, magnesium, manganese carbonate mineral of the " +
            "group of rhombohedral carbonates with formula: Ca(Fe,Mg,Mn)(CO3)2. In composition it is " +
            "closely related to dolomite, but differs from this in having magnesium replaced by " +
            "varying amounts of iron(II) and manganese.";
    String image = "/assets/Rocks/ankerite.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rock_display);
        this.populate();
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient));
    }


    public void populate() {
        getSupportActionBar().setTitle(title);    //set the title

     //   getSupportActionBar().setIcon(getResources().getDrawable(R.drawable.ic_menu_logo));
        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(R.drawable.ankerite);

        TextView textFormula = (TextView) findViewById(R.id.chemFormulaInfo);
        textFormula.setText(formula);

        TextView textTitle = (TextView) findViewById(R.id.textInfo);
        textTitle.setText(info);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rock_display, menu);
        return true;
    }
}


//TODO: PUT IMAGES ON ACTION MENU
//TODO: BACKGROUND COLORS(improve it)
//TODO: IMAGE'S GALLERY
//TODO: TEST WITH SOME REAL INFO
    /*


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
*/