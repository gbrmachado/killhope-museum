package uk.ac.dur.group1.killhope_museum;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

import uk.ac.dur.group1.killhope_museum.dto.RockDTO;

/**
 * Created by David on 07/04/2015.
 */
public class RockWithCache extends RockDTO
{
    private List<Integer> gallery = new ArrayList<>();
    private final Resources r;


    public RockWithCache(String uniqueID, Resources r) {
        super(uniqueID);
        this.r = r;
    }

    @Override
    public Iterable<Bitmap> getGallery() {
        List<Bitmap> ret = makeCollection(super.getGallery());

        for(Integer a : gallery)
        {
            ret.add(BitmapFactory.decodeResource(r, a));
        }
        return ret;
    }

    //It's stupid that this isn't in the standard library.
    private static <E> List<E> makeCollection(Iterable<E> iTerrible) {
        List<E> list = new ArrayList<>();
        for (E item : iTerrible) {
            list.add(item);
        }
        return list;
    }

    public void addGalleryImage(Integer b)
    {
        gallery.add(b);
    }
}
