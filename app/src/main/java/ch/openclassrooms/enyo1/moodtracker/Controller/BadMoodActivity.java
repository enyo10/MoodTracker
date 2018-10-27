package ch.openclassrooms.enyo1.moodtracker.Controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import ch.openclassrooms.enyo1.moodtracker.Model.Helper.OnSwipeTouchListener;
import ch.openclassrooms.enyo1.moodtracker.R;

public class BadMoodActivity extends AppCompatActivity {
    private ImageView addBadMood;
    private ImageView historicView;
    private LinearLayout mLinearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sad_mood);

        configureView();
    }

    /**
     * This method to configure the view.
     */
    @SuppressLint("ClickableViewAccessibility")
    public void configureView(){
        // ImageView happyMoodImage =  findViewById(R.id.happyMoodImageView);
        addBadMood=findViewById(R.id.addSadMood);
        historicView=findViewById(R.id.imageHistoricSadMood);
        mLinearLayout =findViewById(R.id.sadMoodLayout);

        mLinearLayout.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeDown() {
                Intent intent =new Intent(BadMoodActivity.this,VeryBadMoodActivity.class);
                startActivity(intent);


            }

            @Override
            public void onSwipeUp() {

                Intent intent =new Intent(BadMoodActivity.this,NormalMoodActivity.class);
                startActivity(intent);

            }

        });




    }
}
