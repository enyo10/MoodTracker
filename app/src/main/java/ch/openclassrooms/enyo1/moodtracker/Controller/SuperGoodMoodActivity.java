package ch.openclassrooms.enyo1.moodtracker.Controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import ch.openclassrooms.enyo1.moodtracker.Model.Helper.OnSwipeTouchListener;
import ch.openclassrooms.enyo1.moodtracker.R;

public class SuperGoodMoodActivity extends AppCompatActivity {
    private ImageView addSuperGoodMood;
    private ImageView historicView;
    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_good_mood);
        configureView();
    }

    /**
     * This method to configure the view.
     */
    @SuppressLint("ClickableViewAccessibility")
    public void configureView(){
        // ImageView happyMoodImage =  findViewById(R.id.happyMoodImageView);
        addSuperGoodMood=findViewById(R.id.addVeryHappyMood);
        historicView=findViewById(R.id.imageHistoricVeryHappy);
        mLinearLayout =findViewById(R.id.veryHappyMoodLayout);

        mLinearLayout.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeDown() {
                Toast.makeText(SuperGoodMoodActivity.this, "Down", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(SuperGoodMoodActivity.this,HappyMoodActivity.class);
                startActivity(intent);

            }

            @Override
            public void onSwipeLeft() {

            }

            @Override
            public void onSwipeUp() {
                Toast.makeText(SuperGoodMoodActivity.this, "Up", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(SuperGoodMoodActivity.this,VeryBadMoodActivity.class);
                startActivity(intent);

            }

            @Override
            public void onSwipeRight() {
                Toast.makeText(SuperGoodMoodActivity.this, "Right", Toast.LENGTH_SHORT).show();
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

        System.out.println("GameActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("SuperGoodMoodActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("SuperGoodMoodActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("SuperGoodMoodActivity::onDestroy()");
    }
}
