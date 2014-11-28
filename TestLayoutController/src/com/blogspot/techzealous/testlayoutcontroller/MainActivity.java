package com.blogspot.techzealous.testlayoutcontroller;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.blogspot.techzealous.testlayoutcontroller.controlleret.ControllerEt;
import com.blogspot.techzealous.testlayoutcontroller.controlleret.ControllerManager;
import com.example.testfragment.R;

public class MainActivity extends FragmentActivity {

	private Button buttonNext;
	private CheckBox checkBoxWait;
	
	private int mCount = 0;
	private ControllerManager mManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		buttonNext = (Button)findViewById(R.id.buttonMain);
		checkBoxWait = (CheckBox)findViewById(R.id.checkBoxWaitMain);
		
		buttonNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mManager.setShouldWaitTransitionsToFinish(checkBoxWait.isChecked());
				
				ControllerEt controller = new ControllerEt(MainActivity.this, R.layout.fragmentmy_layout);
				controller.setAnimationIn(AnimationUtils.loadAnimation(MainActivity.this, R.anim.in_from_right));
				controller.setAnimationOut(AnimationUtils.loadAnimation(MainActivity.this, R.anim.out_to_right));
				boolean isPushPossible = mManager.push(controller, true);
				
				if(isPushPossible) {
					View controllerView = controller.getLayout();
					TextView textView = (TextView)controllerView.findViewById(R.id.textViewFragmentMy);
					EditText editText = (EditText)controllerView.findViewById(R.id.editTextFragmentMy);
					textView.setText("Controller " + mCount);
					editText.setText("Controller " + mCount);
				
					mCount++;
				}
			}
		});
		
		ViewGroup parent = (ViewGroup)findViewById(R.id.frameLayoutPlaceholderMain);
		mManager = new ControllerManager(parent);
		mManager.setShouldWaitTransitionsToFinish(true);
		
		ControllerEt controller = new ControllerEt(this, R.layout.fragmentmy_layout, parent);
		controller.setAnimationIn(AnimationUtils.loadAnimation(this, R.anim.in_from_right));
		controller.setAnimationOut(AnimationUtils.loadAnimation(this, R.anim.out_to_right));
		mManager.push(controller, true);
		
		TextView textView = (TextView)findViewById(R.id.textViewFragmentMy);
		EditText editText = (EditText)findViewById(R.id.editTextFragmentMy);
		textView.setText("Controller " + mCount);
		editText.setText("Controller " + mCount);
		
		mCount++;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed()
	{
		if(mManager.getControllersCount() > 0) {
			mManager.setShouldWaitTransitionsToFinish(checkBoxWait.isChecked());
			mManager.pop(true);
		} else {
			super.onBackPressed();
		}
	}
}
