package uk.ac.dur.group1.killhope_museum.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uk.ac.dur.group1.killhope_museum.KillhopeApplication;
import uk.ac.dur.group1.killhope_museum.R;
import uk.ac.dur.group1.killhope_museum.RockListFactory;
import uk.ac.dur.group1.killhope_museum.dto.RockDTO;
import uk.ac.dur.group1.killhope_museum.dto.RockListFacade;
import uk.ac.dur.group1.killhope_museum.utilities.DisplayUtilities;

/**
 * The Rock List activity is designed to display a selectable and graphic list of
 * available rocks in the application, when selected, each rock will open up the RockDisplayActivity
 *
 * This activity uses a dynamic view based on the available rocks.
 */
public class RockListActivity extends ActionBarActivity {

    private static final String FILTER_KEY = "Filter";
    public static void launchActivity(Context context, Iterable<String> rockList)
    {
        if(context == null)
            throw new IllegalArgumentException("context is null");

        Intent i = new Intent(context, RockListActivity.class);

        if(rockList != null)
        {
            ArrayList<String> rocks = new ArrayList<>();
            for(String r : rockList)
                rocks.add(r);
            String[] arr = new String[rocks.size()];
            rocks.toArray(arr);
            i.putExtra(FILTER_KEY, arr);
        }

        context.startActivity(i);
    }

    public static void launchActivity(Context context)
    {
        launchActivity(context, null);
    }

    public KillhopeApplication getKillhopeApplication()
    {
        return (KillhopeApplication) super.getApplication();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rock_list);

        ScrollView v = (ScrollView)this.findViewById(R.id.scroll_view_content_wrapper);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        v.addView(layout);

        List<RockDTO> rocks = getKillhopeApplication().getRockList();
        rocks = filterRocks(rocks);
        RockListFacade d = new RockListFacade(rocks);

        int screenWidth = DisplayUtilities.getScreenWidth(this);

        for(RockDTO rock : d.getRocksForScreenSize(screenWidth))
        {
            View forRock = createViewForRock(rock);
            layout.addView(forRock);
        }
    }

    /**
     * A parameter may be passed to this to filter the rocks based on the ID.
     * @param rocks the list to filter
     * @return the filtered list
     */
    private List<RockDTO> filterRocks(List<RockDTO> rocks)
    {
        String[] filter = this.getIntent().getStringArrayExtra(FILTER_KEY);

        if(filter == null)
            return rocks;

        List<String> collectionFilter = Arrays.asList(filter);

        ArrayList<RockDTO> ret = new ArrayList<>();

        for(RockDTO rock : rocks)
            if(collectionFilter.contains(rock.getID()))
                ret.add(rock);


        return ret;
    }


    private View createViewForRock(final RockDTO rock)
    {
        TextView v = new TextView(this);
        Drawable background = new BitmapDrawable(getResources(), rock.getRockListImage());
        setBackground(v, background);
        v.setSingleLine(true);
        v.setTextSize(40);
        v.setGravity(Gravity.CENTER);
        v.setTextColor(Color.WHITE);
        //Note: shadow doesn't personally look great, but it's easy and looks better than nothing.
        v.setShadowLayer(25f, 5.5f, 5.5f, Color.BLACK);
        v.setText(rock.getName());

        v.setClickable(true);
        final Activity self = this;

        v.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RockDisplayActivity.launchActivity(self, rock);
                    }
                }
        );
        return v;
    }



    @SuppressWarnings("deprecation")
    private void setBackground(TextView view, Drawable background) {
        //Move into a helper function if needed again,
        if (android.os.Build.VERSION.SDK_INT >= 16)
            view.setBackground(background);
        else
            view.setBackgroundDrawable(background);

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
