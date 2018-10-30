package ch.openclassrooms.enyo1.moodtracker.Model.Data;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class to manage the mood data. We can put and remove data.
 * We use Json object convert LinkedList of MoodData to Json String,
 * and revert it back.
 */
public class MoodDataManager {
    // A list of mood data.

    private static final int MAX_MOOD_DATA=6;

    public MoodDataManager(){
    }


    /**
     * This method add a mood data object to the list. If list size is 7, the first data will be removed.
     * @param list,
     *        The list to add the mood data.
     * @param moodData,
     *        The data to add to the list.
     */

    public LinkedList<MoodData> addData(LinkedList<MoodData>list, MoodData moodData){
        LinkedList<MoodData>mDataList=list;

        if(mDataList.size()<=MAX_MOOD_DATA){
            list.add(moodData); }

            else {
            mDataList.removeFirst();
            mDataList.add(moodData);
        }
        return mDataList;
    }

    /**
     *
     * @param object,
     *       the object to transform.
     * @return String,
     *        the string value to return.
     *
     */
    public  String objectToJson(Object object){
        Gson json =new Gson();
        return json.toJson(object);
    }

    /**
     *
     * @param json,
     *        A string object to transform to game ArrayList
     * @return ArrayList
     *        The array list of game.
     */
    public  LinkedList<MoodData> jsonToMoodLinkedList(String json){
        Gson gson=new Gson();

        Type founderListType = new TypeToken<LinkedList<MoodData>>(){}.getType();
        LinkedList<MoodData> moodDataList = gson.fromJson(json, founderListType);

        return moodDataList;
    }
}
