package com.example.testfragment.controlleret;

import java.util.ArrayList;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testfragment.R;


public class ControllerManager implements ControllerManagerInterface, OnControllerLifecycleListener {
	
	private ArrayList<ControllerInterface> mArrayControllers;
	private ViewGroup mParentView;
	private Handler mHandler;
	private boolean mShouldWaitTransitionsToFinish = false;
	private boolean mIsFinished = true;
	
	public ControllerManager()
	{
		super();
		mArrayControllers = new ArrayList<ControllerInterface>();
		mHandler = new Handler();
	}
	
	public ControllerManager(ViewGroup aParentView)
	{
		super();
		mArrayControllers = new ArrayList<ControllerInterface>();
		mParentView = aParentView;
		mHandler = new Handler();
	}
	
	/* ControllerManagerInterface */
	@Override
	public void setParentView(ViewGroup aParentView) {
		mParentView = aParentView;
	}

	@Override
	public ViewGroup getParentView() {
		return mParentView;
	}
	
	@Override
	public void push(ControllerInterface aController, boolean aAnimated) {
		if(mShouldWaitTransitionsToFinish && !mIsFinished) {return;}
		mIsFinished = false;
		
		mArrayControllers.add(aController);
		final ControllerInterface constController = aController;
		if(aAnimated) {
			aController.setOnControllerLifecycleListener(new OnControllerLifecycleListener() {
				@Override
				public void onWillDetach(ControllerInterface aController) {
					//do nothing
				}
				
				@Override
				public void onWillAttach(ControllerInterface aController) {
					//do nothing
				}
				
				@Override
				public void onDetached(ControllerInterface aController, boolean aAnimated) {
					//do nothing
				}
				
				@Override
				public void onAttached(ControllerInterface aController, boolean aAnimated) {
					if(!aAnimated) {return;}
					
//					if(mArrayControllers.size() > 1) {
//						ControllerInterface controller = mArrayControllers.get(mArrayControllers.size() - 2);
//						View view = controller.getLayout();
//						TextView textView = (TextView)view.findViewById(R.id.textViewFragmentMy);
//						Log.i("ControllerManager", "controller=" + textView.getText().toString());
//						controller.detach(false);
//					}
					
					int idxLast = mArrayControllers.indexOf(constController);
					if(idxLast > 0) {
						mHandler.post(new Runnable() {
							@Override
							public void run() {
								int idxLast = mArrayControllers.indexOf(constController);
								ControllerInterface controller = mArrayControllers.get(idxLast - 1);
								View view = controller.getLayout();
								TextView textView = (TextView)view.findViewById(R.id.textViewFragmentMy);
								Log.i("ControllerManager", "Controller=" + textView.getText().toString());
								controller.detach(false);
							}
						});
					}
					mIsFinished = true;
				}
			});
			aController.attach(mParentView, aAnimated);
		} else {
			if(mArrayControllers.size() > 1) {
				ControllerInterface controller = mArrayControllers.get(mArrayControllers.size() - 2);
				controller.setOnControllerLifecycleListener(null);
				controller.detach(false);
			}
			aController.attach(mParentView, aAnimated);
			mIsFinished = true;
		}
	}

	@Override
	public void pop(boolean aAnimated) {
		if(mShouldWaitTransitionsToFinish && !mIsFinished) {return;}
		mIsFinished = false;
		
		if(aAnimated) {
			if(mArrayControllers.size() > 1) {
				ControllerInterface controller = mArrayControllers.get(mArrayControllers.size() - 2);
				int idx = mParentView.getChildCount();
				if(idx > 0) {idx = idx - 1;}
				controller.attach(mParentView, false, idx);
			}
			if(mArrayControllers.size() > 0) {
				ControllerInterface controller = mArrayControllers.get(mArrayControllers.size() - 1);
				controller.setOnControllerLifecycleListener(ControllerManager.this);
				controller.detach(aAnimated);
			}
		} else {
			if(mArrayControllers.size() > 0) {
				ControllerInterface controller = mArrayControllers.remove(mArrayControllers.size() - 1);
				controller.setOnControllerLifecycleListener(null);
				controller.detach(false);
			}
			if(mArrayControllers.size() > 0) {
				ControllerInterface controller = mArrayControllers.get(mArrayControllers.size() - 1);
				controller.setOnControllerLifecycleListener(null);
				controller.attach(mParentView, false);
			}
			mIsFinished = true;
		}
	}
	
	@Override
	public void setShouldWaitTransitionsToFinish(boolean aShouldWait)
	{
		mShouldWaitTransitionsToFinish = aShouldWait;
	}
	
	@Override
	public boolean getShouldWaitTransitionsToFinish()
	{
		return mShouldWaitTransitionsToFinish;
	}
	
	@Override
	public boolean getIsTransitionFinished()
	{
		return mIsFinished;
	}
	
	@Override
	public int getControllersCount() {
		return mArrayControllers.size();
	}

	@Override
	public ControllerInterface getController(int aIndex) {
		return mArrayControllers.get(aIndex);
	}

	@Override
	public ArrayList<ControllerInterface> getControllers() {
		return mArrayControllers;
	}

	@Override
	public void add(ControllerInterface aController, int aIndex) {
		mArrayControllers.add(aIndex, aController);
	}

	@Override
	public void remove(int aIndex) {
		mArrayControllers.remove(aIndex);
	}

	@Override
	public void remove(ControllerInterface aController) {
		mArrayControllers.remove(aController);
	}

	/* OnControllerLifecycleListener Interface */
	@Override
	public void onWillAttach(ControllerInterface aController) {
		//do nothing
	}

	@Override
	public void onWillDetach(ControllerInterface aController) {
		//do nothing
	}

	@Override
	public void onAttached(ControllerInterface aController, boolean aAnimated) {		
		
	}

	@Override
	public void onDetached(ControllerInterface aController, boolean aAnimated) {
		if(!aAnimated) {return;}
		
		if(mArrayControllers.size() > 0) {mArrayControllers.remove(mArrayControllers.size() - 1);}
		mIsFinished = true;
	}
	
}
