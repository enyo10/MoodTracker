package ch.openclassrooms.enyo1.moodtracker.Model.Data;

import android.util.Log;

import java.util.Observable;

public class AlarmBroadcastObserver extends Observable {



    public void triggerObservers(){

        Log.i("Syst ", " Trigger work");

        // Sets the changed flag for this Observable
        setChanged();
        // notify registered observer objects by calling the update method
        notifyObservers();

    }


}
