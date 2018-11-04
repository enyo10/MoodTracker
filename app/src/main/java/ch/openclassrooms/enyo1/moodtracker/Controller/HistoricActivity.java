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

public class HistoricActivity extends AppCompatActivity {

    private RelativeLayout mRelativeLayoutRaw1,mRelativeLayoutRaw2,mRelativeLayoutRaw3,mRelativeLayoutRaw4, mRelativeLayoutRaw5, mRelativeLayoutRaw6,mRelativeLayoutRaw7;
    private ArrayList<RelativeLayout>mRelativeLayouts;
    private TextView mTextViewRaw1,mTextViewRaw2,mTextViewRaw3,mTextViewRaw4,mTextViewRaw5,mTextViewRaw6,mTextViewRaw7;
    private int []imagesResource=MainActivity.mImagesResources;
    private int []colorsResource=MainActivity.mColorsResources;

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

  //  RelativeLayout.LayoutParams mLayoutParams;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);

        mMoodDataManager=new MoodDataManager ();

        String jsonString =getIntent ().getStringExtra (BUNDLE_KEY_MOOD_LIST);
        if(jsonString!=null)
        mMoodDataList = mMoodDataManager.jsonToMoodLinkedList (jsonString);

        binViews();
        updateView1();

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

    public void updateView1(){
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
                    mImageViews.get (i).setOnClickListener (new View.OnClickListener () {

                        @Override
                        public void onClick(View v) {
                           //Toast.makeText(HistoricActivity.this, moodData.getMessage (), Toast.LENGTH_LONG).show();
                        }
                    });
                }

               mRelativeLayouts.get (i).setBackgroundColor(getResources().getColor(color));
               mRelativeLayouts.get(i).getLayoutParams().width=ratio*screenWidth/5;
               mRelativeLayouts.get (i).setVisibility (View.VISIBLE);

            }
        }

        }


    }

    /**
     * This to update the historic view.
     */
    private void updateView(){
        // There must be at least one mood data record.

        Display display = getWindowManager().getDefaultDisplay();
        int screenWidth = display.getWidth();

        int size=mMoodDataList.size();

        if(size<=1){

        MoodData  moodData=mMoodDataList.get(0);

            if(moodData.hasMessage()){
                mImageView1.setVisibility(View.VISIBLE);
            }
        }

        if(size>=2){

            final RelativeLayout relavtiveLeft = mRelativeLayoutRaw2;
            ViewGroup.LayoutParams homeLayoutsparams = relavtiveLeft .getLayoutParams();
            homeLayoutsparams.width = screenWidth / 2;

               mRelativeLayoutRaw2.setVisibility(View.VISIBLE);
               mRelativeLayoutRaw2.setLayoutParams (homeLayoutsparams);
                mRelativeLayoutRaw2.setBackgroundColor(getResources().getColor(R.color.warm_grey));
                MoodData moodData1=mMoodDataList.get (1);

            if(moodData1.hasMessage()){
                mImageView2.setVisibility(View.VISIBLE);
            }

        }

        if(size>=3){
            MoodData moodData2=mMoodDataList.get (2);
            mRelativeLayoutRaw3.setVisibility(View.VISIBLE);
            mRelativeLayoutRaw3.getLayoutParams().width=screenWidth/3;
            mRelativeLayoutRaw3.setBackgroundColor (getResources ().getColor (R.color.colorPrimary));


            if(moodData2.hasMessage()){
                mImageView3.setVisibility(View.VISIBLE);
            }
        }

        if(size>=4){
            mRelativeLayoutRaw4.setVisibility(View.VISIBLE);
            MoodData moodData4=mMoodDataList.get (3);

            if(moodData4.hasMessage()){
                mImageView4.setVisibility(View.VISIBLE);
            }

        }
        if(size>=5){
            mRelativeLayoutRaw5.setVisibility(View.VISIBLE);
            MoodData moodData5=mMoodDataList.get (4);

            if(moodData5.hasMessage()){
                mImageView5.setVisibility(View.VISIBLE);
            }

        }
        if(size>=6){
            mRelativeLayoutRaw6.setVisibility(View.VISIBLE);
            MoodData moodData6=mMoodDataList.get (5);

            if(moodData6.hasMessage()){
                mImageView6.setVisibility(View.VISIBLE);
            }


        }
        if(size>=7) {
            mRelativeLayoutRaw7.setVisibility(View.VISIBLE);
            MoodData moodData7=mMoodDataList.get (6);

            if(moodData7.hasMessage()){
                mImageView7.setVisibility(View.VISIBLE);
            }

        }
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
