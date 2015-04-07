package uk.ac.dur.group1.killhope_museum;

import android.content.res.Resources;

import java.util.HashMap;
import java.util.Map;

import uk.ac.dur.group1.killhope_museum.dto.json.JsonGlossary;
import uk.ac.dur.group1.killhope_museum.dto.json.JsonGlossaryAlias;
import uk.ac.dur.group1.killhope_museum.dto.json.JsonGlossaryItem;
import uk.ac.dur.group1.killhope_museum.utilities.JSONUtilities;

/**
 * Created by David on 06/04/2015.
 */
public class GlossaryProvider
{
    private GlossaryProvider() {}

    public static Glossary getGlossary(Resources resources)
    {
        String json = JSONUtilities.getJsonFromRaw(resources, R.raw.glossary);
        JsonGlossary g = JsonGlossary.fromJson(json);

        Glossary ret = new Glossary();

        for(JsonGlossaryItem item : g.getGlossary())
        {
            String key = item.getKey();
            if(key == null)
                continue;
            ret.addEntry(key, item.getValue()); //This should fail if more than 1 key is used.
        }

        //Be lazy: for aliases, instead of referencing the string, clone everything.
        for(JsonGlossaryAlias aliasList : g.getAliases())
            ret.addAliases(aliasList.getTerm(), aliasList.getAliases());


        return ret;
    }


}
