package uk.ac.dur.group1.killhope_museum.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import uk.ac.dur.group1.killhope_museum.R;


public class RockDisplayActivity extends ActionBarActivity implements View.OnClickListener{
//simulating the public class:


    String title = "Ankerite";
    String formula = "CaCO3";
    String info = "Ankerite is a calcium, iron, magnesium, manganese carbonate mineral of the " +
            "group of rhombohedral carbonates with formula: Ca(Fe,Mg,Mn)(CO3)2. In composition it is " +
            "closely related to dolomite, but differs from this in having magnesium replaced by " +
            "varying amounts of iron(II) and manganese.";

    int images[] = {R.drawable.ankerite, R.drawable.ankerite2, R.drawable.ankerite3, R.drawable.ankerite4};
    //the images to display

    LinearLayout imageGallery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rock_display);

        getSupportActionBar().setTitle(title);    //set the title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient)); //set the background to the actionbar

        TextView textFormula = (TextView) findViewById(R.id.chemFormulaInfo);
        textFormula.setText(formula);

        TextView textTitle = (TextView) findViewById(R.id.textInfo);
        textTitle.setText(info);

        LinearLayout imageGallery = (LinearLayout) findViewById(R.id.mygallery);
        for (int i=0; i<images.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setId(images[i]);
            iv.setOnClickListener(this);
            iv.setBackgroundResource(images[i]);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(400, 400);
            iv.setLayoutParams(layoutParams);
            imageGallery.addView(iv);
        }
    }

    @Override
    public void onClick(View v) {
        //TODO : It is not opening when the user clicks on the images
        FullScreenImageActivity.launchActivity(this, R.drawable.ankerite);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rock_display, menu);
        return true;
    }
}


//TODO: FULL SCREEN IMAGE IS NOT WORKING ON IMAGE GALLERY
//TODO: TEST WITH SOME REAL INFO
//TODO: FULL SCREEN PROBLEM

