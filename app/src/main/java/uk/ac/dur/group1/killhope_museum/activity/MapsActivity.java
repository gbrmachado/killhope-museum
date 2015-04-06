package uk.ac.dur.group1.killhope_museum.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.List;

import uk.ac.dur.group1.killhope_museum.KillhopeApplication;
import uk.ac.dur.group1.killhope_museum.R;
import uk.ac.dur.group1.killhope_museum.dto.MapDTO;
import uk.ac.dur.group1.killhope_museum.dto.json.JsonMap;
import uk.ac.dur.group1.killhope_museum.utilities.BitmapUtilities;

public class MapsActivity extends ActionBarActivity
{
    private static final String MAP_NAME = "imap";
    private MapDTO map;


    public static void launchActivity(Context context)
    {
        if(context == null)
            throw new IllegalArgumentException("context is null");

        Intent i = new Intent(context, MapsActivity.class);
        context.startActivity(i);
    }

    protected WebView getWebView()
    {
        return (WebView) this.findViewById(R.id.maps_web_view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        final MapsActivity self = this;
        try {
            setupMap();
        } catch (Exception e)
        {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Error creating maps: " + e.getMessage())
                    .setCancelable(false).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    self.finish();
                }
            })
                    .create().show();
        }
    }

    private KillhopeApplication getKillhopeApplication()
    {
        return (KillhopeApplication) getApplication();
    }

    private MapDTO getMap()
    {
        if(this.map == null)
        {
            KillhopeApplication app = getKillhopeApplication();
            this.map = app.getMap();
        }

        return this.map;
    }

    private void setupMap()
    {
        WebView mapView = getWebView();

        WebSettings settings = mapView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);

        mapView.addJavascriptInterface(new WebAppInterface(this), "Android");

        String data = String.format("<!DOCTYPE html>\n<html>\n<head>" +
                "<script type=\"text/javascript\">\n" +
                "alert('hello');\n" +
                "function load(s)\n" +
                "{\n" +
                "   Android.loadData(s);\n" +
                "}\n" +
                "</script>\n" +
                "</head>\n" +
                " <body>%s\n" +
                "</body>\n" +
                "</html>", toImageMap(this.getMap()));
        getWebView().loadData(data, "text/html", "UTF-8");


    }

    public String toImageMap(MapDTO mapData)
    {
        double scaleFactor = mapData.getScaleFactor();
        String base64 = BitmapUtilities.convertToBase64(mapData.getImage(), Bitmap.CompressFormat.PNG);
        String image = String.format("<img src='data:image/png;base64,%s' usemap='#%s'/>", base64, MAP_NAME);

        String imap = String.format("<map name='%s'>", MAP_NAME);

        for(MapDTO.RoomDTO room : mapData.getRooms())
        {
            imap += getImageMapArea(room, scaleFactor);
        }
        imap += "</map>";
        String ret = image + imap;
        return ret;

    }

    private String getImageMapArea(MapDTO.RoomDTO room, double scaleFactor)
    {
        //x1, y1 ... xn yn
        String coords = "";

        for(Point point : room.getCoordinates())
        {
            coords += String.format("%d,%d",(int)( point.x / scaleFactor),(int)( point.y / scaleFactor));
            coords += ",";
        }

        coords = coords.substring(0, coords.length() - 1);

        return String.format("<area href='javascript:load(\"%s\")' shape='poly' coords='%s'/>", room.getId(), coords);
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

    public class WebAppInterface {
        MapsActivity self;

        /** Instantiate the interface and set the context */
        WebAppInterface(MapsActivity c) {
            self = c;
        }

        @JavascriptInterface
        public void loadData(String data) {
            MapDTO.RoomDTO room = map.getRoom(data);
            if (room.hasRockFilter()) {
                Iterable<String> rockList = room.getRocks();
                RockListActivity.launchActivity(self, rockList);
            } else
            {
                RockListActivity.launchActivity(self);
            }
        }
    }
}
