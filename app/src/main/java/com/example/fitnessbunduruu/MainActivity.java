/* MainActivity.java */

package com.example.fitnessbunduruu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends Activity {

    public final String TAG = "FinalProject";

    public String extraTag = "Category";

    private String[] EXERCISE_CATEGORIES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EXERCISE_CATEGORIES = getResources().getStringArray(R.array.categories);

        Button cardioButton = (Button) findViewById(R.id.buttonCardio);
        Button strengthButton = (Button) findViewById(R.id.buttonStrength);
        Button randomButton = (Button) findViewById(R.id.buttonRandom);

        cardioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selectedCategory = getResources().getString(R.string.cardio);
                Intent intent;

                intent = new Intent(getApplicationContext(), SelectActivity.class);
                intent.putExtra(extraTag, selectedCategory);

                startActivity(intent);
            }
        });

        strengthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selectedCategory = getResources().getString(R.string.strength);
                Intent intent;

                intent = new Intent(getApplicationContext(), SelectActivity.class);

                intent.putExtra(extraTag, selectedCategory);
                startActivity(intent);
            }
        });

        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selectedCategory;
                Intent intent;

                Random random = new Random();

                int index = random.nextInt(EXERCISE_CATEGORIES.length);
                selectedCategory = EXERCISE_CATEGORIES[index];

                intent = new Intent(getApplicationContext(), SelectActivity.class);
                intent.putExtra(extraTag, selectedCategory);

                startActivity(intent);
            }
        });

        Log.i(TAG, "EA OnCreate");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
