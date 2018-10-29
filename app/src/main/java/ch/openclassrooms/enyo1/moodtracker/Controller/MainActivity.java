package ch.openclassrooms.enyo1.moodtracker.Controller;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import ch.openclassrooms.enyo1.moodtracker.Model.Helper.OnSwipeTouchListener;
import ch.openclassrooms.enyo1.moodtracker.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout mainLinearLayout;
    private LinearLayout moodImageViewLayout;
    private ImageView moodImageView;
    private ImageView historicImageView;
    private ImageView addImageView;

    private static int []mImagesResources={R.drawable.smiley_happy,R.drawable.smiley_super_happy,
                                      R.drawable.smiley_sad,R.drawable.smiley_disappointed,R.drawable.smiley_normal};

    private  static int []mColorsResources={R.color.light_sage,R.color.banana_yellow,R.color.faded_red,R.color.warm_grey,
            R.color.cornflower_blue_65};

    private static int currentView = 0;

    private SharedPreferences mSharedPreferences;
    private static final String PREF_KEY_MOOD="PREF_KEY_MOOD";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences= getPreferences(MODE_PRIVATE);

        bindViews();
        mainLinearLayout.setBackgroundColor(getResources().getColor(mColorsResources[currentView]));
        moodImageView.setImageResource(mImagesResources[currentView]);
        showViews();
        historicImageView.setOnClickListener(this);
        addImageView.setOnClickListener(this);


    }

    private void bindViews(){
        mainLinearLayout    = findViewById(R.id.activityMainLayout);
        moodImageView       = findViewById(R.id.happyMoodImageView);
        moodImageViewLayout = findViewById(R.id.happyMoodLayout);
        historicImageView   = findViewById(R.id.historicImage);
        addImageView        = findViewById(R.id.addMoodImage);


    }

    /**
     * This method to configure the view.
     */
    @SuppressLint("ClickableViewAccessibility")
    public void showViews(){

        moodImageViewLayout.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeDown() {

                if (currentView>0)
                    currentView--;
                mainLinearLayout.setBackgroundColor(getResources().getColor(mColorsResources[currentView]));
                moodImageView.setImageResource(mImagesResources[currentView]);
                Toast.makeText(MainActivity.this, "Down :"+currentView , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeUp() {

                if (currentView<4)
                    currentView++;
                mainLinearLayout.setBackgroundColor(getResources().getColor(mColorsResources[currentView]));
                moodImageView.setImageResource(mImagesResources[currentView]);
                Toast.makeText(MainActivity.this, "Down :"+currentView , Toast.LENGTH_SHORT).show();

            }


        });



    }

    @Override
    public void onClick(View v) {
        if(addImageView==v)
            addCustomDialogBox();
        if(historicImageView==v){
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

    /**
     * This method start the historic activity.
     */
    public void toHistoricActivity(){

        Intent intent=new Intent(MainActivity.this,HistoricActivity.class);
        startActivity(intent);

    }


    /**
     * this method to save the current mood.
     */
    private void saveData(){



    }

}
