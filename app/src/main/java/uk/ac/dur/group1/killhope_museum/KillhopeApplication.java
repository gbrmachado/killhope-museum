package uk.ac.dur.group1.killhope_museum;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.List;

import uk.ac.dur.group1.killhope_museum.dto.MapDTO;
import uk.ac.dur.group1.killhope_museum.dto.json.JsonMap;
import uk.ac.dur.group1.killhope_museum.dto.RockDTO;
import uk.ac.dur.group1.killhope_museum.utilities.JSONUtilities;

/**
 * A class designed to hold global variables for the Killhope museum application.
 */
public class KillhopeApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    public List<RockDTO> getRockList()
    {
        return RockListFactory.getRockList(this.getResources());
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
        if(map.getImage() == null || map.getImage() == "")
            return getDefaultMapImage();

        //TODO: Ben
        throw new UnsupportedOperationException("Not implemented - TODO: Ben");
    }

    private Bitmap getDefaultMapImage()
    {
        return BitmapFactory.decodeResource(getResources(), R.drawable.killhope_map);
    }

}
