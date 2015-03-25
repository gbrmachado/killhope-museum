package uk.ac.dur.group1.killhope_museum.dto;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Collection;

import uk.ac.dur.group1.killhope_museum.utilities.BitmapUtilities;


/**
 * A facade over a collection of rocks designed to expose properties useful to the visual display
 * of the collection.
 */
public class RockListFacade
{
    public static final int MINIMUM_HEIGHT = 100;

    //There are a lot of considerations that I'm ignoring (for example updates/changes of height)
    // as realistically, this won't happen
    private final Collection<RockDTO> rocks;

    public RockListFacade() { this(new ArrayList<RockDTO>()); }

    public RockListFacade(Collection<RockDTO> rocks) {
        if(rocks == null)
            throw new IllegalArgumentException("rocks is null");

        this.rocks = rocks;
    }

    /**
     * @return The width of the shortest side (or -1 if there are no rocks in the collection).
     */
    public int getMinimumWidth()
    {
        if(isEmpty())
            return -1;

        int ret = Integer.MAX_VALUE;

        for(RockDTO r : rocks)
        {
            int width = r.getRockListImage().getWidth();
            if(width < ret)
                ret = width;
        }

        return ret;
    }

    /**
     * @return The height of the shortest side (or -1 if there are no rocks in the collection).
     */
    public int getMinimumHeight()
    {
        if(isEmpty())
            return -1;

        int ret = Integer.MAX_VALUE;

        for(RockDTO r : rocks)
        {
            int height = r.getRockListImage().getHeight();
            if(height < ret)
                ret = height;
        }

        return ret;
    }

    /**
     * @return The height of the image to be displayed.
     */
    public int getHeight(int screenWidth)
    {
        //The images are intended to be displayed across the whole screen, the source images are of
        //different widths, and if there is a small image, this may cause a problem.

        int minimumWidth = getMinimumWidth();
        if (screenWidth < minimumWidth)
        {
            //The width of the screen is not wider than any of the images.
            //We may need to scale up if MINIMUM_HEIGHT < getMinimumHeight().
            //Note that if we have a smaller height, we will crop rather than scale down to avoid
            //aspect ratio issues.
            return MINIMUM_HEIGHT;
        }

        //Otherwise, we will need to scale up, possibly in 2 dimensions, determine whether this is the case.
        int minimumHeight = getMinimumHeight();
        if(minimumHeight >= MINIMUM_HEIGHT)
        {
            //in this case, we only need to scale up in the X direction.
            double scaleFactor = (double) screenWidth / (double) minimumWidth;
            return (int) Math.ceil(MINIMUM_HEIGHT * scaleFactor);
        }

        //There's a more mathematical way to accomplish this, but not worth the extra complexity to
        //shave milliseconds off the algorithm.

        //Determine the height required to make each image reach the proper width, return the maximum.
        int height = MINIMUM_HEIGHT;
        for(RockDTO rock : this.rocks)
        {
            int newHeight = getHeightNeededForWidth(rock, screenWidth);
            height = Math.max(height, newHeight);
        }

        return height;
    }

    private int getHeightNeededForWidth(RockDTO rock, int width)
    {
        double aspectRatio = BitmapUtilities.getAspectRatio(rock.getRockListImage());
        return (int) ((double)width/aspectRatio);
    }



    public int size()
    {
        return this.rocks.size();
    }

    public boolean isEmpty()
    {
        return size() == 0;
    }


    public Collection<RockDTO> getRocksForScreenSize(int screenWidth)
    {
        int height = getHeight(screenWidth);
        ArrayList<RockDTO> ret = new ArrayList<>();
        for(RockDTO r : rocks)
        {
            ret.add(new RockListRock(r, height, screenWidth));
        }

        return ret;
    }

    /**
     * Performs image scaling for a rock in the overall list.
     */
    public class RockListRock extends RockDTO
    {

        private final int height;
        private final int width;
        private Bitmap imageCache;

        public RockListRock(RockDTO rock, int height, int width)
        {
            super(rock);
            this.height = height;
            this.width = width;
        }

        @Override
        public Bitmap getRockListImage()
        {
            //It was deemed easier to overlay a textView than to modify the underlying bitmap to add
            //The name in text, this may want to be changed at a later date, and this is a good point
            //to do so.
            if(imageCache == null)
                imageCache = BitmapUtilities.scaleTo(super.getRockListImage(), height, width);

            return imageCache;
        }
    }
}
