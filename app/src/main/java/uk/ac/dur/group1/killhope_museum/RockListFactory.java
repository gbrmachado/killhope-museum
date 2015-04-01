package uk.ac.dur.group1.killhope_museum;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uk.ac.dur.group1.killhope_museum.dto.RockDTO;

/**
 * Created by David on 21/03/2015.
 */
public class RockListFactory
{
    public static List<RockDTO> getRockList(Resources applicationResources)
    {
        //TODO: to be fixed by ben.
        ArrayList<RockDTO> rocks = new ArrayList<>();
        List<Integer> ids = Arrays.asList(
                R.drawable.ankerite_line,
                R.drawable.aragonite_line,
                R.drawable.baryte_line,
                R.drawable.barytocalcite_line,
                R.drawable.calcite_line,
                R.drawable.cerussite_line,
                R.drawable.fluorite_line,
                R.drawable.galena_line,
                R.drawable.limonite_line,
                R.drawable.quartz_line,
                R.drawable.siderite_line,
                R.drawable.smithsonite_line,
                R.drawable.sphalerite_line,
                R.drawable.witherite_line);
        List<String> names = Arrays.asList(
                "Ankerite",
                "Aragonite",
                "Baryte",
                "Barytocalcite",
                "Calcite",
                "Cerussite",
                "Fluorite",
                "Galena",
                "Limonite",
                "Quartz",
                "Siderite",
                "Smithsonite",
                "Sphalerite",
                "Witherite"
        );
        //Leaving the rest for Ben.



        rocks.add(getSpecialCase(applicationResources));
        //TODO: Remainder for Ben.
        for(int i = 1; i < 14; i++)
        {
            RockDTO r = new RockDTO("Rocks-" + names.get(i));
            r.setDisplayName(names.get(i));
            r.setRockListImage(BitmapFactory.decodeResource(applicationResources, ids.get(i)));
            rocks.add(r);
        }
        return rocks;
    }

    private static RockDTO getSpecialCase(Resources applicationResource) {
        RockDTO specialCase = new RockDTO("Rocks-Ankerite");
        specialCase.setFormula("PCa(Mg, Fe2+, Mn)(CO₃)₂");
        specialCase.setRockListImage(BitmapFactory.decodeResource(applicationResource, R.drawable.ankerite_line));
        specialCase.setDisplayName("Ankerite");
        //TODO: Gabriel will need to merge and add these.
        //specialCase.addImage();
        //specialCase.setVideoAnimation();

        specialCase.addContent(new RockDTO.RockContent("","<b>Colour</b>: White/Yellowish white.<br/><b>Abundance</b>: Frequently fills joints in coal seams.<br/><b>Hardness</b>: 3.5 - 4 (Soft - Medium).<br/><b>Lustre</b>: Vitreous to pearly.<br/><b>Ore</b>: Gangue mineral accompanying iron ore.<br/><b>Interesting fact</b>: Darkens with heating and may become magnetic."));
        specialCase.addContent(new RockDTO.RockContent("","<b>Uses</b>: Minor iron ore.<br/><b>Main countries involved in the extraction of mineral</b>: N/A.<br/><b>Crystal Habit</b>: Rhombohedral with curved faces, columnar, granular, massive.<br/><b>Crystal Structure</b>: Trigonal.<br/><b>Depositional Environment</b>: Result of both hydrothermal, low-temperature metasomatism and banded iron formations.<br/><b>Transparency</b>: Translucent - transparent.<br/><b>Origin of Name</b>: Named after M.J.Anker, an Austrian mineralogist.<br/><b>Colours Observed at Killhope</b>: Cream, brown."));
        specialCase.addContent(new RockDTO.RockContent("","<b>Further uses</b>: None.<br/><b>Streak</b>: White.<br/><b>Cleavage</b>: Perfect.<br/><b>Fracture</b>: Subconchoidal."));
        specialCase.addContent(new RockDTO.RockContent("","<b>Specific Gravity</b>: 2.93 - 3.10.<br/><b>Further properties</b>: Colour turns yellowish brown/brown when oxidation of iron has occured.<br/><b>Relevance at Killhope</b>: Large concentration of ankerite accompany galena-sphalerite-fluorite-baryte veins such as in the North Pennines orefield where limestone or quartz dolerite has been replaced.<br/><b>Optical Properties</b>: Increasing replacement of Mg by Fe2+ increases the refractive indices, birefringence and specific gravity."));


        return specialCase;
    }
}
