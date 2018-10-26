package ch.openclassrooms.enyo1.moodtracker.Controller;

import android.annotation.SuppressLint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import ch.openclassrooms.enyo1.moodtracker.R;

public class HappyMoodActivity extends AppCompatActivity{
    private ImageView addSmiley;
    private ImageView historicView;
    private ImageView happyMoodImage;
    private LinearLayout linearLayout;



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
        happyMoodImage = (ImageView) findViewById(R.id.happyMoodImageView);
        addSmiley=findViewById(R.id.add_happy_smiley);
        historicView=findViewById(R.id.imageHistoric_h);
        linearLayout =findViewById(R.id.happy_mood_layout);

        // Implement it's on touch listener.
        linearLayout.setOnTouchListener(new View.OnTouchListener() {



            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                // Show an alert dialog.
                AlertDialog alertDialog = new AlertDialog.Builder(HappyMoodActivity.this).create();
                alertDialog.setMessage("You touched the Linear Layout.");
                alertDialog.show();

                // Return false, then android os will still process click event,
                // if return true, the on click listener will never be triggered.
                return false;
            }


        });


    }




}
