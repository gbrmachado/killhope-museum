package uk.ac.dur.group1.killhope_museum.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import uk.ac.dur.group1.killhope_museum.KillhopeApplication;
import uk.ac.dur.group1.killhope_museum.R;

public class GlossaryItemActivity extends ActionBarActivity {

    private static final String GLOSSARY_KEY = "item";

    public static void launchActivity(Context context, String key)
    {
        if(context == null)
            throw new IllegalArgumentException("context is null");
        if(key == null)
            throw new IllegalArgumentException("the supplied key is null");

        Intent i = new Intent(context, GlossaryItemActivity.class);
        i.putExtra(GLOSSARY_KEY, key);
        context.startActivity(i);
    }

    private KillhopeApplication getKillhopeApplication() { return (KillhopeApplication) this.getApplication(); }
    private String getGlossaryDefinition(String key) { return getKillhopeApplication().getGlossaryItem(key); }

    private WebView getContentPane() { return (WebView) findViewById(R.id.glossary_item_web_view); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glossary_item);

        String argument = getIntent().getStringExtra(GLOSSARY_KEY);
        if(!validateArgument(argument))
        {
            finish();
            return;
        }

        String definition = getGlossaryDefinition(argument);
        setPageTitle(argument);
        setData(definition);
    }

    private void setData(String definition)
    {
        WebView contentView = getContentPane();
        String finalHtml = String.format("<html><body>%s</body></html>", definition);
        contentView.loadData(finalHtml, "text/html; charset=utf-8", "UTF-8");
    }

    private void setPageTitle(String pageTitle)
    {
        //The page title should have a formatter, therefore:
        String currentTitle = getTitle().toString();
        String newTitle = String.format(currentTitle, pageTitle);
        setTitle(newTitle);
    }

    private boolean validateArgument(String argument)
    {
        if(argument == null)
        {
            Toast.makeText(this, "Unable to display glossary entry", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(getGlossaryDefinition(argument) == null)
        {
            Toast.makeText(this, String.format("Unable to display glossary entry: %s", argument), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_glossary_item, menu);
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
