/* SelectActivity.java */

package com.example.fitnessbunduruu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.view.View;


// test

public class SelectActivity extends Activity {

    public final String TAG = "FinalProject";

    public String extraTag = "Category";

    private static TextView mTitleView;

    private static String mCategory;

    private LinearLayout mActivityLayout;
    private GridLayout mSelectLayout;

    private Button mBeginButton;

    private int mIndex;

    private ImageView imageViewArr[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        // Log.i(TAG, "EA OnCreate");
        imageViewArr = new ImageView[10];

        mTitleView = (TextView) findViewById(R.id.selectTitleView);
        mActivityLayout = (LinearLayout) findViewById(R.id.selectLayout);
        mSelectLayout = (GridLayout) findViewById(R.id.selectGridLayout);

        mBeginButton = (Button) findViewById(R.id.beginButton);

        Bundle extras = getIntent().getExtras();

        mCategory = extras.getString(extraTag);

        // Set title depending on which radio button user selected.
        mTitleView.setText(mCategory);

        // Can we easily highlight selected images?
        // Blue Highlight: 00a4f9

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
//        imageViewArr[4] = (ImageView) findViewById(R.id.exerciseSelectImageView5);
//        imageViewArr[5] = (ImageView) findViewById(R.id.exerciseSelectImageView6);
//        imageViewArr[6] = (ImageView) findViewById(R.id.exerciseSelectImageView7);
//        imageViewArr[7] = (ImageView) findViewById(R.id.exerciseSelectImageView8);
//        imageViewArr[8] = (ImageView) findViewById(R.id.exerciseSelectImageView9);
//        imageViewArr[9] = (ImageView) findViewById(R.id.exerciseSelectImageView10);

        if (mCategory.equals("Strength")) {

            //setting image resource

            imageViewArr[0].setImageResource(R.drawable.ic_weights);
            imageViewArr[1].setImageResource(R.drawable.ic_large_weights);
            imageViewArr[2].setImageResource(R.drawable.ic_pull_ups);
            imageViewArr[3].setImageResource(R.drawable.ic_pushups);
//            imageViewArr[4].setImageResource(R.drawable.ic_sprint);
//            imageViewArr[5].setImageResource(R.drawable.ic_sprint);
//            imageViewArr[6].setImageResource(R.drawable.ic_sprint);
//            imageViewArr[7].setImageResource(R.drawable.ic_sprint);
//            imageViewArr[8].setImageResource(R.drawable.ic_sprint);
//            imageViewArr[9].setImageResource(R.drawable.ic_sprint);

        } else { // Cardio
            imageViewArr[0].setImageResource(R.drawable.ic_sprint);
            imageViewArr[1].setImageResource(R.drawable.ic_jump_rope);
            imageViewArr[2].setImageResource(R.drawable.ic_treadmill);
            imageViewArr[3].setImageResource(R.drawable.ic_running);
//            imageViewArr[4].setImageResource(R.drawable.ic_sprint);
//            imageViewArr[5].setImageResource(R.drawable.ic_sprint);
//            imageViewArr[6].setImageResource(R.drawable.ic_sprint);
//            imageViewArr[7].setImageResource(R.drawable.ic_sprint);
//            imageViewArr[8].setImageResource(R.drawable.ic_sprint);
//            imageViewArr[9].setImageResource(R.drawable.ic_sprint);
        }
        //setting image position

        // imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imageViewArr[0] == v) {

                    imageViewArr[0].setColorFilter(Color.BLUE, PorterDuff.Mode.LIGHTEN);

                }
                else if (imageViewArr[1] == v) {

                    imageViewArr[1].setColorFilter(Color.BLUE, PorterDuff.Mode.LIGHTEN);

                }
                if (imageViewArr[2] == v) imageViewArr[2].setColorFilter(Color.BLUE, PorterDuff.Mode.LIGHTEN);
                if (imageViewArr[3] == v) imageViewArr[3].setColorFilter(Color.BLUE, PorterDuff.Mode.LIGHTEN);
                if (imageViewArr[4] == v) imageViewArr[4].setColorFilter(Color.BLUE, PorterDuff.Mode.LIGHTEN);
                if (imageViewArr[5] == v) imageViewArr[5].setColorFilter(Color.BLUE, PorterDuff.Mode.LIGHTEN);
                if (imageViewArr[6] == v) imageViewArr[6].setColorFilter(Color.BLUE, PorterDuff.Mode.LIGHTEN);
                if (imageViewArr[7] == v) imageViewArr[7].setColorFilter(Color.BLUE, PorterDuff.Mode.LIGHTEN);
                if (imageViewArr[8] == v) imageViewArr[8].setColorFilter(Color.BLUE, PorterDuff.Mode.LIGHTEN);
                if (imageViewArr[9] == v) imageViewArr[9].setColorFilter(Color.BLUE, PorterDuff.Mode.LIGHTEN);

                // add to a list or mark remaining # to select?
            }
        };

        imageViewArr[0].setOnClickListener(listener);
        imageViewArr[1].setOnClickListener(listener);
        imageViewArr[2].setOnClickListener(listener);
        imageViewArr[3].setOnClickListener(listener);
        imageViewArr[4].setOnClickListener(listener);
        imageViewArr[5].setOnClickListener(listener);
        imageViewArr[6].setOnClickListener(listener);
        imageViewArr[7].setOnClickListener(listener);
        imageViewArr[8].setOnClickListener(listener);
        imageViewArr[9].setOnClickListener(listener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
