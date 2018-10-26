package ch.openclassrooms.enyo1.moodtracker.Controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import ch.openclassrooms.enyo1.moodtracker.Model.Helper.OnSwipeTouchListener;
import ch.openclassrooms.enyo1.moodtracker.R;

public class HappyMoodActivity extends AppCompatActivity{
    private ImageView addSmiley;
    private ImageView historicView;
    private LinearLayout mLinearLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy_mood);

        configureView();


    }

    /**
     * This method to configure the view.
     */
    @SuppressLint("ClickableViewAccessibility")
    public void configureView(){
      // ImageView happyMoodImage =  findViewById(R.id.happyMoodImageView);
        addSmiley=findViewById(R.id.addHappyMood);
        historicView=findViewById(R.id.imageHistoricHappyMood);
        mLinearLayout =findViewById(R.id.happyMoodLayout);

        mLinearLayout.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeDown() {
                Toast.makeText(HappyMoodActivity.this, "Down", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSwipeLeft() {
                Toast.makeText(HappyMoodActivity.this, "Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeUp() {
                Toast.makeText(HappyMoodActivity.this, "Up", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(HappyMoodActivity.this,SuperGoodMoodActivity.class);
                startActivity(intent);

            }

            @Override
            public void onSwipeRight() {
                Toast.makeText(HappyMoodActivity.this, "Right", Toast.LENGTH_SHORT).show();
            }
        });




    }




}
