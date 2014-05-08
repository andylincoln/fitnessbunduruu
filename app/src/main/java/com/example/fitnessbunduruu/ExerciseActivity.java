// ExerciseActivity.java
//
//

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

    // test1

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
        ExerciseGroup mGroup = (ExerciseGroup) getIntent().getParcelableExtra(exerciseGroupTag);

        // Verify that this activity has received the ExerciseGroup and its Exercises.
        // Log.i(TAG, "Group has name: " + mGroup.getName() + " with size: " + Integer.toString(mGroup.getSize()));
        //for (int i = 0; i < mGroup.getSize(); i++) {
        //      Log.i(TAG, "Workout: " + mGroup.getName() + " has exercise:" + mGroup.getExerciseAt(i).getName());
        //}

        // Set title depending on which radio button user selected.
        mTitleView.setText(extras.getString(extraTag));
        // Set the specific type of workout depending on which radio button user selected.
        // This is a static value atm for the purposes of presentation.

        mWorkoutTypeView.setText("Sprint"); // TODO: Change this so it actually computes it. Preferably through strings file

        // Set the image for the specific workout the user selected.
        // This is static for the time being.
        mWorkoutTypeImageView.setImageResource(R.drawable.ic_sprint);

        // I do this multiple times, abstract this out.
        mStartStopButton.setText("Start");
        mStartStopButton.setTextColor(Color.parseColor("#01d410")); // green

        mMediaPlayerGo = MediaPlayer.create(ExerciseActivity.this, R.raw.go);
        mMediaPlayer = MediaPlayer.create(ExerciseActivity.this, R.raw.stop);

        // Gots to do some shiftying of my code around.
        final CountDownTimer countdownTimer = new CountDownTimer(5000, 1) {

            public void onTick(long millisUntilFinished) {
                mCountdownView.setText("Time remaining: " + (millisUntilFinished / 1000) + "." + (millisUntilFinished % 1000));
                //mCountdownView.setText("Time remaining: " + millisUntilFinished) ;
            }

            public void onFinish() {

                mMediaPlayer.stop();
                mMediaPlayer.reset();

                // Abstract the following code out.

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



                // Am I playing the correct file(s)?
                int numSecs = mMediaPlayer.getDuration() / 1000;

                Log.i(TAG, Integer.toString(numSecs) + "\n");

                mMediaPlayer.start();

                mCountdownView.setText("You did it!");
                mStartStopButton.setText("Start");
                mStartStopButton.setTextColor(Color.parseColor("#01d410")); // green
            }
        };

        // This seems hackish to me only because this listener depends on the countdown timer defined above.
        // I should make this neater.
        //
        // This works for the time being but there needs to be additionally defined behavior if
        // the user stops a workout before it completes.
        mMediaPlayerGo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                countdownTimer.start();
            }
        });

        mStartStopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                if (mStartStopButton.getText().equals("Start")) {

                    mStartStopButton.setText("Stop");
                    mStartStopButton.setTextColor(Color.parseColor("#ff0505")); // red

                    mMediaPlayerGo.stop();
                    mMediaPlayerGo.reset();

                    // Files in the resource directory are not actually treated as individual files, but rather one mega file.
                    // If you do not set the data source with a start time and length, it was just play all of your resource audio files in alphabetic order.
                    //
                    // Explanation:
                    // http://stackoverflow.com/questions/3289038/play-audio-file-from-the-assets-directory

                    try {
                        Log.i(TAG, "start offset = " + mGoFD.getStartOffset());
                        Log.i(TAG, "length       = " + mGoFD.getLength());

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

                    // Countdown timer cannot start until sound file finishes...

                    //countdownTimer.start();

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
