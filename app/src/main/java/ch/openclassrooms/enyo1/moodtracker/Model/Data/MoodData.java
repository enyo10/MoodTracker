package ch.openclassrooms.enyo1.moodtracker.Model.Data;

/**
 * This class represent a mood.
 * A mood object has some values, that describe it.
 */
public class MoodData {
    // The message: the reason for the mood.
    private String mMessage="";
    //The color that represent a given mood.
    private int mColor;
    private int mImage;
    private int mResourceId;
    private int mWidthRatio;

    /**
     * A constructor with without parameter.
     */
    public MoodData() {


    }

    /**
     * A second constructor with two parameters.
     * @param resourceId,
     *       A mood is triggered with a color.
     */

    public MoodData(int resourceId) {
     setResourceId(resourceId);
     setWidthRatio (resourceId);
    }

    /**
     * This method to check if the mood object has a message.
     * @return boolean,
     *
     */
    public boolean hasMessage(){
        return !mMessage.isEmpty();
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        mImage = image;
    }

    public int getResourceId() {
        return mResourceId;
    }

    public void setResourceId(int resourceId) {
        mResourceId = resourceId;
    }

    public int getWidthRatio() {
        return mWidthRatio;
    }

    public void setWidthRatio(int widthRatio) {
        mWidthRatio = widthRatio;
    }

    @Override
    public String toString() {
        return "[   Message: " + getMessage () + "]";
    }
}