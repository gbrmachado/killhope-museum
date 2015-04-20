package uk.ac.dur.group1.killhope_museum.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
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
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

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

    private TextView getTextError() {return (TextView) findViewById(R.id.glossary_results_error); }

    /**
     *
     * @param makeErrorVisible Whether to hide the listView and make the error text visible.
     */
    private void toggleErrorVisibility(boolean makeErrorVisible)
    {
        int listVisibility = makeErrorVisible  ? View.GONE : View.VISIBLE;
        int errorVisibility = makeErrorVisible ? View.VISIBLE : View.GONE;
        getListView().setVisibility(listVisibility);
        getTextError().setVisibility(errorVisibility);
    }

    private void setError(CharSequence message)
    {
        TextView errorText = getTextError();
        errorText.setText(message);
        toggleErrorVisibility(true);
    }

    private void setGlossary(List<String> glossary)
    {
        if(glossary == null || glossary.size() == 0)
        {
            setError(getString(R.string.glossary_no_results_available));
            return;
        }
        Collections.sort(glossary);
        this.listFilterAdapater = new ArrayAdapter<>(this, R.layout.list_item, R.id.list_item_text, glossary);
        ListView listView = getListView();
        listView.setAdapter(listFilterAdapater);
        listView.setEmptyView(findViewById(R.id.glossary_results_error));
        setupFiltering(); //Filtering should only be applied if there is an adapter.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glossary);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient)); //set the background to the actionbar

        KillhopeApplication application = (KillhopeApplication) getApplication();
        setGlossary(application.getGlossary());
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
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
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
