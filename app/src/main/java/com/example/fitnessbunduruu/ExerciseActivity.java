// ExerciseActivity.java

package com.example.fitnessbunduruu;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.media.MediaPlayer;
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

import com.example.fitnessbunduruu.types.ExerciseGroup;

public class ExerciseActivity extends Activity {

    public static final String TAG = "FinalProject";

    public static final String extraTag = "Category";

    public static final String exerciseGroupTag = "ExerciseGroup";

    private int mIndex;

    private ExerciseGroup mGroup;

    private TextView mCountdownView;

    private RadioGroup mCategoryGroup;

    private TableLayout mLayout;

    private TextView mTitleView;
    private TextView mWorkoutTypeView;

    private static ImageView mWorkoutTypeImageView;

    private static Button mStartStopButton;

    // Need separate media players so that we can start the countdown timer
    // only when the 'GO' sound finishes playing.
    private static MediaPlayer mMediaPlayerGo;
    private static MediaPlayer mMediaPlayer;

    private static AssetFileDescriptor mGoFD;
    private static AssetFileDescriptor mStopFD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        mLayout = (TableLayout) findViewById(R.id.tableLayout);

        mTitleView = (TextView) findViewById(R.id.titleView);
        mWorkoutTypeView = (TextView) findViewById(R.id.workoutTypeView);
        mCountdownView = (TextView) findViewById(R.id.countdownView);

        mWorkoutTypeImageView = (ImageView) findViewById(R.id.workoutTypeImageView);

        mStartStopButton = (Button) findViewById(R.id.startStopButton);

        mGoFD = getResources().openRawResourceFd(R.raw.go);
        mStopFD = getResources().openRawResourceFd(R.raw.stop);

        Bundle extras = getIntent().getExtras();

        // Determine workouts to display on screen based on incoming ExerciseGroup.
        mGroup = (ExerciseGroup) getIntent().getParcelableExtra(exerciseGroupTag);

        // Set title depending on which workout type radio button user selected.
        mTitleView.setText(extras.getString(extraTag));

        mIndex = 0;

        // Set the current exercise depending on which checkboxes the user selected.
        mWorkoutTypeView.setText(mGroup.getExerciseAt(mIndex).getName());
        mWorkoutTypeImageView.setImageResource(mGroup.getExerciseAt(mIndex).getImageResourceNum());

        mIndex++;

        mStartStopButton.setText("Start");
        mStartStopButton.setTextColor(Color.parseColor("#01d410")); // green

        // Separate media players for each audio sound.
        mMediaPlayerGo = MediaPlayer.create(ExerciseActivity.this, R.raw.go);
        mMediaPlayer = MediaPlayer.create(ExerciseActivity.this, R.raw.stop);

        final CountDownTimer countdownTimer = new CountDownTimer(5000, 1) {

            public void onTick(long millisUntilFinished) {
                mCountdownView.setText("Time remaining: " + (millisUntilFinished / 1000) + "." + (millisUntilFinished % 1000));
                //mCountdownView.setText("Time remaining: " + millisUntilFinished) ;
            }

            public void onFinish() {

                restartMediaPlayerStop();

            }
        };

        mMediaPlayerGo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                countdownTimer.start();
            }
        });

        // Exercise just ended, do we need to start a new one or are we all done.
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                // A countdown timer has reached 0.
                if (mIndex == mGroup.getSize()) {
                    // Workout complete!
                    mCountdownView.setText("You did it!");

                    // Disable Start button.
                    mStartStopButton.setText("Workout complete!");
                    mStartStopButton.setOnClickListener(null);

                } else {
                    // increment index and setup ui.
                    mCountdownView.setText("Get ready...");

                    // Set up next exercise.
                    mWorkoutTypeView.setText(mGroup.getExerciseAt(mIndex).getName());
                    mWorkoutTypeImageView.setImageResource(mGroup.getExerciseAt(mIndex).getImageResourceNum());

                    mIndex++;

                    restartMediaPlayerGo();
                }
            }
        });

        mStartStopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                if (mStartStopButton.getText().equals("Start")) {

                    mStartStopButton.setText("Stop");
                    mStartStopButton.setTextColor(Color.parseColor("#ff0505")); // red

                    restartMediaPlayerGo();

                } else {

                    countdownTimer.cancel();
                    mStartStopButton.setOnClickListener(null);
                    mCountdownView.setText("You gave up!");
                    mMediaPlayerGo.stop();

                }
            }
        });
    }

    private void restartMediaPlayerGo() {

        mMediaPlayerGo.stop();
        mMediaPlayerGo.reset();

        // Files in the resource directory are not actually treated as individual files, but rather one mega file.
        try {
            //mMediaPlayerGo.setDataSource(mGoFD.getFileDescriptor(), mGoFD.getStartOffset(), mGoFD.getLength());
            // Quick workaround to trim the 'start' sound, play 80,000 bytes of file.
            mMediaPlayerGo.setDataSource(mGoFD.getFileDescriptor(), mGoFD.getStartOffset(), 80000);

        } catch (Exception IOE) {
            Log.i(TAG, "click set data exc: " + IOE.toString());
        }
        try {
            mMediaPlayerGo.prepare();
        } catch (Exception IOE) {
            Log.i(TAG, "click prepare exc: " + IOE.toString());
        }

        mMediaPlayerGo.start();
    }

    private void restartMediaPlayerStop() {
        mMediaPlayer.stop();
        mMediaPlayer.reset();

        // Files in the resource directory are not actually treated as individual files, but rather one mega file.
        // If you do not set the data source with a start time and length, it was just play all of your resource audio files in alpha order.
        //
        // Explanation:
        // http://stackoverflow.com/questions/3289038/play-audio-file-from-the-assets-directory

        try {
            mMediaPlayer.setDataSource(mStopFD.getFileDescriptor(), mStopFD.getStartOffset(), mStopFD.getLength());
        } catch (Exception exc) {
            Log.i(TAG, "finish set data exc: " + exc.toString());
        }
        try {
            mMediaPlayer.prepare();
        } catch (Exception exc) {
            Log.i(TAG, "finish prepare exc: " + exc.toString());
        }

        mMediaPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Release audio resources from the media player.
        // This is recommended practice by the Android Dev site.
        mMediaPlayer.release();
        mMediaPlayerGo.release();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
