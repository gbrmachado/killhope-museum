package uk.ac.dur.group1.killhope_museum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uk.ac.dur.group1.killhope_museum.dto.RockDTO;

/**
 * Created by David on 21/03/2015.
 */
public class RockListFactory
{
    public static List<RockDTO> getRockList()
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

        for(int i = 0; i < 14; i++)
        {
            rocks.add(new RockDTO(names.get(i), ids.get(i)));
        }
        return rocks;
    }
}
