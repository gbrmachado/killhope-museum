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
                R.drawable.ankerite_1,
                R.drawable.ankerite_2,
                R.drawable.ankerite_3,
                R.drawable.ankerite_4));

        localMap.put("Rock-Aragonite", Arrays.asList(
                R.drawable.aragonite_line,
                R.drawable.aragonite_1,
                R.drawable.aragonite_2,
                R.drawable.aragonite_3,
                R.drawable.aragonite_4));
        localMap.put("Rock-Baryte", Arrays.asList(
                R.drawable.baryte_line,
                R.drawable.baryte_1,
                R.drawable.baryte_2,
                R.drawable.baryte_3,
                R.drawable.baryte_4));
        localMap.put("Rock-Barytocalcite", Arrays.asList(
                R.drawable.barytocalcite_line,
                R.drawable.barytocalcite_1,
                R.drawable.barytocalcite_2,
                R.drawable.barytocalcite_3,
                R.drawable.barytocalcite_4));
        localMap.put("Rock-Calcite", Arrays.asList(
                R.drawable.calcite_line,
                R.drawable.calcite_1,
                R.drawable.calcite_2,
                R.drawable.calcite_3,
                R.drawable.calcite_4));
        localMap.put("Rock-Cerussite", Arrays.asList(
                R.drawable.cerussite_line,
                R.drawable.cerussite_1,
                R.drawable.cerussite_2,
                R.drawable.cerussite_3,
                R.drawable.cerussite_4));
        localMap.put("Rock-Fluorite", Arrays.asList(
                R.drawable.fluorite_line,
                R.drawable.fluorite_1,
                R.drawable.fluorite_2,
                R.drawable.fluorite_3,
                R.drawable.fluorite_4));
        localMap.put("Rock-Galena", Arrays.asList(
                R.drawable.galena_line,
                R.drawable.galena_1,
                R.drawable.galena_2,
                R.drawable.galena_3,
                R.drawable.galena_4));
        localMap.put("Rock-Limonite", Arrays.asList(
                R.drawable.limonite_line,
                R.drawable.limonite_1,
                R.drawable.limonite_2,
                R.drawable.limonite_3,
                R.drawable.limonite_4));
        localMap.put("Rock-Quartz", Arrays.asList(
                R.drawable.quartz_line,
                R.drawable.quartz_1,
                R.drawable.quartz_2,
                R.drawable.quartz_3,
                R.drawable.quartz_4));
        localMap.put("Rock-Siderite", Arrays.asList(
                R.drawable.siderite_line,
                R.drawable.siderite_1,
                R.drawable.siderite_2,
                R.drawable.siderite_3,
                R.drawable.siderite_4));
        localMap.put("Rock-Smithsonite", Arrays.asList(
                R.drawable.smithsonite_line,
                R.drawable.smithsonite_1,
                R.drawable.smithsonite_2,
                R.drawable.smithsonite_3,
                R.drawable.smithsonite_4));
        localMap.put("Rock-Sphalerite", Arrays.asList(
                R.drawable.sphalerite_line,
                R.drawable.sphalerite_1,
                R.drawable.sphalerite_2,
                R.drawable.sphalerite_3,
                R.drawable.sphalerite_4));
        localMap.put("Rock-Witherite", Arrays.asList(
                R.drawable.witherite_line,
                R.drawable.witherite_1,
                R.drawable.witherite_2,
                R.drawable.witherite_3,
                R.drawable.witherite_4));

        animationMap = new HashMap<>();
        animationMap.put("Rock-Ankerite", R.raw.rock_gif_ankerite);
        animationMap.put("Rock-Aragonite", R.raw.rock_gif_aragonite);
        animationMap.put("Rock-Baryte", R.raw.rock_gif_baryte);
        animationMap.put("Rock-Barytocalcite", R.raw.rock_gif_barytocalcite);
        animationMap.put("Rock-Calcite", R.raw.rock_gif_calcite);
        animationMap.put("Rock-Cerussite", R.raw.rock_gif_cerussite);
        animationMap.put("Rock-Fluorite", R.raw.rock_gif_fluorite);
        animationMap.put("Rock-Galena", R.raw.rock_gif_galena);
        animationMap.put("Rock-Limonite", R.raw.rock_gif_limonite);
        animationMap.put("Rock-Quartz", R.raw.rock_gif_quartz);
        animationMap.put("Rock-Siderite", R.raw.rock_gif_siderite);
        animationMap.put("Rock-Smithsonite", R.raw.rock_gif_smithsonite);
        animationMap.put("Rock-Sphalerite", R.raw.rock_gif_sphalerite);
        animationMap.put("Rock-Witherite", R.raw.rock_gif_witherite);
    }

    private static RockDTO fromJson(JsonRock rock, Resources r)
    {
        //TODO: Not handling images yet.
        RockDTO ret = new RockWithCache(rock.getUniqueId(), r);

        ret.setFormula(rock.getFormula());
        ret.setDisplayName(rock.getTitle());

        for(JsonRockContent content : rock.getContent())
            ret.addContent(new RockDTO.RockContent(content.getTitle(), content.getData()));

       return ret;
    }

    private static List<RockDTO> fromJson(String json, Resources res)
    {
        JsonRockData data = JsonRockData.fromJson(json);

        //Convert from JSON to a DTO
        List<RockDTO> rocks = new ArrayList<>();
        for(JsonRock r : data.getRocks())
            rocks.add(fromJson(r, res));

        return rocks;
    }

    public static List<RockDTO> getRockList(Resources applicationResources) {
        //Load the rocks from the JSON.
        String json = JSONUtilities.getJsonFromRaw(applicationResources, R.raw.rocks);
        List<RockDTO> rocks = fromJson(json, applicationResources);

        //Augment with images if they don't exist.

        for(RockDTO rock : rocks)
        {

            if(rock.getRockListImage() == null) {
                rock.setRockListImage(getLineForRock(applicationResources, rock.getID().toString()));
                //We can guarantee that this will be true as we create them ourselves.
                setGallery((RockWithCache)rock, applicationResources);
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


    public static void setGallery(RockWithCache rock, Resources res)
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
            rock.addGalleryImage(b);
        }
    }
}
