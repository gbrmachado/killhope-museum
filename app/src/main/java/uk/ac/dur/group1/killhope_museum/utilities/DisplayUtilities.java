package uk.ac.dur.group1.killhope_museum.utilities;

import android.content.Context;
import android.util.DisplayMetrics;

public class DisplayUtilities {
    private DisplayUtilities() {}

    public static int getScreenWidth(Context context)
    {
        return getDisplayMetrics(context).widthPixels;
    }

    public static int getScreenHeight(Context context)
    {
        return getDisplayMetrics(context).heightPixels;
    }

    public static DisplayMetrics getDisplayMetrics(Context context)
    {
        return context.getResources().getDisplayMetrics();
    }
}
