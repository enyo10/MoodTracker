package ch.openclassrooms.enyo1.moodtracker.Controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import ch.openclassrooms.enyo1.moodtracker.Model.Helper.OnSwipeTouchListener;
import ch.openclassrooms.enyo1.moodtracker.R;

public class VeryBadMoodActivity extends AppCompatActivity {

    private ImageView addSmiley;
    private ImageView historicView;
    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_very_bad_mood);
        configureView();
    }

    /**
     * This method to configure the view.
     */
    @SuppressLint("ClickableViewAccessibility")
    public void configureView(){
        // ImageView happyMoodImage =  findViewById(R.id.happyMoodImageView);
        addSmiley=findViewById(R.id.addVeryHappyMood);
        historicView=findViewById(R.id.imageHistoricVeryBadMood);
        mLinearLayout =findViewById(R.id.veryBadMoodLayout);

        mLinearLayout.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeDown() {
                Intent intent =new Intent(VeryBadMoodActivity.this,SuperGoodMoodActivity.class);
                startActivity(intent);

            }

            @Override
            public void onSwipeUp() {

                Intent intent =new Intent(VeryBadMoodActivity.this,BadMoodActivity.class);
                startActivity(intent);

            }


        });




    }

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("GameActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("VeryBadMoodActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("VeryBadMoodActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("VeryBadMoodActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("VeryBadMoodActivity::onDestroy()");
    }

}
