package uk.ac.dur.group1.killhope_museum.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import uk.ac.dur.group1.killhope_museum.R;

public class MuseumPage extends ActionBarActivity {
    private static final int FOOTER_HEIGHT = 300;
    private int imagesDraw[] = {R.drawable.killhopepage1, R.drawable.killhopepage2, R.drawable.killhopepage3, R.drawable.killhopepage4};
    private List<Bitmap> imagesBitmap = new ArrayList<>();

    String title = "Killhope Museum";
    String info = "<b> North of England Lead Mining Museum</b><br/>\n" +
            "<p>\n" +
            "The North of England Lead Mining Museum, also known as Killhope, is an industrial museum near the village of Cowshill, County Durham, England. The museum stands on the site of the former Park Level Mine, which is being restored to show the workings of a 19th-century lead mine.\n" +
            "</p>\n" +
            "<b> Location</b><br>\n" +
            "<p>\n" +
            "The museum is located alongside the Killhope Burn, about 4 km upstream from Cowshill, and is accessible via the A689 road between Stanhope, County Durham and Alston, Cumbria.\n" +
            "\n" +
            "It is situated in the heart of the North Pennines Area of Outstanding Natural Beauty, an area that, in 2003, was designated the first Geopark in Great Britain.\n" +
            "\n" +
            "The museum is open every day between 1 April and 31 October, but is closed (except to pre-booked groups) during the winter months, when weather conditions in the area can be severe. A bus service operates through Weardale; services usually terminate at Cowshill, but some summer services will continue to Killhope on request.\n" +
            "</p>\n" +
            "\n" +
            "<b>History</b>\n" +
            "<p>\n" +
            "Lead ore in the North Pennines occurs in mineralised veins within the Carboniferous rocks of the area. Until the mid-19th century, exploitation of these ore bodies was mostly confined to surface excavations and vertical shafts. From 1818, mining in the area was controlled by W B Lead Co, a mining company established by the Blacketts, a prominent Newcastle family which had leased mining rights in Weardale from the Bishop of Durham.\n" +
            "\n" +
            "In 1853, W B Lead began driving the Park Level Mine, which eventually intersected 11 mineral veins. As the mine developed, so also did the surface workings. In 1858 a \"mineshop\" was built to accommodate the miners; the population density in such a remote area was very low and, until then, miners had been faced with a long daily walk to and from the mine. In 1862, storage bays (\"bouse teams\") were constructed, to store the raw lead ore (the \"bouse\"), and washing rakes were installed, in which water was used to separate the lead ore in the bouse from the waste material. In 1878, soon after the mine struck the richest of the veins, the Park Level Mill was brought into operation, to speed up the process of washing the ore. The main feature of the mill was a large waterwheel, the \"Killhope Wheel\".\n" +
            "\n" +
            "Not long after the Park Level Mill came into use, the price of lead plummeted, rendering lead-mining in Weardale uneconomic and, in 1883, W B Lead closed all its operations in the district. The Park Level Mine was taken over by another company, Weardale Lead, which continued to operate it until 1910, when production ceased. The mine was re-opened briefly in 1916, during the First World War, after which it lay derelict for over 60 years, during which time the buildings crumbled and any equipment that could be removed was salvaged for scrap.\n" +
            "\n" +
            "Between 1818 and 1883, records show that W B Lead extracted over 31,200 tonnes of lead concentrates from the Killhope operations; between 1884 and 1916, Weardale Lead extracted a further 9,000 tonnes. Taking in the period before 1818, for which there are no records, it is thought that total output from Killhope may have exceeded 60,000 tonnes. In addition, 180 tonnes of zinc concentrates were recovered in the 1950s by treatment of some of the waste material.\n" +
            "</p>\n" +
            "<b>Reconstruction</b>\n" +
            "<p>\n" +
            "By 1980, the Killhope Wheel was facing demolition, the washing floor had become a marshy field, and the rest of the site was decaying. In that year, Durham County Council took over the site and began a programme of restoration. First to be restored was the \"mineshop\", which was opened to the public in May 1984. The Killhope Wheel was restored to working order in 1991 and the mine itself was opened in 1996.\n" +
            "\n" +
            "It had been hoped that the existing mine could be restored to allow access by the public, but though the Park Level was found to be generally in sound condition, and the first 100 metres has been utilised as an access route, the area in the vicinity of the first vein workings was badly collapsed and unsafe. Reconstruction therefore consisted in constructing a new 'artificial' mine within a chamber that was excavated from the surface. Within this 'artificial' mine, the rock surfaces are actually fibreglass casts, taken not just from Killhope but also from mines in the Nenthead district in nearby Cumbria. Despite their artificial nature, the casts faithfully represent the appearance and texture of the real rock.\n" +
            "</p>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_page);

        //generate a list of bitmaps
        for (int i=0; i< imagesDraw.length; i++) {
            imagesBitmap.add(BitmapFactory.decodeResource(getResources(), imagesDraw[i]));
        }

        setupGalleryFooter();

        getSupportActionBar().setTitle(title);    //set the title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab_gradient)); //set the background to the actionbar

        WebView museumWebView = (WebView) findViewById(R.id.museumContent);

        museumWebView.getSettings().setDefaultTextEncodingName("utf-8");
        museumWebView.getSettings().setJavaScriptEnabled(true);
        museumWebView.setBackgroundColor(0x00000000);   //set the transparent background
        museumWebView.loadData(info, "text/html", "UTF-8");
    }

    public static void launchActivity(Context context)
    {
        if(context == null)
            throw new IllegalArgumentException("context is null");

        Intent i = new Intent(context, MuseumPage.class);
        context.startActivity(i);
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

    private void setupGalleryFooter() {
        LinearLayout imageGallery = (LinearLayout) findViewById(R.id.mygalleryMuseum);
        final MuseumPage self = this;
        for (final Bitmap bm : imagesBitmap) {

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

}
