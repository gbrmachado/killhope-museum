package uk.ac.dur.group1.killhope_museum.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import uk.ac.dur.group1.killhope_museum.KillhopeApplication;
import uk.ac.dur.group1.killhope_museum.R;

public class GlossaryActivity extends ActionBarActivity {
    //http://www.androidhive.info/2012/09/android-adding-search-functionality-to-listview/

    private ArrayAdapter<String> listFilterAdapater;


    public static void launchActivity(Context context)
    {
        if(context == null)
            throw new IllegalArgumentException("context is null");

        Intent i = new Intent(context, GlossaryActivity.class);
        context.startActivity(i);
    }

    private ListView getListView()
    {
        return (ListView) findViewById(R.id.glossary_list);
    }

    private EditText getSearchBox()
    {
        return (EditText) findViewById(R.id.glossary_search);
    }

    private void setGlossary(Map<String, String> glossary)
    {
        ArrayList<String> keys = new ArrayList<>();
        for(String key : glossary.keySet())
            keys.add(key);

        Collections.sort(keys);
        this.listFilterAdapater = new ArrayAdapter<>(this, R.layout.list_item, R.id.list_item_text, keys);
        getListView().setAdapter(listFilterAdapater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glossary);

        KillhopeApplication application = (KillhopeApplication) getApplication();
        setGlossary(application.getGlossary());
        setupFiltering();
        setClickListener();
    }

    private void setClickListener()
    {
        final GlossaryActivity self = this;
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String item = listFilterAdapater.getItem(position);
                GlossaryItemActivity.launchActivity(self, item);
            }
        });
    }

    private void setupFiltering() {
        getSearchBox().addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                listFilterAdapater.getFilter().filter(s);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_glossary, menu);
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
