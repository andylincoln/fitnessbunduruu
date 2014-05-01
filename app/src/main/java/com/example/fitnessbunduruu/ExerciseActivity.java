package com.example.fitnessbunduruu;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;

public class ExerciseActivity extends Activity {

    public final String TAG = "FinalProject";

    public String extraTag = "Category";

    private TextView mCountdownView;

    private RadioGroup mCategoryGroup;

    private TableLayout mLayout;

    private TextView mTitleView;
    private TextView mWorkoutTypeView;

    private ImageView mWorkoutTypeImageView;

    private Button mStartStopButton;

    // test1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        mLayout               = (TableLayout)findViewById(R.id.tableLayout);

        mTitleView            = (TextView) findViewById(R.id.titleView);
        mWorkoutTypeView      = (TextView) findViewById(R.id.workoutTypeView);
        mCountdownView        = (TextView) findViewById(R.id.countdownView);

        mWorkoutTypeImageView = (ImageView) findViewById(R.id.workoutTypeImageView);

        mStartStopButton      = (Button) findViewById(R.id.startStopButton);

        Bundle extras = getIntent().getExtras();

        // Set title depending on which radio button user selected.
        mTitleView.setText(extras.getString(extraTag));
        // Set the specific type of workout depending on which radio button user selected.
        // This is a static value atm for the purposes of presentation.
        mWorkoutTypeView.setText("Sprint");

        // Set the image for the specific workout the user selected.
        // This is static for the time being.
        mWorkoutTypeImageView.setImageResource(R.drawable.ic_sprint);



        // I do this multiple times, abstract this out.
        mStartStopButton.setText("Start");
        mStartStopButton.setTextColor(Color.parseColor("#01d410")); // green

        // Gots to do some shiftying of my code around.
        final CountDownTimer countdownTimer = new CountDownTimer(5000, 1) {

            public void onTick(long millisUntilFinished) {
                mCountdownView.setText("Time remaining: " + (millisUntilFinished / 1000) + "." + (millisUntilFinished % 1000));
                //mCountdownView.setText("Time remaining: " + millisUntilFinished) ;
            }

            public void onFinish() {
                mCountdownView.setText("You did it!");
                mStartStopButton.setText("Start");
                mStartStopButton.setTextColor(Color.parseColor("#01d410")); // green
            }
        };

        mStartStopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                if (mStartStopButton.getText().equals("Start")) {

                    mStartStopButton.setText("Stop");
                    mStartStopButton.setTextColor(Color.parseColor("#ff0505")); // red
                    countdownTimer.start();

                } else {

                    countdownTimer.cancel();
                    mCountdownView.setText("You gave up!");
                    mStartStopButton.setText("Start");
                    mStartStopButton.setTextColor(Color.parseColor("#01d410")); // green
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
