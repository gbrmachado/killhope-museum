package uk.ac.dur.group1.killhope_museum.activity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.Collection;
import java.util.List;

import uk.ac.dur.group1.killhope_museum.MainMenuIcon;
import uk.ac.dur.group1.killhope_museum.MainMenuIconCollectionFactory;
import uk.ac.dur.group1.killhope_museum.R;
import uk.ac.dur.group1.killhope_museum.utilities.DisplayUtilities;

public class MainMenuActivity extends ActionBarActivity {

    /**
     * The minimum padding between each menu icon
     */
    private static final int MINIMUM_PADDING = 15;
    /**
     * The number of icons in the X and Y direction.
     */
    private static final int ICON_COUNT = 3;

    public static void launchActivity(Context context)
    {
        if(context == null)
            throw new IllegalArgumentException("context is null");

        Intent i = new Intent(context, MainMenuActivity.class);
        context.startActivity(i);
    }

    private List<MainMenuIcon> getIcons()
    {
        return MainMenuIconCollectionFactory.getInstance(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        List<MainMenuIcon> icons = getIcons();
        if(icons.size() != 5)
            throw new IllegalStateException("5 icons were not supplied");

        //all icons should have consistent height and width (due to constraints added by the factory).
        int iconDimensions = icons.get(0).getDimensions();

        DisplayMetrics metrics = DisplayUtilities.getDisplayMetrics(this);
        int screenHeight = metrics.heightPixels;
        int screenWidth = metrics.widthPixels;

        //we want 5 icons, in the same layout as they would be on a dice, therefore we want a table
        //layout, with each cell being the same width and height.

        //we may need to scale down the icons, determine if this is the case.

        int totalIconHeightRequired = Math.min(screenHeight, screenWidth) - (MINIMUM_PADDING * 6);
        int iconHeightRequired = totalIconHeightRequired / ICON_COUNT;

        int dimensionsToUse = Math.min(iconDimensions, iconHeightRequired);

        drawIcons(dimensionsToUse, icons);


    }

    protected void drawIcons(int iconDimensions, List<MainMenuIcon> icons)
    {
        //TODO: iconDimensions
        ImageView topLeft = (ImageView) this.findViewById(R.id.main_menu_top_left_image);
        ImageView topRight = (ImageView) this.findViewById(R.id.main_menu_top_right_image);
        ImageView center = (ImageView) this.findViewById(R.id.main_menu_center_image);
        ImageView bottomLeft = (ImageView) this.findViewById(R.id.main_menu_bottom_left_image);
        ImageView bottomRight = (ImageView) this.findViewById(R.id.main_menu_bottom_right_image);

        int id = 0;

        setIcon(topLeft, icons.get(id++), iconDimensions);
        setIcon(topRight, icons.get(id++), iconDimensions);
        setIcon(center, icons.get(id++), iconDimensions);
        setIcon(bottomLeft, icons.get(id++), iconDimensions);
        setIcon(bottomRight, icons.get(id++), iconDimensions);
    }

    private void setIcon(ImageView view, final MainMenuIcon icon, int iconDimensions)
    {
        view.setImageBitmap(icon.getScaledBitmap(iconDimensions));
        view.setPadding(MINIMUM_PADDING,MINIMUM_PADDING,MINIMUM_PADDING,MINIMUM_PADDING);
        view.setClickable(true);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                icon.getCommand().execute();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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
