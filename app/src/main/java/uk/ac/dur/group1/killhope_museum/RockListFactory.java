package uk.ac.dur.group1.killhope_museum;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.ac.dur.group1.killhope_museum.dto.RockDTO;
import uk.ac.dur.group1.killhope_museum.dto.json.JsonRock;
import uk.ac.dur.group1.killhope_museum.dto.json.JsonRockContent;
import uk.ac.dur.group1.killhope_museum.dto.json.JsonRockData;
import uk.ac.dur.group1.killhope_museum.utilities.JSONUtilities;

/**
 * Created by David on 21/03/2015.
 */
public class RockListFactory {
    //As local files are based in resources rather thsn using file paths, use this for local files.
    private static Map<String, Iterable<Integer>> localMap;
    private static Map<String, Integer> animationMap;
    static {
        localMap = new HashMap<>();
        localMap.put("Rock-Ankerite", Arrays.asList(
                R.drawable.ankerite_line,
                R.drawable.ankerite,
                R.drawable.ankerite2,
                R.drawable.ankerite3,
                R.drawable.ankerite4));

        localMap.put("Rock-Aragonite", Arrays.asList(R.drawable.aragonite_line));
        localMap.put("Rock-Baryte", Arrays.asList(R.drawable.baryte_line));
        localMap.put("Rock-Barytocalcite", Arrays.asList(R.drawable.barytocalcite_line));
        localMap.put("Rock-Calcite", Arrays.asList(R.drawable.calcite_line));
        localMap.put("Rock-Cerussite", Arrays.asList(R.drawable.cerussite_line));
        localMap.put("Rock-Fluorite", Arrays.asList(R.drawable.fluorite_line));
        localMap.put("Rock-Galena", Arrays.asList(R.drawable.galena_line));
        localMap.put("Rock-Limonite", Arrays.asList(R.drawable.limonite_line));
        localMap.put("Rock-Quartz", Arrays.asList(R.drawable.quartz_line));
        localMap.put("Rock-Siderite", Arrays.asList(R.drawable.siderite_line));
        localMap.put("Rock-Smithsonite", Arrays.asList(R.drawable.smithsonite_line));
        localMap.put("Rock-Sphalerite", Arrays.asList(R.drawable.sphalerite_line));
        localMap.put("Rock-Witherite", Arrays.asList(R.drawable.witherite_line));

        animationMap = new HashMap<>();
        animationMap.put("Rock-Ankerite", R.raw.rock_gif_ankerite);
    }

    private static RockDTO fromJson(JsonRock rock)
    {
        //TODO: Not handling images yet.
        RockDTO ret = new RockDTO(rock.getUniqueId());

        ret.setFormula(rock.getFormula());
        ret.setDisplayName(rock.getTitle());

        for(JsonRockContent content : rock.getContent())
            ret.addContent(new RockDTO.RockContent(content.getTitle(), content.getData()));

       return ret;
    }

    private static List<RockDTO> fromJson(String json)
    {
        JsonRockData data = JsonRockData.fromJson(json);

        //Convert from JSON to a DTO
        List<RockDTO> rocks = new ArrayList<>();
        for(JsonRock r : data.getRocks())
            rocks.add(fromJson(r));

        return rocks;
    }

    public static List<RockDTO> getRockList(Resources applicationResources) {
        //Load the rocks from the JSON.
        String json = JSONUtilities.getJsonFromRaw(applicationResources, R.raw.rocks);
        List<RockDTO> rocks = fromJson(json);

        //Augment with images if they don't exist.

        for(RockDTO rock : rocks)
        {
            if(rock.getRockListImage() == null) {
                rock.setRockListImage(getLineForRock(applicationResources, rock.getID().toString()));
                setGallery(rock, applicationResources);
                setAnimation(rock, applicationResources);
            }
        }

        return rocks;

    }

    private static void setAnimation(RockDTO rock, Resources applicationResources)
    {
        String key = rock.getID().toString();

        if(key == null || !animationMap.containsKey(key))
            return;

        int id = animationMap.get(key);

        try {
            byte[] gif = IOUtils.toByteArray(applicationResources.openRawResource(id));
            rock.setAnimation(gif);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Bitmap getLineForRock(Resources r, String key)
    {
        //If a rock does not have a "line" image, give them a blank bitmap, otherwise, load from the cache.
        if(localMap.containsKey(key))
            for(Integer b : localMap.get(key))
                return BitmapFactory.decodeResource(r, b);//Return localmp.get().first();

        return  Bitmap.createBitmap(2000, 300, Bitmap.Config.ARGB_8888);
    }


    public static void setGallery(RockDTO rock, Resources res)
    {
        String key = rock.getID().toString();

        if(key == null || !localMap.containsKey(key))
            return;

        boolean skipFirst = true;
        for(Integer b : localMap.get(key))
        {
            if (skipFirst) {
                skipFirst = false;
                continue;
            }
            Bitmap image = BitmapFactory.decodeResource(res, b);
            rock.addImage(image, true);
        }
    }
}
