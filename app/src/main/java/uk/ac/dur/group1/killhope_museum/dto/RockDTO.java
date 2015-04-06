package uk.ac.dur.group1.killhope_museum.dto;

import android.graphics.Bitmap;
import java.util.ArrayList;
import java.util.List;

/**
 * A Data transfer object designed to encapsulate a rock.
 * This functionality has not been implemented intentionally and is intended for Ben to complete.
 */
public class RockDTO
{
    private String displayName;
    private final String uniqueID;
    private String formula;
    private List<RockContent> content = new ArrayList<>();
    private List<Bitmap> potentialImages = new ArrayList<>();
    /**
     * The indexes of the images in the potentialImages collection which will be used for the gallery.
     */
    private List<Integer> galleryImages = new ArrayList<>();


    private Bitmap rockListImage;
    private Bitmap videoAnimation;

    public void addImage(Bitmap image, boolean isGalleryImage)
    {
        potentialImages.add(image);
        if (isGalleryImage)
            galleryImages.add(potentialImages.size() - 1);
    }

    public boolean isGalleryImage(int index)
    {
        return galleryImages.indexOf(index) != -1;
    }

    public void toggleGalleryImage(int index)
    {
        int indexOf = galleryImages.indexOf(index);
        if (indexOf == -1)
            galleryImages.add(new Integer(index));
        else
        {
            galleryImages.remove(indexOf);
        }
    }

    public Iterable<Bitmap> getGallery()
    {
        ArrayList<Bitmap> ret = new ArrayList<>();
        for(int i : galleryImages)
        {
            ret.add(potentialImages.get(i));
        }
        return ret;
    }

    public RockDTO(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    protected RockDTO(RockDTO original)
    {
        displayName = original.displayName;
        uniqueID = original.uniqueID;
        formula = original.formula;
        content = original.content; //currently immutable, so cloning is fine.
        rockListImage = original.rockListImage; //should really clone, but we'll be fine for this app.
        galleryImages = original.galleryImages;
        videoAnimation = original.videoAnimation;
        potentialImages = original.potentialImages;
    }

    public CharSequence getID()
    {
        return this.uniqueID;
    }

    /**
     * @return The human-readable name of the rock.
     */
    public CharSequence getName()
    {
        if(this.displayName == null)
            return this.uniqueID;
        return this.displayName;
    }

    public Bitmap getRockListImage()
    {
         return this.rockListImage;
    }

    public Bitmap getAnimation()
    {
        return this.videoAnimation;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public void addContent(RockContent content)
    {
        this.content.add(content);
    }

    public void addContentAt(RockContent content, int location)
    {
        this.content.add(location, content);
    }

    public void clearContent()
    {
        this.content.clear();
    }

    public void setRockListImage(Bitmap rockListImage) {
        this.rockListImage = rockListImage;
    }

    public Bitmap getVideoAnimation() {
        return videoAnimation;
    }

    public void setVideoAnimation(Bitmap videoAnimation) {
        this.videoAnimation = videoAnimation;
    }

    public Iterable<RockContent> getContent()
    {
        return this.content;
    }

    /**
     * The content and potential heading of one section of content for a rock.
     */
    public static class RockContent
    {
        private final String title;
        private final String data;

        public RockContent(String title, String data) {
            this.title = title;
            this.data = data;
        }

        public String getTitle()
        {
            return title;
        }

        public String getData()
        {
            return data;
        }
    }

}
