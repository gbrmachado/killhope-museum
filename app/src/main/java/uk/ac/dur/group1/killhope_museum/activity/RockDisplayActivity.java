package uk.ac.dur.group1.killhope_museum.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uk.ac.dur.group1.killhope_museum.KillhopeApplication;
import uk.ac.dur.group1.killhope_museum.R;
import uk.ac.dur.group1.killhope_museum.dto.MapDTO;
import uk.ac.dur.group1.killhope_museum.dto.RockDTO;
import uk.ac.dur.group1.killhope_museum.layout.AccordionLayout;
import uk.ac.dur.group1.killhope_museum.layout.GlossaryLoaderJavascriptInterface;

public class RockDisplayActivity extends ActionBarActivity {

    private static final int FOOTER_HEIGHT = 300;

    private static String uniqueId;
    private static String title;
    private static String formula;
    private static Iterable<String> content;
    private static Iterable<Bitmap> images = null;
    private static byte[] animation;


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
        animation = rock.getAnimation();

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

        setupAnimation();

        LinearLayout layout = (LinearLayout) findViewById(R.id.rock_display_main_content);

        AccordionLayout accordion = new AccordionLayout(this);
        setContent(accordion);
        accordion.addTo(layout);
    }



    private void setContent(AccordionLayout myWebView) {
        String finalContent = "";
        finalContent += "<html>" +
                "<head>" +
                "<script type=\"text/javascript\">" +
                "function loadGlossaryEntry(str) { Android.loadGlossary(str); }</script>" +
                "</head>" +
                "<body>%s</body></html>";

        for (String cont : content)
            myWebView.AddWebViewSection(String.format(finalContent, makeHTMLGlossary(cont)));
    }

    //Transforms content into links to the glossary page.
    private String makeHTMLGlossary(String input)
    {
        Collection<String> dictionaryEntries = ((KillhopeApplication) getApplication()).getLinkableWords();

        for(String s : dictionaryEntries)
        {
            //Non-word character, then the word (case insensitive), optional s, then another non-word.
            //This is required so "more" will not match "ore".
            Pattern p = Pattern.compile(String.format("(\\W)((?i)%ss?)(\\W)", s));
            Matcher m = p.matcher(input);
            //Note: The replacement replaces the loadGlossaryEntry js with %s, which is the dictionary entry.
            //Whereas $2 is the word matched. So "ores" will link to ore.
            String newLink = String.format("<a href=\"noJS.html\" onclick=\"loadGlossaryEntry(&quot;%s&quot;);return false;\">$2</a>", s);
            //as we match non-word characters outside, we need to re-add these.
            String replaceWith = String.format("$1%s$3", newLink);

            if (m.find())
                input = m.replaceAll(replaceWith);
        }
        return input;
    }

    private void setupAnimation()
    {
        if(animation == null)
            return;

        String base64 = Base64.encodeToString(animation, Base64.DEFAULT);

        WebView animation = (WebView) findViewById(R.id.rock_display_animated_gif_web_view);
        makeTransparentBackground(animation);
        String data = String.format("<html><body><img style='margin: 0px auto;display:block' src=\"data:image/gif;base64,%s\"/></body></html>", base64);
        animation.loadData(data, "text/html; charset=utf-8", "UTF-8");

    }

    private void makeTransparentBackground(View v)
    {
        //Sets the background to a transparent colour to allow for the gradient to be visible.
        v.setBackgroundColor(0x00000000);   //set the transparent background
    }

    private void setupGalleryFooter() {
        LinearLayout imageGallery = (LinearLayout) findViewById(R.id.mygallery);
        final RockDisplayActivity self = this;
        for (final Bitmap bm : images) {

            ImageView iv = new ImageView(this);

            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FullScreenImageActivity.launchActivity(self, bm);
                }
            });

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, FOOTER_HEIGHT);
            double scale = (double) bm.getHeight() / (double) FOOTER_HEIGHT;
            int width = (int) (bm.getWidth() / scale );
            layoutParams.width = width;
            iv.setLayoutParams(layoutParams);
            //We set the background instead of the image for the imageview. Determine why.
            iv.setImageBitmap(bm);
            imageGallery.addView(iv);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        return true;
    }
}
