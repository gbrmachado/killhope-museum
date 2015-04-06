package uk.ac.dur.group1.killhope_museum.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import uk.ac.dur.group1.killhope_museum.R;
import uk.ac.dur.group1.killhope_museum.dto.RockDTO;

public class RockDisplayActivity extends ActionBarActivity {

    private static String uniqueId;
    private static String title;
    private static String formula;
    private static Iterable<String> content;
    private static Iterable<Bitmap> images = null;



    public static void launchActivity(Context context, RockDTO rock)
    {
        if(context == null)
            throw new IllegalArgumentException("context is null");
        if(rock == null)
            throw new IllegalArgumentException("rock is null");

        Intent i = new Intent(context, RockDisplayActivity.class);

        uniqueId = rock.getID().toString();
        title = rock.getDisplayName();
        formula = rock.getFormula();

        ArrayList<String> content = new ArrayList<>();
        for(RockDTO.RockContent data : rock.getContent())
            content.add(data.getData());

        RockDisplayActivity.content = content;

        images = rock.getGallery();

        context.startActivity(i);
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

        setupGalleryFooter();

        WebView mywebView = (WebView) findViewById(R.id.webview);

        mywebView.getSettings().setDefaultTextEncodingName("utf-8");
        mywebView.getSettings().setJavaScriptEnabled(true);
        mywebView.setBackgroundColor(0x00000000);   //set the transparent background
        
        String finalContent = "";
        finalContent += "<html>" +
                "<body>";
        
        for (String cont : content)
            finalContent += cont + "<br>";

        finalContent += "</body></html>";
        //WARNING: There's a bug in Android which will mean that UTF-8 characters won't be displayed
        //In a webview, unless the charset is also set in the MIME type.
        mywebView.loadData(finalContent, "text/html; charset=utf-8", "UTF-8");
    }

    private void setupGalleryFooter() {
        LinearLayout imageGallery = (LinearLayout) findViewById(R.id.mygallery);
        final RockDisplayActivity self = this;
        for (final Bitmap bm : images) {

            ImageView iv = new ImageView(this);
            //We set the background instead of the image for the imageview. Determine why.
            iv.setBackground(new BitmapDrawable(getResources(), bm));
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FullScreenImageActivity.launchActivity(self, bm);
                }
            });

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(300, 300);
            iv.setLayoutParams(layoutParams);
            imageGallery.addView(iv);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rock_display, menu);
        return true;
    }
}
