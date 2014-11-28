package com.blogspot.techzealous.testlayoutcontroller;

import com.example.testfragment.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FragmentMy extends Fragment {

	private int mNumber;
	
	public FragmentMy()
	{
		super();
	}
	
	public FragmentMy(int aNumber)
	{
		super();
		mNumber = aNumber;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if(container == null) {Log.i("FragmentPage", "conainer is null"); return null;}
		
		RelativeLayout root = (RelativeLayout)inflater.inflate(R.layout.fragmentmy_layout, container, false);
		TextView textView = (TextView)root.findViewById(R.id.textViewFragmentMy);
		EditText editText = (EditText)root.findViewById(R.id.editTextFragmentMy);
		
		textView.setText("Fragment " + mNumber);
		editText.setText("Fragment " + mNumber);
		
		return root;
	}
}
