// SelectActivity.java

package com.example.fitnessbunduruu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessbunduruu.types.Exercise;
import com.example.fitnessbunduruu.types.ExerciseGroup;

public class SelectActivity extends Activity {

    public static final String TAG = "FinalProject";

    public static final String extraTag = "Category";

    public static final String exerciseGroupTag = "ExerciseGroup";

    private static TextView mTitleView;

    private static String mCategory;

    private LinearLayout mActivityLayout;
    private TableLayout mSelectTableLayout;

    private Button mBeginButton;

    private int mIndex;

    private ImageView imageViewArr[];
    private CheckBox checkBoxes[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Log.i(TAG, "SelectActivity OnCreate");
        imageViewArr = new ImageView[4];
        checkBoxes = new CheckBox[4];

        mTitleView = (TextView) findViewById(R.id.selectTitleView);
        mActivityLayout = (LinearLayout) findViewById(R.id.selectLayout);
        mSelectTableLayout = (TableLayout) findViewById(R.id.selectTableLayout);

        mBeginButton = (Button) findViewById(R.id.beginButton);

        Bundle extras = getIntent().getExtras();

        mCategory = extras.getString(extraTag);

        // Set title depending on which radio button user selected.
        mTitleView.setText(mCategory);

        populateSelectGrid();

        // User clicks the start button to start a workout.
        mBeginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent;

                // Prepare to launch the Exercise Activity.
                intent = new Intent(getApplicationContext(), ExerciseActivity.class);

                // Create an exercise group depending upon which checkboxes are selected.
                ExerciseGroup group = new ExerciseGroup(mCategory);

                String[] exerciseNames;

                Exercise.workoutType type;

                int[] imageResourceNumbers;

                // Stuff the workout type, exercise names, and exercise images into a parcelable
                // for the ExerciseActivity to handle displaying.
                if (mCategory.equals(getResources().getString(R.string.cardio))) {

                    intent.putExtra(extraTag, getResources().getString(R.string.cardio));

                    exerciseNames = getResources().getStringArray(R.array.CardioCategory);
                    type = Exercise.workoutType.CARDIO;
                    // These must be synced with arrays of strings in strings files...
                    imageResourceNumbers = new int[]{R.drawable.ic_sprint, R.drawable.ic_jump_rope, R.drawable.ic_treadmill, R.drawable.ic_running};

                } else {

                    intent.putExtra(extraTag, getResources().getString(R.string.strength));

                    exerciseNames = getResources().getStringArray(R.array.StrengthCategory);
                    type = Exercise.workoutType.STRENGTH;
                    // These must be synced with arrays of strings in strings files...
                    imageResourceNumbers = new int[]{R.drawable.ic_weights, R.drawable.ic_pull_ups, R.drawable.ic_large_weights, R.drawable.ic_pushups};

                }

                for (int i = 0; i < exerciseNames.length; i++) {

                    // Create an Exercise for each Checkbox, and add it to the group.
                    if (checkBoxes[i].isChecked()) {

                        group.add(new Exercise(type, exerciseNames[i], imageResourceNumbers[i], 0, 0, 0));

                    }

                }

                if (group.getSize() == 0) {

                    // Do not start the next ExerciseActivity if the user has not selected any exercises.
                    Toast.makeText(getApplicationContext(), "Please select at least one exercise.", Toast.LENGTH_LONG).show();

                } else {

                    intent.putExtra(exerciseGroupTag, (Parcelable) group);

                    startActivity(intent);
                }
            }
        });

    }

    private void populateSelectGrid() {

        //ImageView Setup

        // Setting to four each until we have more.
        // Filling the screen with redundancies is just confusing for users

        imageViewArr[0] = (ImageView) findViewById(R.id.exerciseSelectImageView);
        imageViewArr[1] = (ImageView) findViewById(R.id.exerciseSelectImageView2);
        imageViewArr[2] = (ImageView) findViewById(R.id.exerciseSelectImageView3);
        imageViewArr[3] = (ImageView) findViewById(R.id.exerciseSelectImageView4);

        checkBoxes[0] = (CheckBox) findViewById(R.id.exerciseCheckBox);
        checkBoxes[1] = (CheckBox) findViewById(R.id.exerciseCheckBox2);
        checkBoxes[2] = (CheckBox) findViewById(R.id.exerciseCheckBox3);
        checkBoxes[3] = (CheckBox) findViewById(R.id.exerciseCheckBox4);

        String[] exerciseNames;
        if (mCategory.equals("Strength")) {

            exerciseNames = getResources().getStringArray(R.array.StrengthCategory);

            for (int i = 0; i < exerciseNames.length; i++) {
                checkBoxes[i].setText(exerciseNames[i]);
            }
            //setting image resource

            imageViewArr[0].setImageResource(R.drawable.ic_weights);
            imageViewArr[1].setImageResource(R.drawable.ic_pull_ups);
            imageViewArr[2].setImageResource(R.drawable.ic_large_weights);
            imageViewArr[3].setImageResource(R.drawable.ic_pushups);

        } else { // Cardio

            exerciseNames = getResources().getStringArray(R.array.CardioCategory);

            for (int i = 0; i < exerciseNames.length; i++) {
                checkBoxes[i].setText(exerciseNames[i]);
            }

            imageViewArr[0].setImageResource(R.drawable.ic_sprint);
            imageViewArr[1].setImageResource(R.drawable.ic_jump_rope);
            imageViewArr[2].setImageResource(R.drawable.ic_treadmill);
            imageViewArr[3].setImageResource(R.drawable.ic_running);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
