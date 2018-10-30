package ch.openclassrooms.enyo1.moodtracker.Model.Data;

import java.util.Calendar;
import java.util.Date;

/**
 * This class represent a mood.
 * A mood object has some values, that describe it.
 */
public class MoodData implements Comparable<MoodData> {
    // The date that the mood object is created.
    private static final Date sDate = Calendar.getInstance().getTime();
    // The message: the reason for the mood.
    private String mMessage;
    //The color that represent a given mood.
    private int mColor;
    private int mImage;

    /**
     * A constructor with without parameter.
     */
    public MoodData() {


    }

    /**
     * A second constructor with two parameters.
     * @param color,
     *       A mood is triggered with a color.
     * @param image,
     *        A mood hat can have a message that describe it.
     */

    public MoodData(int color, int image) {
     setColor(color);
     setImage(image);


    }

    /**
     * This method to check if the mood object has a message.
     * @return
     */
    public boolean hasMessage(){
        return !mMessage.isEmpty();
    }

    public Date getDate() {
        return sDate;
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

    @Override
    public int compareTo(MoodData o) {
        return sDate.compareTo(o.getDate());
    }


    @Override
    public String toString() {
        return "[ Date : " +this.sDate + " , Message: " + getMessage() +"]";
    }
}