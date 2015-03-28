package uk.ac.dur.group1.killhope_museum.activity;

import uk.ac.dur.group1.killhope_museum.activity.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import uk.ac.dur.group1.killhope_museum.R;

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

    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The key of the required parcelable for this activity.
     */
    public static final String IMAGE_KEY = "image";

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreenImageActivity.cachedValue = null;

        setContentView(R.layout.activity_full_screen_image);

        final View controlsView = findViewById(R.id.fullscreen_content_controls);//the view to hide.
        final View contentView = findViewById(R.id.full_screen_content_view);

        // Set up an instance of SystemUiHider to control the system UI for
        // this activity.
        mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
        mSystemUiHider.setup();
        mSystemUiHider
                .setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
                    // Cached values.
                    int mControlsHeight;
                    int mShortAnimTime;

                    @Override
                    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
                    public void onVisibilityChange(boolean visible) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                            // If the ViewPropertyAnimator API is available
                            // (Honeycomb MR2 and later), use it to animate the
                            // in-layout UI controls at the bottom of the
                            // screen.
                            if (mControlsHeight == 0) {
                                //fudge factor as in later versions of android, once the navigation
                                //is hidden, the height of the control changes.
                                mControlsHeight = controlsView.getHeight() + 300;

                            }
                            if (mShortAnimTime == 0) {
                                mShortAnimTime = getResources().getInteger(
                                        android.R.integer.config_shortAnimTime);
                            }
                            controlsView.animate()
                                    .translationY(visible ? 0 : mControlsHeight)
                                    .setDuration(mShortAnimTime);
                        } else {
                            // If the ViewPropertyAnimator APIs aren't
                            // available, simply show or hide the in-layout UI
                            // controls.
                            controlsView.setVisibility(visible ? View.VISIBLE : View.GONE);
                        }

                        if (visible && AUTO_HIDE) {
                            // Schedule a hide().
                            delayedHide(AUTO_HIDE_DELAY_MILLIS);
                        }
                    }
                });

        // Set up the user interaction to manually show or hide the system UI.
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TOGGLE_ON_CLICK) {
                    mSystemUiHider.toggle();
                } else {
                    mSystemUiHider.show();
                }
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.

        ImageView close = (ImageView)findViewById(R.id.full_screen_close);
        close.setOnTouchListener(mDelayHideTouchListener);

        final FullScreenImageActivity self = this;
        close.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                self.finish();
            }
        });


        if(cachedValue == null)
        {
            int resourceID = this.getIntent().getIntExtra(IMAGE_KEY, -1);
            if (resourceID == -1)
                throw new IllegalArgumentException("Invalid/no key set: " + IMAGE_KEY);

            cachedValue = BitmapFactory.decodeResource(this.getResources(), resourceID);
        }

        this.loadImage(cachedValue);
    }

    private void loadImage(Bitmap image)
    {
        //convert the image to base64 so we don't need to deal with more problems with relative URLs
        //if we convert this to a server-based interface.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] byteArrayImage = baos.toByteArray();
        String encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);

        WebView v = (WebView)this.findViewById(R.id.full_screen_image_web_view);
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
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);

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
        delayedHide(100);
    }


    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            mSystemUiHider.hide();
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
        super.onDestroy();
        FullScreenImageActivity.cachedValue = null;
    }
}
