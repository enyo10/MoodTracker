package ch.openclassrooms.enyo1.moodtracker.Controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import ch.openclassrooms.enyo1.moodtracker.Model.Data.AlarmBroadcastObserver;


public final class AlarmReceiver extends BroadcastReceiver {

   // public static final String SAMPLE_EXTRA_DATA="ch.enyo.EXTRA_DATA";
    public static AlarmBroadcastObserver sAlarmBroadcastObserver=new AlarmBroadcastObserver ();

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i("Receiver","Received info");

        sAlarmBroadcastObserver.triggerObservers ();

    }


}
