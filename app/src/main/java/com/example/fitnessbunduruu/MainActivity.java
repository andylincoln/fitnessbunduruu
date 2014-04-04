package com.example.fitnessbunduruu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {

    public final String TAG = "FinalProject";

    public String extraTag = "Category";

    private String[] EXERCISE_CATEGORIES;

    private TextView mTextExercise;
    private Button mButtonExercise;

    private RadioGroup mCategoryGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EXERCISE_CATEGORIES = getResources().getStringArray(R.array.categories);

        mCategoryGroup = (RadioGroup) findViewById(R.id.CategoryGroup);

        mCategoryGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                Intent intent;

                switch (checkedId) {
                    case R.id.buttonCardio:

                        intent = new Intent(getApplicationContext(), ExerciseActivity.class);
                        intent.putExtra(extraTag, getResources().getString(R.string.cardio));

                        startActivity(intent);

                        break;
                    case R.id.buttonStrength:

                        intent = new Intent(getApplicationContext(), ExerciseActivity.class);
                        intent.putExtra(extraTag, getResources().getString(R.string.strength));

                        startActivity(intent);

                        break;
                    case R.id.buttonRandom:

                        String selectedCategory;

                        Random random = new Random();
                        int index = random.nextInt(EXERCISE_CATEGORIES.length);
                        selectedCategory = EXERCISE_CATEGORIES[index];

                        intent = new Intent(getApplicationContext(), ExerciseActivity.class);
                        intent.putExtra(extraTag, selectedCategory);

                        startActivity(intent);

                        break;
                }
            }
        });

        Log.i(TAG, "EA OnCreate");

        //Log.i(TAG,rightNow.getTime().toString());
        //Log.i(TAG,String.valueOf(rightNow.get(Calendar.DAY_OF_WEEK)));
        //Log.i(TAG,String.valueOf(rightNow.get(Calendar.DAY_OF_MONTH)));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
