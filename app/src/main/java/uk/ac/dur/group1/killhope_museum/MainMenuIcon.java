package uk.ac.dur.group1.killhope_museum;

import android.graphics.Bitmap;

import uk.ac.dur.group1.killhope_museum.utilities.BitmapUtilities;

/**
 * Encapsulates an icon on the main menu, which is a collection of a square icon and a command.
 */
public class MainMenuIcon
{
    private ICommand command;
    private Bitmap icon;

    public MainMenuIcon(ICommand command, Bitmap icon)
    {
        if(command == null)
            throw new IllegalArgumentException("command is null");
        if(icon == null)
            throw new IllegalArgumentException("icon is null");
        if(icon.getHeight() != icon.getWidth())
            throw new IllegalArgumentException("icon is not square.");

        this.command = command;
        this.icon = icon;
    }

    public ICommand getCommand() {
        return command;
    }

    public void setCommand(ICommand command) {
        this.command = command;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public int getDimensions() {
        return getIcon().getHeight();
    }

    public Bitmap getScaledBitmap(int iconDimensions) {
        return BitmapUtilities.scaleToHeight(getIcon(), iconDimensions);
    }
}
