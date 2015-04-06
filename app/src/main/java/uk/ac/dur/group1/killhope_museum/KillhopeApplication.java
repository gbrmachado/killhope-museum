package uk.ac.dur.group1.killhope_museum;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.List;
import java.util.Map;


import uk.ac.dur.group1.killhope_museum.activity.RockDisplayActivity;
import uk.ac.dur.group1.killhope_museum.activity.RockListActivity;

import uk.ac.dur.group1.killhope_museum.dto.MapDTO;
import uk.ac.dur.group1.killhope_museum.dto.json.JsonMap;

import uk.ac.dur.group1.killhope_museum.dto.RockDTO;
import uk.ac.dur.group1.killhope_museum.utilities.JSONUtilities;

/**
 * A class designed to hold global variables for the Killhope museum application.
 */
public class KillhopeApplication extends Application
{
    private List<RockDTO> rocks = null;
    private Map<String, String> glossary;

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    private void initialise()
    {

        if(rocks == null)
            rocks = RockListFactory.getRockList(this.getResources());
    }


    public List<RockDTO> getRockList()
    {
        initialise();

        return rocks;  //Just hope and pray that the consumer won't mutate them.
    }




    public boolean isValidRockID(String id)
    {
        initialise();
        if(id == null)
            return false;

        for(RockDTO rock : rocks)
        {
            if(rock == null)
                continue;
            String obtainedID = rock.getID().toString();
            if (id.equals(obtainedID)) //id is guaranteed not to be null.
                return true;
        }
        return false;
    }

    public RockDTO getRock(String id) {
        initialise();
        if (id == null)
            throw new IllegalArgumentException("id is null");

        for (RockDTO rock : rocks) {
            if (rock == null)
                continue;
            String obtainedID = rock.getID().toString();
            if (id.equals(obtainedID)) //id is guaranteed not to be null.
                return rock;
        }

        throw new IllegalStateException("rock not found");
    }

    public MapDTO getMap()
    {
        String json = JSONUtilities.getJsonFromRaw(getResources(), R.raw.maps);
        JsonMap map =  JsonMap.fromJson(json);

        Bitmap image = imageforMap(map);
        return new MapDTO(map,image);
    }

    private Bitmap imageforMap(JsonMap map)
    {
        if(map.getImage() == null || map.getImage().length() == 0)
            return getDefaultMapImage();

        //TODO: Ben
        throw new UnsupportedOperationException("Not implemented - TODO: Ben");
    }

    private Bitmap getDefaultMapImage()
    {
        return BitmapFactory.decodeResource(getResources(), R.drawable.killhope_map);
    }

    public Map<String, String> getGlossary()
    {
        if(this.glossary == null)
            this.glossary = GlossaryProvider.getGlossary(getResources());
        return this.glossary;
    }
}
