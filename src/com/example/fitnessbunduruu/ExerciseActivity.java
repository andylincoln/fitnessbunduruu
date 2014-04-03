package com.example.fitnessbunduruu;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ExerciseActivity extends Activity {

	public final String TAG="FinalProject";
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exercise);
			
		
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
