package uk.ac.dur.group1.killhope_museum.activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import uk.ac.dur.group1.killhope_museum.R;
import uk.ac.dur.group1.killhope_museum.dto.RockDTO;

public class RockDisplayActivity extends ActionBarActivity implements View.OnClickListener{
//simulating the public class:
    String uniqueId = "Rock-Ankerite";

    String title = "Ankerite";
    String formula = "PCa(Mg, Fe2+, Mn)(CO₃)₂";
    String content[] = {
            "<b>Colour</b>: White/Yellowish white.<br/><b>Abundance</b>: Frequently fills joints in coal seams.<br/><b>Hardness</b>: 3.5 - 4 (Soft - Medium).<br/><b>Lustre</b>: Vitreous to pearly.<br/><b>Ore</b>: Gangue mineral accompanying iron ore.<br/><b>Interesting fact</b>: Darkens with heating and may become magnetic.",
            "<b>Uses</b>: Minor iron ore.<br/><b>Main countries involved in the extraction of mineral</b>: N/A.<br/><b>Crystal Habit</b>: Rhombohedral with curved faces, columnar, granular, massive.<br/><b>Crystal Structure</b>: Trigonal.<br/><b>Depositional Environment</b>: Result of both hydrothermal, low-temperature metasomatism and banded iron formations.<br/><b>Transparency</b>: Translucent - transparent.<br/><b>Origin of Name</b>: Named after M.J.Anker, an Austrian mineralogist.<br/><b>Colours Observed at Killhope</b>: Cream, brown.",
            "<b>Further uses</b>: None.<br/><b>Streak</b>: White.<br/><b>Cleavage</b>: Perfect.<br/><b>Fracture</b>: Subconchoidal.",
            "<b>Specific Gravity</b>: 2.93 - 3.10.<br/><b>Further properties</b>: Colour turns yellowish brown/brown when oxidation of iron has occured.<br/><b>Relevance at Killhope</b>: Large concentration of ankerite accompany galena-sphalerite-fluorite-baryte veins such as in the North Pennines orefield where limestone or quartz dolerite has been replaced.<br/><b>Optical Properties</b>: Increasing replacement of Mg by Fe2+ increases the refractive indices, birefringence and specific gravity."
    };

    int images[] = {R.drawable.ankerite, R.drawable.ankerite2, R.drawable.ankerite3, R.drawable.ankerite4};
    //the images to display

    String finalContent = "";
    public static void launchActivity(Context context, RockDTO rock)
    {
        Toast.makeText(context, "Show: " + rock.getID() + " Not implemented", Toast.LENGTH_SHORT).show();
        //TODO: Not implemented.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rock_display);

        getSupportActionBar().setTitle(title);    //set the title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient)); //set the background to the actionbar

        TextView textFormula = (TextView) findViewById(R.id.chemFormulaInfo);
        textFormula.setText(formula);

        LinearLayout imageGallery = (LinearLayout) findViewById(R.id.mygallery);
        for (int i=0; i<images.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setId(images[i]);
            iv.setOnClickListener(this);
            iv.setBackgroundResource(images[i]);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300, 300);
            iv.setLayoutParams(layoutParams);
            imageGallery.addView(iv);
        }

        WebView mywebView = (WebView) findViewById(R.id.webview);
        mywebView.getSettings().setJavaScriptEnabled(true);
        mywebView.setBackgroundColor(0x00000000);   //set the transparent background
        for (int i=0; i<content.length; i++) {
            finalContent = finalContent + content[i] + "<br>";
        }
        mywebView.loadData(finalContent , "text/html", "UTF-8");
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

