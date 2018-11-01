package ch.openclassrooms.enyo1.moodtracker.Controller;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.GregorianCalendar;
import java.util.LinkedList;

import ch.openclassrooms.enyo1.moodtracker.Model.Data.MoodData;
import ch.openclassrooms.enyo1.moodtracker.Model.Data.MoodDataManager;
import ch.openclassrooms.enyo1.moodtracker.Model.Helper.OnSwipeTouchListener;
import ch.openclassrooms.enyo1.moodtracker.R;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    private LinearLayout mainLinearLayout;
    private LinearLayout moodImageViewLayout;
    private ImageView moodImageView;
    private ImageView historicImageView;
    private ImageView addImageView;

    public static final int []mImagesResources={R.drawable.smiley_happy,R.drawable.smiley_super_happy, R.drawable.smiley_sad,R.drawable.smiley_disappointed,R.drawable.smiley_normal};

    public  static final int []mColorsResources={R.color.light_sage,R.color.banana_yellow,R.color.faded_red,R.color.warm_grey, R.color.cornflower_blue_65};

    private static int currentMoodId;
    private static MoodData currentMoodData;
    private MoodDataManager mMoodDataManager;

    private static SharedPreferences mSharedPreferences;
    private static final String PREF_KEY_MOOD_LIST="PREF_KEY_MOOD_LIST";
    private static final String BUNDLE_KEY_CURRENT_MOOD="CURRENT_MOOD";
    public static final String BUNDLE_KEY_MOOD_LIST="BUNDLE_KEY_MOOD_LIST";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=getIntent ();
        if(intent.getStringExtra ("bonjour")=="bonjour"){
            saveData ();
        }

        mSharedPreferences= getPreferences(MODE_PRIVATE);
        mMoodDataManager=new MoodDataManager ();


        bindViews();

        if(savedInstanceState!=null){
            currentMoodId=savedInstanceState.getInt(BUNDLE_KEY_CURRENT_MOOD);

        }else {
            currentMoodId=0;

        }
        // Initialise the default mood data.
        currentMoodData=new MoodData (currentMoodId);

        updateCurrentView (currentMoodData.getResourceId ());

        historicImageView.setOnClickListener(this);
        addImageView.setOnClickListener(this);

        showViews();

        scheduleAlarm();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_KEY_CURRENT_MOOD,currentMoodId);
        super.onSaveInstanceState(outState);
    }


    private void bindViews(){
        mainLinearLayout    = findViewById(R.id.activityMainLayout);
        moodImageView       = findViewById(R.id.happyMoodImageView);
        moodImageViewLayout = findViewById(R.id.happyMoodLayout);
        historicImageView   = findViewById(R.id.historicImage);
        addImageView        = findViewById(R.id.addMoodImage);
    }

    /**
     *
     * @param moodId,
     *       The id of the actual mood.
     */
    public void updateCurrentView(int moodId){

        mainLinearLayout.setBackgroundColor(getResources().getColor(mColorsResources[moodId]));
        moodImageView.setImageResource(mImagesResources[moodId]);

    }

    /**
     * This method to configure the view.
     */
    @SuppressLint("ClickableViewAccessibility")
    public void showViews(){

        moodImageViewLayout.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeDown() {

                if (currentMoodId>0)
                    currentMoodId--;
                updateCurrentView (currentMoodId);
                Toast.makeText(MainActivity.this, "Down :currentMoodId -->"+currentMoodId , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeUp() {

                if (currentMoodId<4)
                    currentMoodId++;
                updateCurrentView (currentMoodId);
                Toast.makeText(MainActivity.this, "Down :"+currentMoodId , Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(addImageView==v)
            addCustomDialogBox();
        if(historicImageView==v){
            toHistoricActivity();
        }
    }

    /**
     * This method to add the custom dialog box.
     */
    public void addCustomDialogBox(){

        LayoutInflater layoutInflater =getLayoutInflater();
        View view;
        view = layoutInflater.inflate(R.layout.user_input_dialog_box,null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(this);
        final EditText userInputDialogEditText =  view.findViewById(R.id.userInputDialog);

        alertDialogBuilderUserInput.setView(view);

        alertDialogBuilderUserInput

                .setCancelable(false)

                .setPositiveButton("Valider", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogBox, int id) {
                    String txt=  userInputDialogEditText.getText().toString();

                        Toast.makeText(MainActivity.this, "Value :"+txt, Toast.LENGTH_SHORT).show();

                        currentMoodData =new MoodData(currentMoodId);
                        currentMoodData.setMessage(txt);
                    }
                })
                .setNegativeButton("Annuler",

                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialogBox, int id) {

                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
    }

    /**
     * This method start the historic activity.
     * The method send the list of mood data to the historic activity.
     */
    public void toHistoricActivity(){
        String jsonString =mSharedPreferences.getString (PREF_KEY_MOOD_LIST,null);

        Intent intent=new Intent(MainActivity.this,HistoricActivity.class);
        intent.putExtra (BUNDLE_KEY_MOOD_LIST,jsonString);
        startActivity(intent);
    }

    /**
     * This method to schedule the alarm.
     */

    public void scheduleAlarm()
    {
        // time at which alarm will be scheduled here alarm is scheduled at 5 min from current time,
        // we fetch  the current time in milliseconds and added 5 time
        // i.e. 5*60*1000= 86,400,000   milliseconds in a 5 min
        /*Long time = new GregorianCalendar().getTimeInMillis()+5*60*1000;


        Long time = new GregorianCalendar().getTimeInMillis()+5*60*1000;


        Intent intentAlarm = new Intent(this, AlarmReceiver.class);
        intentAlarm.putExtra ("currentViewId", currentMoodData.getResourceId ());
        intentAlarm.putExtra("message",currentMoodData.getMessage ());


        // create the object
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //set the alarm for particular time
        alarmManager.set(AlarmManager.RTC_WAKEUP,time, PendingIntent.getBroadcast(this,1,  intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
        Toast.makeText(this, "Alarm Scheduled for 5 min", Toast.LENGTH_LONG).show();*/

        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiverOne.class);
        intent.putExtra ("currentViewId", currentMoodData.getResourceId ());

        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


// setRepeating() lets you specify a precise custom interval--in this case,
// 2 minutes.

        Long time = new GregorianCalendar().getTimeInMillis()+2*60*1000;

        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time,
                1000 * 60 * 2, alarmIntent);
        Toast.makeText(this, "Alarm Scheduled for 2 min", Toast.LENGTH_LONG).show();


    }

    /**
     * This method to save the given data.
     *        The data to be stored.
     */
    public static void saveData(){
        MoodDataManager mMoodDataManager=new MoodDataManager ();

        LinkedList<MoodData> moodDataList;

        String jsonString= mSharedPreferences.getString(PREF_KEY_MOOD_LIST,null);
        if(jsonString!=null) {
            moodDataList = mMoodDataManager.jsonToMoodLinkedList(jsonString);

        }else {
            moodDataList=new LinkedList<>();
        }
        moodDataList= mMoodDataManager.addData(moodDataList,currentMoodData);

        String toBeStored=mMoodDataManager.objectToJson(moodDataList);
        mSharedPreferences.edit().putString(PREF_KEY_MOOD_LIST, toBeStored).apply();
        Log.e("my" ,""+currentMoodId);

        // Initialise the default mood data.
        currentMoodId = 0;

        Log.e("my" ,""+currentMoodId);


    }

    public static class AlarmReceiverOne extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            Log.d("-", "Receiver4");
            saveData ();
        }
    }


    @Override
    protected void onResume() {
        super.onResume ();

    }


}
