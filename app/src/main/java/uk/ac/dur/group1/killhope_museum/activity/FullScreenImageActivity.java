package uk.ac.dur.group1.killhope_museum.activity;

import uk.ac.dur.group1.killhope_museum.activity.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import uk.ac.dur.group1.killhope_museum.R;
import uk.ac.dur.group1.killhope_museum.utilities.BitmapUtilities;

/**
 * A full-screen activity that displays a specified image.
 *
 * @see SystemUiHider
 */
public class FullScreenImageActivity extends Activity {

    /**
     * A cached value to get around problems with serialising large objects in-between activities.
     * Will only be set between launchActivity() and when the dialog is closed.
     * This is not unset on onCreate() because a screen rotation would leave the value unset.
     */
    private static Bitmap cachedValue = null;

    /**
     * Launches the activity to display the specified bitmap.
     * @param context The context to launch the activity from
     * @param resourceID the id of the resource in the local resources folder.
     */
    public static void launchActivity(Context context, int resourceID)
    {
        if (context == null)
            throw new IllegalArgumentException("context is null");

        Intent myIntent = new Intent(context, FullScreenImageActivity.class);
        myIntent.putExtra(IMAGE_KEY, resourceID);
        context.startActivity(myIntent);
    }


    public static void launchActivity(Context context, Bitmap image)
    {
        if (context == null)
            throw new IllegalArgumentException("context is null");

        Intent myIntent = new Intent(context, FullScreenImageActivity.class);
        FullScreenImageActivity.cachedValue = image;
        context.startActivity(myIntent);
    }

    private static boolean destroyedViaRotation = false;

    /**
     * The number of milliseconds to wait after user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 2000;

    /**
     * The key of the required parcelable for this activity.
     */
    public static final String IMAGE_KEY = "image";

    private int myOrientation;
    private View controlsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final FullScreenImageActivity self = this;

        setContentView(R.layout.activity_full_screen_image);

        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        myOrientation = display.getRotation();


        controlsView = findViewById(R.id.fullscreen_content_controls);

        ImageView close = (ImageView) findViewById(R.id.full_screen_close);


        close.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                self.finish();
            }
        });


        if (cachedValue == null) {
            int resourceID = this.getIntent().getIntExtra(IMAGE_KEY, -1);
            if (resourceID == -1)
                throw new IllegalArgumentException("Invalid/no key set: " + IMAGE_KEY);

            cachedValue = BitmapFactory.decodeResource(this.getResources(), resourceID);
        }

        FrameLayout fscw = (FrameLayout) findViewById(R.id.full_screen_content_view);
        TouchWebView a = new TouchWebView(this);
        fscw.addView(a);
        this.loadImage(a, cachedValue);
    }
    private void loadImage(WebView v, Bitmap image)
    {
        //convert the image to base64 so we don't need to deal with more problems with relative URLs
        //if we convert this to a server-based interface.
        String encodedImage = BitmapUtilities.convertToBase64(image);

        String data = getHTML(encodedImage);
        v.loadData(data, "text/html", "UTF-8");

        setupScrollableCenteredView(v, image);
    }

    private void setupScrollableCenteredView(WebView v, Bitmap image) {
        WebSettings settings = v.getSettings();

        Display display = getWindowManager().getDefaultDisplay();
        int width;
        int height;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point size = new Point();
            display.getSize(size);
            width = size.x;
            height = size.y;
        }
        else {
            width = display.getWidth();
            height = display.getHeight();
        }

        //settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);

        //scroll to the middle.
        //TODO: Check with someone that this is the correct way to do so.
        v.scrollTo((image.getWidth() + width) / 2, (image.getHeight() + height) / 2);

    }

    private String getHTML(String encodedImage)
    {
        String image = "<body><img id='main_image' src='data:image/png;base64," + encodedImage + "'/></body>";

        String css = "<head>" +
                "<style type=\"text/css\">\n" +
                "img\n" +
                "{\n" +
                "    min-height:100%;\n" +
                "    min-width:100%;\n" +
                "    height:auto;\n" +
                "    width:auto;\n" +
                "    position:absolute;\n" +
                "    left:0;\n" +
                "    top:0;\n" +
                "    margin:auto;\n" +
                "}\n" +
                "</style>" +
                "</head>";
        return "<html>" + css + image + "</html>";
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(1000);
    }

    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            controlsView.setVisibility(View.GONE);
        }
    };

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    @Override
    protected void onDestroy() {

        if(!FullScreenImageActivity.destroyedViaRotation)
            FullScreenImageActivity.cachedValue = null;
        FullScreenImageActivity.destroyedViaRotation = false;

        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if(newConfig.orientation != myOrientation)
            FullScreenImageActivity.destroyedViaRotation = true;

        super.onConfigurationChanged(newConfig);
    }


    //http://stackoverflow.com/a/21173178/1112560
    public class TouchWebView extends WebView {

        WebTouchListener wtl;

        public TouchWebView(Context context) {
            super(context);
        }

        public TouchWebView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }

        public TouchWebView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public void resetTouchListener() {

            if(this.wtl == null) {
                this.wtl = new WebTouchListener();
            }
            this.setOnTouchListener(wtl);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent event) {

            this.resetTouchListener();
            return super.dispatchTouchEvent(event);
        }

        public class WebTouchListener implements OnTouchListener {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                WebView.HitTestResult hr = ((TouchWebView)v).getHitTestResult();

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    controlsView.setVisibility(View.VISIBLE);
                    delayedHide(AUTO_HIDE_DELAY_MILLIS);
                }

                // TODO Auto-generated method stub
                return false;
            }


        }
    }
}
