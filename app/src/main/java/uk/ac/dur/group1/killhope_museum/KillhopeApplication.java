package uk.ac.dur.group1.killhope_museum;

import android.app.Application;

import java.util.List;

import uk.ac.dur.group1.killhope_museum.activity.RockListActivity;
import uk.ac.dur.group1.killhope_museum.dto.RockDTO;
import uk.ac.dur.group1.killhope_museum.dto.RockListFacade;

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


}
