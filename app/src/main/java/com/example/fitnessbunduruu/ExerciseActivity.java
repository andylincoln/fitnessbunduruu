package com.example.fitnessbunduruu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.RadioGroup;

public class ExerciseActivity extends Activity {

    public final String TAG = "FinalProject";

    private RadioGroup mCategoryGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
