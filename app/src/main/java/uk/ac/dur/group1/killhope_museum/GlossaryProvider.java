package uk.ac.dur.group1.killhope_museum;

import android.content.res.Resources;

import java.util.HashMap;
import java.util.Map;

import uk.ac.dur.group1.killhope_museum.dto.json.JsonGlossary;
import uk.ac.dur.group1.killhope_museum.dto.json.JsonGlossaryItem;
import uk.ac.dur.group1.killhope_museum.utilities.JSONUtilities;

/**
 * Created by David on 06/04/2015.
 */
public class GlossaryProvider
{
    private GlossaryProvider() {}

    public static Map<String, String> getGlossary(Resources resources)
    {
        String json = JSONUtilities.getJsonFromRaw(resources, R.raw.glossary);
        JsonGlossary g = JsonGlossary.fromJson(json);

        HashMap<String,String> ret = new HashMap<>();
        for(JsonGlossaryItem item : g.getGlossary())
        {
            String key = item.getKey();
            if(key == null)
                continue;
            ret.put(key, item.getValue()); //This should fail if more than 1 key is used.
        }

        return ret;
    }


}
