package ch.openclassrooms.enyo1.moodtracker.Controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import ch.openclassrooms.enyo1.moodtracker.Model.Helper.OnSwipeTouchListener;
import ch.openclassrooms.enyo1.moodtracker.R;

public class NormalMoodActivity extends AppCompatActivity {
    private LinearLayout mLinearLayout;
    private ImageView historicView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_mood);
        configureView();
    }

    /**
     * This method to configure the view.
     */
    @SuppressLint("ClickableViewAccessibility")
    public void configureView(){

        historicView=findViewById(R.id.imageHistoricNormalMood);
        mLinearLayout =findViewById(R.id.normalMoodLayout);

        mLinearLayout.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeDown() {
                Intent intent =new Intent(NormalMoodActivity.this,BadMoodActivity.class);
                startActivity(intent);
            }



        });

        

    }

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("NormalMoodActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("NormalMoodActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("NormalMoodActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("NormalMoodActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("NormalMoodActivity::onDestroy()");
    }

}
