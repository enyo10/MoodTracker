package ch.openclassrooms.enyo1.moodtracker.Controller;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import ch.openclassrooms.enyo1.moodtracker.Model.Helper.OnSwipeTouchListener;
import ch.openclassrooms.enyo1.moodtracker.R;

public class HappyMoodActivity extends AppCompatActivity implements OnClickListener{
    private ImageView addSmiley;
    private ImageView historicView;
    private LinearLayout mLinearLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy_mood);

        configureView();

        addSmiley.setOnClickListener(this);
        historicView.setOnClickListener(this);

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


    @Override
    public void onClick(View v) {
        if(addSmiley==v)
            addCustomDialogBox();
        if(historicView==v){
            toHistoricActivity();
        }


    }


    /**
     * This method to add the custom dialog box.
     */
    public void addCustomDialogBox(){
        LayoutInflater layoutInflater =getLayoutInflater();
        View view =layoutInflater.inflate(R.layout.user_input_dialog_box,null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(this);

        alertDialogBuilderUserInput.setView(view);

        final EditText userInputDialogEditText =  view.findViewById(R.id.userInputDialog);

        alertDialogBuilderUserInput

                .setCancelable(false)

                .setPositiveButton("Valider", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogBox, int id) {

                        // ToDo get user input here.(To Do later).

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

    public void toHistoricActivity(){

        Intent intent=new Intent(HappyMoodActivity.this,HistoricActivity.class);
        startActivity(intent);

    }
}
