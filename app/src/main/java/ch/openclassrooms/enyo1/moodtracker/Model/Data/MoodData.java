package ch.openclassrooms.enyo1.moodtracker.Model.Data;

import java.util.Date;

/**
 * This class represent a mood.
 * A mood object has some values, that describe it.
 */
public class MoodData {
    // The data that the mood is save.
    private Date mDate;
    // The message: the reason for the mood.
    private String mMessage;
    //The color that represent a given mood.
    private int mColor;

    public MoodData() {

    }

    public MoodData(int color, Date date, String message) {


    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
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
}