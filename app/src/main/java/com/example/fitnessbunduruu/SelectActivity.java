/* SelectActivity.java */

package com.example.fitnessbunduruu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public class SelectActivity extends Activity {

    public final String TAG = "FinalProject";

    public String extraTag = "Category";

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

        // Construct a Workout Group based on user selected exercises.

        mBeginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent;

                // Start a group of workouts.
                intent = new Intent(getApplicationContext(), ExerciseActivity.class);
                intent.putExtra(extraTag, getResources().getString(R.string.cardio));

                startActivity(intent);
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
            imageViewArr[1].setImageResource(R.drawable.ic_large_weights);
            imageViewArr[2].setImageResource(R.drawable.ic_pull_ups);
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
        //TODO Will add the ability to chain workouts next
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
