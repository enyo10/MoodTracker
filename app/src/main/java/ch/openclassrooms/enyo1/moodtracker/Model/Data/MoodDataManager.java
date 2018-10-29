package ch.openclassrooms.enyo1.moodtracker.Model.Data;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class to manage the mood data.
 */
public class MoodDataManager {
    // A list of mood data.
    private static  LinkedList<MoodData> mMoodDataList;
    private SharedPreferences mSharedPreferences;


    private static final int MAX_MOOD_DATA=7;

    public MoodDataManager(){
        mMoodDataList=new LinkedList<>();
    }

    public static LinkedList<MoodData> getMoodDataList() {
        return mMoodDataList;
    }


    public void addData(MoodData moodData){
        if(mMoodDataList.size()<=6){

            mMoodDataList.add(moodData); }

            else {
            mMoodDataList.removeFirst();
            mMoodDataList.add(moodData);

        }

    }

    public MoodData removeData(){
        return mMoodDataList.removeFirst();
    }
}
