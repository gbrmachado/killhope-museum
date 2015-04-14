package uk.ac.dur.group1.killhope_museum;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;


import uk.ac.dur.group1.killhope_museum.activity.RockDisplayActivity;
import uk.ac.dur.group1.killhope_museum.activity.RockListActivity;

import uk.ac.dur.group1.killhope_museum.dto.MapDTO;
import uk.ac.dur.group1.killhope_museum.dto.QuizDTO;
import uk.ac.dur.group1.killhope_museum.dto.json.JsonMap;

import uk.ac.dur.group1.killhope_museum.dto.RockDTO;
import uk.ac.dur.group1.killhope_museum.dto.json.JsonQuizWrapper;
import uk.ac.dur.group1.killhope_museum.utilities.JSONUtilities;

import java.util.List;

import uk.ac.dur.group1.killhope_museum.activity.RockListActivity;
import uk.ac.dur.group1.killhope_museum.dto.RockDTO;
import uk.ac.dur.group1.killhope_museum.dto.RockListFacade;

/**
 * A class designed to hold global variables for the Killhope museum application.
 */
public class KillhopeApplication extends Application
{
    private List<RockDTO> rocks = null;
    private Glossary glossary;
    private QuizDTO quiz;
    private Object rockLock = new Object();

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    private void initialise()
    {
        synchronized (rockLock)
        {
            if (rocks == null)
            {
                rocks = RockListFactory.getRockList(this.getResources());
            }
        }
    }


    public List<RockDTO> getRockList()
    {
        initialise();

        return rocks;  //Just hope and pray that the consumer won't mutate them.
    }

    public QuizDTO getQuiz()
    {
        if(this.quiz == null)
        {
            String json = JSONUtilities.getJsonFromRaw(getResources(), R.raw.quiz);
            this.quiz = JsonQuizWrapper.dtoFromJSON(json);
        }
        return this.quiz;
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

    public List<String> getGlossary()
    {
        return makeCollection(getInternalGlossary().getAllGlossaryEntries());
    }

    public Collection<String> getLinkableWords()
    {
        return makeCollection(getInternalGlossary().getAllLinkableWords());
    }

    private Glossary getInternalGlossary()
    {
        if(this.glossary == null)
            this.glossary = GlossaryProvider.getGlossary(getResources());
        return this.glossary;
    }

    public String getGlossaryItem(String name)
    {
        return getInternalGlossary().lookupEntry(name);
    }


    //It's stupid that this isn't in the standard library.
    private static <E> List<E> makeCollection(Iterable<E> iTerrible) {
        List<E> list = new ArrayList<>();
        for (E item : iTerrible) {
            list.add(item);
        }
        return list;
    }
}
