package uk.ac.dur.group1.killhope_museum.utilities;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by David on 21/03/2015.
 */
public class BitmapUtilities
{
    private BitmapUtilities() {}

    public static Bitmap scaleToHeight(Bitmap bitmap, int height)
    {
        if(bitmap == null)
            throw new IllegalArgumentException("bitmap is null");

        double aspectRatio = getAspectRatio(bitmap);
        int width = (int) Math.floor(height * aspectRatio);

        return Bitmap.createScaledBitmap(bitmap, width, height, false);
    }

    public static Bitmap scaleToWidth(Bitmap bitmap, int width)
    {
        if(bitmap == null)
            throw new IllegalArgumentException("bitmap is null");

        double aspectRatio = getAspectRatio(bitmap);
        int height = (int) Math.floor(width / aspectRatio);

        return Bitmap.createScaledBitmap(bitmap, width, height, false);
    }

    public static double getAspectRatio(Bitmap bitmap)
    {
        if(bitmap == null)
            throw new IllegalArgumentException("bitmap is null");

        return (double) bitmap.getWidth() / (double) bitmap.getHeight();
    }

    private static Bitmap cropTo(Bitmap image, int height, int width)
    {
        return Bitmap.createBitmap(image, 0, 0, width, height);
    }

    public static Bitmap scaleTo(Bitmap image, int newHeight, int newWidth)
    {
        Bitmap ret = image;
        boolean needsScaleHeight = image.getHeight() < newHeight;
        boolean needsScaleWidth =  image.getWidth() < newWidth;

        boolean needsUpScale = needsScaleHeight || needsScaleWidth;

        if(needsUpScale)
        {
            boolean needsBothScale = needsScaleHeight && needsScaleWidth;
            if(!needsBothScale)
            {
                if(needsScaleHeight)
                {
                    ret = scaleToHeight(image, newHeight);
                } else {
                    ret = scaleToWidth(image, newWidth);
                }
            }
            //Scale on both X and Y - determine as a percentage which one is less.
            double xPercent = (double) newWidth / (double) image.getWidth();
            double yPercent = (double) newHeight / (double) image.getHeight();

            //TODO: repeated logic.
            if(xPercent < yPercent) {
                ret = scaleToHeight(image, newHeight);
            }
            else
            {
                ret = scaleToWidth(image, newWidth);
            }
        }

        ret = cropTo(ret, newHeight, newWidth);

        assert(ret.getWidth() == newWidth);
        assert(ret.getHeight() == newHeight);

        return ret;

    }

    /**
     * Converts the specified image into a base64 encoded JPEG image.
     * @param image the image to encode.
     * @return A base64 representation of the JPEG form of the image.
     */
    public static String convertToBase64(Bitmap image)
    {
        return convertToBase64(image, Bitmap.CompressFormat.JPEG);
    }

    public static String convertToBase64(Bitmap image, Bitmap.CompressFormat format)
    {
        if(image == null)
            throw new IllegalArgumentException("image is null");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(format, 100, baos); //bm is the bitmap object
        byte[] byteArrayImage = baos.toByteArray();
        return Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
    }
}
