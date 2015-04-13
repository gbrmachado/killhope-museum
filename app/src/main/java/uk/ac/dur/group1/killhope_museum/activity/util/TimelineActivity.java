package uk.ac.dur.group1.killhope_museum.activity.util;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import uk.ac.dur.group1.killhope_museum.R;
import uk.ac.dur.group1.killhope_museum.activity.FullScreenImageActivity;


public class TimelineActivity extends ActionBarActivity {

    public static void launchActivity(Context context)
    {
        if(context == null)
            throw new IllegalArgumentException("context is null");

        Intent i = new Intent(context, TimelineActivity.class);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Context context = this;

        setContentView(R.layout.activity_timeline);

        TextView content = (TextView) findViewById(R.id.contentText);
        content.setMovementMethod(new ScrollingMovementMethod());

        Button earlyEarth = (Button) findViewById(R.id.earlyEarth);
        earlyEarth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout contentLayout = (LinearLayout) findViewById(R.id.contentLayout);
                contentLayout.removeAllViews();
                TextView content = new TextView(context);
                contentLayout.addView(content);
                content.setText("About 450 million years ago, during the Ordovician period of Earth history, the area we know as northern England was located 15°S of the equator (Matte, 2001). Here it lay beneath a deep ocean, in which mud and silt were deposited.\n" +
                        "\n" +
                        "As geological time progressed, vast movements of the Earth’s tectonic plates compressed these muds, turning them into slate and forming the Skiddaw Group. (These same movements causes gigantic volcanoes to erupt lava and ash.)\n");
            }
        });

        Button theWeardaleGranite = (Button) findViewById(R.id.theWeardaleGranite);
        theWeardaleGranite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout contentLayout = (LinearLayout) findViewById(R.id.contentLayout);
                contentLayout.removeAllViews();
                TextView content = new TextView(context);
                contentLayout.addView(content);
                content.setText("Movement of the Earths plates caused compression in the North East of England, beginning at around 490 million years ago.\n" +
                        "\n" +
                        "Approximately 400 million years ago a large mass of magma (molten rock) was injected into the Ordovician slates and volcanic rocks. This cooled and crystallised, forming the Weardale Granite.\n" +
                        "\n" +
                        "An unconformity lies above the granite, a time gap in the rock record caused by millions of years of erosion of the overlying rocks. After this significant ‘time gap’ the Carboniferous rocks that make up most of the Northern Pennines were deposited on top of the granite.\n");
            }
        });

        Button carboniferousSediments = (Button) findViewById(R.id.carboniferousSediments);
        carboniferousSediments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout contentLayout = (LinearLayout) findViewById(R.id.contentLayout);
                contentLayout.removeAllViews();
                TextView content = new TextView(context);
                contentLayout.addView(content);
                content.setText("In the Early Carboniferous movement of the Earths tectonic plates  opened up sedimentary basins in Northumberland and Yorkshire. This allowed the deposition of the Carboniferous sedimentary rocks we observe today during the period from 354-300 million years ago.\n" +
                        "\n" +
                        "During this time, the North Pennines was drifting north across the equator and was episodically submerged beneath shallow tropical seas, similar to that around the present day Bahamas.\n" +
                        "\n" +
                        "Skeletons of sea creatures accumulated as calcitic ooze on the seafloor, whilst rivers fed mud and sand particles into the sea, building up vast deltas on which swampy forests grew. Over millions of years, the calcitic ooze, mud, sand and wood rich swamps compacted into layers of limestone, shale, sandstone and coal.\n" +
                        "\n" +
                        "Fluctuating sea levels covered and exposed the deltas, allowing for a cyclic pattern of sedimentary rock to form. Each cycle of limestone, shale, sandstone and coal is referred to as a cyclothem.\n");
            }
        });

        Button formationOfTheNorthPennineOreField = (Button) findViewById(R.id.formationOfTheNorthPennineOreField);
        formationOfTheNorthPennineOreField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout contentLayout = (LinearLayout) findViewById(R.id.contentLayout);
                contentLayout.removeAllViews();
                TextView content = new TextView(context);
                contentLayout.addView(content);
                content.setText("At great depths in the Earth, rocks can be saturated with saline water, very much like sea water, this often dissolves chemical elements from the surrounding rocks which contain traces or iron, lead, zinc and the various elements found in Northern Pennines mineral deposits.\n" +
                        "\n" +
                        "The Weardale granite has been a powerful heat source since it was first deposited 400 Million years ago, and is still appreciably hotter than the surrounding rocks. \n" +
                        "The original geological model for the North Pennine ore field hypothesised that residual heat from the Weardale granite caused heating of groundwater within the overlying carboniferous sediments, as shown in the diagram above. Heating remobilised the fluids, causing them to circulate upwards through cracks in the overlying rocks. This process created a vacuum effect, in turn sourcing fluids from the surrounding Ordovician sediments.These fluids carried dissolved minerals, which were eventually precipitated as the fluids cooled and produced the mineral deposits that we see in the Killhope area today. \n");
            }
        });

        Button newGeologicalModel = (Button) findViewById(R.id.newGeologicalModel);
        newGeologicalModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout contentLayout = (LinearLayout) findViewById(R.id.contentLayout);
                contentLayout.removeAllViews();
                TextView content = new TextView(context);
                contentLayout.addView(content);
                content.setText("New geological dating methods have shown that the minerals originally thought to be sourced from the Ordovician sediments, are in fact Early Permian in age. This suggests that the minerals were in fact sourced from the Whin Sill, a Dolerite intrusion thought to be approximately 300 Million years old. The Whin Sill intruded the North Pennine area after the Variscan Orogeny. The Variscan Orogeny refers to a great movement of tectonic plates that eventually led to the amalgamation of continents to make the supercontinent Pangea. The after effect of the Variscan event led to extension across England, giving the opportunity for the Whin Sill to intrude, and supply the mineralising fluids for the North Pennine ore field.\n" +
                        "\n" +
                        "Remobilisation of fluids did occur as a result of heating from the weardale granite, although this was not the source of the mineralisation as originally thought.");
            }
        });

        Button advancedTectonicHistory = (Button) findViewById(R.id.advancedTectonicHistory);
        advancedTectonicHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout contentLayout = (LinearLayout) findViewById(R.id.contentLayout);
                contentLayout.removeAllViews();
                final ImageView content = new ImageView(context);
                content.setImageResource(R.drawable.advanced_tectonic_history);
                contentLayout.addView(content);
                content.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        FullScreenImageActivity.launchActivity(context, R.drawable.advanced_tectonic_history);
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }
}
