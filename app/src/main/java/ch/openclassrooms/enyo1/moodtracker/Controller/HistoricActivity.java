package ch.openclassrooms.enyo1.moodtracker.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import ch.openclassrooms.enyo1.moodtracker.Model.Data.MoodData;
import ch.openclassrooms.enyo1.moodtracker.Model.Data.MoodDataManager;
import ch.openclassrooms.enyo1.moodtracker.R;

public class HistoricActivity extends AppCompatActivity {

    private RelativeLayout mRelativeLayoutRaw1,mRelativeLayoutRaw2,mRelativeLayoutRaw3,mRelativeLayoutRaw4, mRelativeLayoutRaw5, mRelativeLayoutRaw6,mRelativeLayoutRaw7;
    private TextView mTextViewRaw1,mTextViewRaw2,mTextViewRaw3,mTextViewRaw4,mTextViewRaw5,mTextViewRaw6,mTextViewRaw7;


    private ImageView mImageView1;
    private ImageView mImageView2;
    private ImageView mImageView3;
    private ImageView mImageView4;
    private ImageView mImageView5;
    private ImageView mImageView6;
    private ImageView mImageView7;

    private   LinkedList<MoodData> mMoodDataList;
    private MoodData mMoodData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);
        mMoodDataList=MoodDataManager.getMoodDataList();
        binViews();

    }

    /**
     * This method to bind the views.
     */
    public void binViews(){
        // The linear layout of the historic view.

        mRelativeLayoutRaw1 = findViewById(R.id.activity_historic_row1);
        mRelativeLayoutRaw2 = findViewById(R.id.activity_historic_row2);
        mRelativeLayoutRaw3 = findViewById(R.id.activity_historic_row3);
        mRelativeLayoutRaw4 = findViewById(R.id.activity_historic_row4);
        mRelativeLayoutRaw5 = findViewById(R.id.activity_historic_row5);
        mRelativeLayoutRaw6 = findViewById(R.id.activity_historic_row6);
        mRelativeLayoutRaw7 = findViewById(R.id.activity_historic_row7);

      // The text view.
        mTextViewRaw1=findViewById(R.id.activity_historic_row1_txt);
        mTextViewRaw2=findViewById(R.id.activity_historic_row2_txt);
        mTextViewRaw3=findViewById(R.id.activity_historic_row3_txt);
        mTextViewRaw4=findViewById(R.id.activity_historic_row4_txt);
        mTextViewRaw5=findViewById(R.id.activity_historic_row5_txt);
        mTextViewRaw6=findViewById(R.id.activity_historic_row6_txt);
        mTextViewRaw7=findViewById(R.id.activity_historic_row7_txt);

        // The image view
        mImageView1 = findViewById(R.id.activity_historic_row1_img);
        mImageView2 = findViewById(R.id.activity_historic_row2_img);
        mImageView3 = findViewById(R.id.activity_historic_row3_img);
        mImageView4 = findViewById(R.id.activity_historic_row4_img);
        mImageView5 = findViewById(R.id.activity_historic_row5_img);
        mImageView6 = findViewById(R.id.activity_historic_row6_img);
        mImageView7 = findViewById(R.id.activity_historic_row7_img);

    }

    /**
     * This to update the historic view.
     */
    private void showMoodData(){
        // There must be at least one mood data record.
        MoodData moodData;
        int size=mMoodDataList.size();

            moodData=mMoodDataList.get(0);
            if(moodData.hasMessage()){
                mImageView1.setVisibility(View.VISIBLE);
            }



        if(size>=2){
                mRelativeLayoutRaw2.setVisibility(View.VISIBLE);

            if(moodData.hasMessage()){
                mImageView2.setVisibility(View.VISIBLE);
            }

        }

        if(size>=3){
            moodData=mMoodDataList.get(0);

        }

        if(size>=4){
             moodData=mMoodDataList.get(0);

        }
        if(size>=5){
             moodData=mMoodDataList.get(0);

        }
        if(size>=6){
            moodData=mMoodDataList.get(0);

        }
        if(size>=7) {
             moodData=mMoodDataList.get(0);

        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("GameActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("GameActivity::onResume()");
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
