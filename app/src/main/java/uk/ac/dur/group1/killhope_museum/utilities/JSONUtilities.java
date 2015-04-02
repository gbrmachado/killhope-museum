package uk.ac.dur.group1.killhope_museum.utilities;

import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by David on 01/04/2015.
 */
public class JSONUtilities
{
    private JSONUtilities() {}

    public static String getJsonFromRaw(Resources resources, int id)
    {
        try
        {
            return getJsonFromRawThrow(resources, id);
        }
        catch (IOException e)
        {
            throw new IllegalStateException(String.format("Invalid JSON for resource ID: %d", id), e);
        }
    }

    private static String getJsonFromRawThrow(Resources resources, int id) throws IOException {
        InputStream is = resources.openRawResource(id);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
            is.close();
        }

       return writer.toString();
    }
}
