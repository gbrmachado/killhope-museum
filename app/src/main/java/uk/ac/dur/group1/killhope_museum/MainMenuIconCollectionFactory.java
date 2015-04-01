package uk.ac.dur.group1.killhope_museum;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import uk.ac.dur.group1.killhope_museum.activity.QRCodeActivity;
import uk.ac.dur.group1.killhope_museum.activity.RockListActivity;
import uk.ac.dur.group1.killhope_museum.activity.quiz_homeActivity;

/**
 * Created by David on 23/03/2015.
 */
public class MainMenuIconCollectionFactory
{
    private MainMenuIconCollectionFactory() {}

    public static List<MainMenuIcon> getInstance(Context context)
    {
        //Note that the ordering of these matters we want the rock list in the "middle" as its the
        // most important icon.

        //The dimensions
        List<MainMenuIcon> ret = Arrays.asList
        (
            createMuseumIcon(context),
            createQuizIcon(context),
            createRockListIcon(context),
            createMapIcon(context),
            createQRCodeIcon(context)
        );

        //confirm that all icons have the same dimensions.
        //Note that the constructor confirms that the width and height are the same.
        validate(ret);

        return ret;
    }

    private static void validate(Collection<MainMenuIcon> ret) {
        int height = -1;
        for(MainMenuIcon r : ret)
        {
            int iconHeight = r.getIcon().getHeight();
            if(iconHeight != height)
            {
                if(height == -1)
                {
                    height = iconHeight;
                    continue;
                }
                else
                {
                    throw new IllegalStateException("icons are not all the same dimensions.");
                }
            }
        }
    }

    private static MainMenuIcon createRockListIcon(final Context context)
    {
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.main_menu_icon_mineral_list);
        return new MainMenuIcon(new LaunchRockListCommand(context), icon);
    }
    private static MainMenuIcon createQuizIcon(final Context context)
    {
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.main_menu_icon_quiz);
        return new MainMenuIcon(new LaunchQuizCommand(context), icon);
    }
    private static MainMenuIcon createMapIcon(final Context context)
    {
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.main_menu_icon_map);
        return new MainMenuIcon(new LaunchMapCommand(context), icon);
    }
    private static MainMenuIcon createQRCodeIcon(final Context context)
    {
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.main_menu_icon_qrcode);
        return new MainMenuIcon(new LaunchQRCodeCommand(context), icon);
    }
    private static MainMenuIcon createMuseumIcon(final Context context)
    {
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.main_menu_icon_museum);
        return new MainMenuIcon(new LaunchMuseumCommand(context), icon);
    }





    //I can't for the life of me get Anonymous classes from a static context working in Java,
    // this kinda sucks.
    private static class LaunchRockListCommand extends LaunchActivityCommand
    {
        public LaunchRockListCommand(Context context) { super(context); }
        @Override
        public void execute()
        {
            RockListActivity.launchActivity(this.context);
        }
    }
    private static class LaunchQuizCommand extends LaunchActivityCommand
    {
        public LaunchQuizCommand(Context context) { super(context); }
        @Override
        public void execute()
        {
            quiz_homeActivity.launchActivity(this.context);
        }

    }
    private static class LaunchMuseumCommand extends LaunchActivityCommand
{
    public LaunchMuseumCommand(Context context) { super(context); }
    @Override
    public void execute() { Toast.makeText(context, "museum not implemented", Toast.LENGTH_LONG).show(); }
}
    private static class LaunchQRCodeCommand extends LaunchActivityCommand
    {
        public LaunchQRCodeCommand(Context context) { super(context); }
        @Override
        public void execute() { QRCodeActivity.launchActivity(this.context);   }
    }
    private static class LaunchMapCommand extends LaunchActivityCommand
    {
        public LaunchMapCommand(Context context) { super(context); }
        @Override
        public void execute()  {   Toast.makeText(context, "Map not implemented", Toast.LENGTH_LONG).show(); }
    }

    private static abstract class LaunchActivityCommand implements ICommand
    {
        protected final Context context;

        public LaunchActivityCommand(Context context)
        {
            this.context = context;
        }
        @Override
        public abstract void execute();
    }


}
