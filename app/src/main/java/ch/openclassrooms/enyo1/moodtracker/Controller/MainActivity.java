package ch.openclassrooms.enyo1.moodtracker.Controller;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

//import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import ch.openclassrooms.enyo1.moodtracker.Model.Data.MoodData;
import ch.openclassrooms.enyo1.moodtracker.Model.Data.MoodDataManager;
import ch.openclassrooms.enyo1.moodtracker.Model.Helper.OnSwipeTouchListener;
import ch.openclassrooms.enyo1.moodtracker.R;


/**
 * The main activity implements a click listener and Observer.
 * The Observer pattern is used to keep track the alarm receiver.
 */
public class MainActivity extends AppCompatActivity implements Observer,View.OnClickListener{

    private LinearLayout mainLinearLayout;
    private LinearLayout moodImageViewLayout;
    private ImageView moodImageView;
    private ImageView historicImageView;
    private ImageView addImageView;
    private MediaPlayer mMediaPlayer;

    public static final int []mImagesResources={R.drawable.smiley_sad,R.drawable.smiley_disappointed,R.drawable.smiley_normal,R.drawable.smiley_happy,R.drawable.smiley_super_happy};
    public static final int []mColorsResources={R.color.faded_red,R.color.warm_grey, R.color.cornflower_blue_65,
            R.color.light_sage,R.color.banana_yellow
            };
    public static final int []values={1,2,3,4,5};
    public static final int  []rings={R.raw.sad,R.raw.disappointed,R.raw.normal,R.raw.happy,R.raw.super_happy};


    private static int currentMoodId;
    private  MoodData currentMoodData;

    private static SharedPreferences mSharedPreferences;
    private static final String PREF_KEY_MOOD_LIST="PREF_KEY_MOOD_LIST";
    private static final String BUNDLE_KEY_CURRENT_MOOD="CURRENT_MOOD";
    public static final String BUNDLE_KEY_MOOD_LIST="BUNDLE_KEY_MOOD_LIST";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mSharedPreferences= getPreferences(MODE_PRIVATE);

// Add this activity to the observer list.
       AlarmReceiver.sAlarmBroadcastObserver.addObserver (this);

        bindViews();

        if(savedInstanceState!=null){
            currentMoodId=savedInstanceState.getInt(BUNDLE_KEY_CURRENT_MOOD);

        }else {
            currentMoodId=3;

        }
        // Initialise the default mood data.
        currentMoodData=new MoodData (currentMoodId);

        updateCurrentView (currentMoodData.getResourceId ());

        historicImageView.setOnClickListener(this);
        addImageView.setOnClickListener(this);

        showViews();

        scheduleAlarm();

        mMediaPlayer= MediaPlayer.create(MainActivity.this,R.raw.happy);
        mMediaPlayer.start();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_KEY_CURRENT_MOOD,currentMoodId);
        super.onSaveInstanceState(outState);
    }

    /**
     *This method to bind the views.
     */
    private void bindViews(){
        mainLinearLayout    = findViewById(R.id.activityMainLayout);
        moodImageView       = findViewById(R.id.happyMoodImageView);
        moodImageViewLayout = findViewById(R.id.happyMoodLayout);
        historicImageView   = findViewById(R.id.historicImage);
        addImageView        = findViewById(R.id.addMoodImage);
    }

    /**
     *This method to update the view.
     * @param moodId,
     *       The id of the actual mood.
     */
    public void updateCurrentView(int moodId){
        currentMoodData=new MoodData (moodId);

        mainLinearLayout.setBackgroundColor(getResources().getColor(mColorsResources[currentMoodData.getResourceId ()]));
        moodImageView.setImageResource(mImagesResources[currentMoodData.getResourceId ()]);

    }

    /**
     * This method to configure the view.
     * By swiping, the image change.
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
                playMusic (rings[currentMoodId]);
            }

            @Override
            public void onSwipeUp() {

                if (currentMoodId<4)
                    currentMoodId++;
                updateCurrentView (currentMoodId);
                Toast.makeText(MainActivity.this, "Down :"+currentMoodId , Toast.LENGTH_SHORT).show();
                playMusic (rings[currentMoodId]);
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(addImageView==v)
            addCustomDialogBox();

        if(historicImageView==v)
             toHistoricActivity();
    }

    /**
     * This method to add the custom dialog box.
     */
    public void addCustomDialogBox(){



       View view = View.inflate(getBaseContext (),R.layout.user_input_dialog_box,null);
       // view = layoutInflater.inflate(R.layout.user_input_dialog_box,null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(this);
        final EditText userInputDialogEditText =  view.findViewById(R.id.userInputDialog);

        alertDialogBuilderUserInput.setView(view);

        alertDialogBuilderUserInput

                .setCancelable(false)

                .setPositiveButton("Valider", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogBox, int id) {
                    String txt=  userInputDialogEditText.getText().toString();

                        Toast.makeText(MainActivity.this, "Value :"+txt, Toast.LENGTH_SHORT).show();

                       // currentMoodData =new MoodData(currentMoodId);
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
     * This method to save the given data.
     *        The data to be stored.
     */
    public  void saveData(){
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
        Log.e("System my " ,""+currentMoodId);

        // Initialise the default mood data.
        currentMoodId = 3;
        currentMoodData=new MoodData (currentMoodId);
        updateCurrentView (currentMoodId);

        Log.e(" info :" ," Data is save ");


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
        // set alarm to wakeup the device at 24 o'clock.

       /* Calendar calendar =Calendar.getInstance ();
        calendar.setTimeInMillis (System.currentTimeMillis ());
        calendar.set(Calendar.HOUR_OF_DAY,0);
        AlarmManager alarmManager=(AlarmManager) getSystemService (Context.ALARM_SERVICE);
        Intent i = new Intent(this, AlarmReceiver.class);
        PendingIntent   alarm_Intent = PendingIntent.getBroadcast(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setInexactRepeating (AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis (),AlarmManager.INTERVAL_DAY,alarm_Intent);

       */

       AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
       Intent intent = new Intent(this, AlarmReceiver.class);

       PendingIntent   alarmIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


      // SetRepeating() lets you specify a precise custom interval--in this case, 2 minutes.

        Long time = new GregorianCalendar().getTimeInMillis()+2*60*1000;

        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, time,
                1000 * 60 * 2, alarmIntent);
        Toast.makeText(this, "Alarm Scheduled for 2 min", Toast.LENGTH_LONG).show();


    }

    @Override
    public void update(Observable o, Object arg) {
        saveData ();

    }


    /**
     * This method is to play the music, according to the mood image.
     * @param musicId,
     *        the id off the music to be play. We have it from resources array call, values.
     */
    public void playMusic(int musicId){
        // If the music is playing, we stop it, and restart.
        if(mMediaPlayer.isPlaying ())
            mMediaPlayer.stop ();

        mMediaPlayer= MediaPlayer.create(MainActivity.this,musicId);
        mMediaPlayer.start();

    }

    @Override
    protected void onRestart() {
        super.onRestart ();

        System.out.println("MainActivity::onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("MainActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("MainActivity::onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("MainActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("MainActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("MainActivity::onDestroy()");
    }


}
