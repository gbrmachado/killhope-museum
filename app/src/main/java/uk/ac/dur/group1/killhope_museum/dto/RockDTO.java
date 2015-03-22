package uk.ac.dur.group1.killhope_museum.dto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import uk.ac.dur.group1.killhope_museum.KillhopeApplication;
import uk.ac.dur.group1.killhope_museum.R;

/**
 * A Data transfer object designed to encapsulate a rock.
 * This functionality has not been implemented intentionally and is intended for Ben to complete.
 */
public class RockDTO
{
    public static int counter = 0;
    private String name;

    int pcounter;

    Integer resource_id; //TODO: remove;
    public RockDTO()
    {
        this.pcounter = counter++;
    }

    public RockDTO(String name, int rid)
    {
        this.name = name;
        resource_id = rid;
    }

    protected RockDTO(RockDTO original)
    {
        resource_id = original.resource_id;
        name = original.name;
    }

    public CharSequence getID()
    {
        //this is wrong.
        if(name != null)
            return "Rock-" + name;
        return "" + pcounter;
    }

    /**
     * @return The human-readable name of the rock.
     */
    public CharSequence getName()
    {
        if(name != null)
            return name;
        return "TODO: Rock + " + pcounter;
    }

    public Bitmap getRockListImage()
    {
         return BitmapFactory.decodeResource(KillhopeApplication.doNotUSE.getResources(), resource_id == null ? R.drawable.ankerite_line : resource_id);
    }
}
