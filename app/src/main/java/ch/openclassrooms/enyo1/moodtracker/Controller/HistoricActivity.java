package ch.openclassrooms.enyo1.moodtracker.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

import ch.openclassrooms.enyo1.moodtracker.Model.Data.MoodData;
import ch.openclassrooms.enyo1.moodtracker.Model.Data.MoodDataManager;
import ch.openclassrooms.enyo1.moodtracker.R;

import static ch.openclassrooms.enyo1.moodtracker.Controller.MainActivity.BUNDLE_KEY_MOOD_LIST;

public class HistoricActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout mRelativeLayoutRaw1,mRelativeLayoutRaw2,mRelativeLayoutRaw3,mRelativeLayoutRaw4, mRelativeLayoutRaw5, mRelativeLayoutRaw6,mRelativeLayoutRaw7;
    private ArrayList<RelativeLayout>mRelativeLayouts;
    private TextView mTextViewRaw1,mTextViewRaw2,mTextViewRaw3,mTextViewRaw4,mTextViewRaw5,mTextViewRaw6,mTextViewRaw7;

    private ImageView mImageView1;
    private ImageView mImageView2;
    private ImageView mImageView3;
    private ImageView mImageView4;
    private ImageView mImageView5;
    private ImageView mImageView6;
    private ImageView mImageView7;

    private ArrayList<ImageView>mImageViews;

    private   LinkedList<MoodData> mMoodDataList;
    private MoodDataManager mMoodDataManager;
    private ImageView currentImageView ;
    private MoodData currentMoodData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);

        mMoodDataManager=new MoodDataManager ();

        String jsonString =getIntent ().getStringExtra (BUNDLE_KEY_MOOD_LIST);
        if(jsonString!=null)
        mMoodDataList = mMoodDataManager.jsonToMoodLinkedList (jsonString);

        binViews();
        updateView();

    }

    /**
     * This method to bind the views.
     */
    public void binViews(){
        mRelativeLayouts=new ArrayList<> ();
        mImageViews=new ArrayList<> ();
        // The linear layout of the historic view.

        mRelativeLayoutRaw1 = findViewById(R.id.activity_historic_row1);
        mRelativeLayoutRaw2 = findViewById(R.id.activity_historic_row2);
        mRelativeLayoutRaw3 = findViewById(R.id.activity_historic_row3);
        mRelativeLayoutRaw4 = findViewById(R.id.activity_historic_row4);
        mRelativeLayoutRaw5 = findViewById(R.id.activity_historic_row5);
        mRelativeLayoutRaw6 = findViewById(R.id.activity_historic_row6);
        mRelativeLayoutRaw7 = findViewById(R.id.activity_historic_row7);

        mRelativeLayouts.add (mRelativeLayoutRaw1);
        mRelativeLayouts.add (mRelativeLayoutRaw2);
        mRelativeLayouts.add (mRelativeLayoutRaw3);
        mRelativeLayouts.add (mRelativeLayoutRaw4);
        mRelativeLayouts.add (mRelativeLayoutRaw5);
        mRelativeLayouts.add (mRelativeLayoutRaw6);
        mRelativeLayouts.add (mRelativeLayoutRaw7);


      // The text view.
        mTextViewRaw1=findViewById(R.id.activity_historic_row1_txt);
        mTextViewRaw2=findViewById(R.id.activity_historic_row2_txt);
        mTextViewRaw3=findViewById(R.id.activity_historic_row3_txt);
        mTextViewRaw4=findViewById(R.id.activity_historic_row4_txt);
        mTextViewRaw5=findViewById(R.id.activity_historic_row5_txt);
        mTextViewRaw6=findViewById(R.id.activity_historic_row6_txt);
        mTextViewRaw7=findViewById(R.id.activity_historic_row7_txt);

        mTextViewRaw1.setText ("Il y a une semaine");
        mTextViewRaw2.setText ("Il y a six jours");
        mTextViewRaw3.setText ("il y a cinq jours");
        mTextViewRaw4.setText ("il y y quatre jours");
        mTextViewRaw5.setText ("avant hier.");
        mTextViewRaw6.setText ("Hier");

        // The image view
        mImageView1 = findViewById(R.id.activity_historic_row1_img);
        mImageView2 = findViewById(R.id.activity_historic_row2_img);
        mImageView3 = findViewById(R.id.activity_historic_row3_img);
        mImageView4 = findViewById(R.id.activity_historic_row4_img);
        mImageView5 = findViewById(R.id.activity_historic_row5_img);
        mImageView6 = findViewById(R.id.activity_historic_row6_img);
        mImageView7 = findViewById(R.id.activity_historic_row7_img);

        // Add the image view representing the message text to the list.

        mImageViews.add (mImageView1);
        mImageViews.add (mImageView2);
        mImageViews.add (mImageView3);
        mImageViews.add (mImageView4);
        mImageViews.add (mImageView5);
        mImageViews.add (mImageView6);
        mImageViews.add (mImageView7);

    }

    /**
     * This method to update the list of the historic.
     */

    public void updateView(){
        Display display = getWindowManager().getDefaultDisplay();
        int screenWidth = display.getWidth();
        int color;
        int ratio;
        int dataListSize;
        MoodData moodData;

        if(mMoodDataList!=null) {
            dataListSize = mMoodDataList.size ();


            if(dataListSize!=0){
                for(int i=0;i<dataListSize;i++){
                moodData= mMoodDataList.get (i);
                Log.e ("System ", "color " +moodData.getColor ());

                Log.e ("Sys","ResourceId "+moodData.getResourceId ());
                 color=MainActivity.mColorsResources[moodData.getResourceId ()];
                 ratio=MainActivity.values[moodData.getResourceId ()];

                if(moodData.hasMessage()){
                    mImageViews.get (i).setVisibility(View.VISIBLE);
                    mImageViews.get (i).setOnClickListener (this);
                }

               mRelativeLayouts.get (i).setBackgroundColor(getResources().getColor(color));
               mRelativeLayouts.get(i).getLayoutParams().width=ratio*screenWidth/5;
               mRelativeLayouts.get (i).setVisibility (View.VISIBLE);
            }
        }
        }

    }

    @Override
    public void onClick(View v) {


        int index = Integer.parseInt (v.getTag ().toString ());
        MoodData moodData=mMoodDataList.get (index);

        Toast.makeText(HistoricActivity.this, ""+ moodData.getMessage (), Toast.LENGTH_LONG).show();

    }


    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("HistoricActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("HistoricActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("HistoricActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("HistoricActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("HistoricActivity::onDestroy()");
    }


}
