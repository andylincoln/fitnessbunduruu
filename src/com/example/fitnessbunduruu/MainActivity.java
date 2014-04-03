package com.example.fitnessbunduruu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	public final String TAG="FinalProject";
	
	private final static int REQUEST_CODE = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button button = (Button) findViewById(R.id.buttonExercise);
		button.setOnClickListener(new OnClickListener() {

			// Call startExplicitActivation() when pressed
			@Override
			public void onClick(View v) {			
				
				Intent intent = new Intent(MainActivity.this, ExerciseActivity.class);
				
				Log.i(TAG, "Sending Intent ");
				
				startActivityForResult (intent, REQUEST_CODE);	
				
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
