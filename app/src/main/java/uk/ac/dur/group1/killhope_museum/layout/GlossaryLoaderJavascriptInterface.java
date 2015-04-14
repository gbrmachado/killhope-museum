package uk.ac.dur.group1.killhope_museum.layout;

import android.content.Context;
import android.webkit.JavascriptInterface;

import uk.ac.dur.group1.killhope_museum.activity.GlossaryItemActivity;

public class GlossaryLoaderJavascriptInterface {
    Context self;

    /** Instantiate the interface and set the context */
    public GlossaryLoaderJavascriptInterface(Context c) {
        self = c;
    }

    @JavascriptInterface
    public void loadGlossary(String data) {
        GlossaryItemActivity.launchActivity(self, data);
    }
}
