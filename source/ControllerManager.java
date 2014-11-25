package com.example.testfragment.controlleret;

import java.util.ArrayList;

import android.view.ViewGroup;


public class ControllerManager implements ControllerManagerInterface, OnControllerLifecycleListener {

	private ArrayList<ControllerInterface> mArrayControllers;
	private ViewGroup mParentView;
	
	public ControllerManager()
	{
		super();
		mArrayControllers = new ArrayList<ControllerInterface>();
	}
	
	public ControllerManager(ViewGroup aParentView)
	{
		super();
		mArrayControllers = new ArrayList<ControllerInterface>();
		mParentView = aParentView;
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
		mArrayControllers.add(aController);
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
					//aController.setOnControllerLifecycleListener(null);
					if(!aAnimated) {return;}
					
					if(mArrayControllers.size() > 1) {
						ControllerInterface controller = mArrayControllers.get(mArrayControllers.size() - 2);
						controller.detach(false);
					}
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
		}
	}

	@Override
	public void pop(boolean aAnimated) {
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
				controller.detach(aAnimated);
			}
			if(mArrayControllers.size() > 0) {
				ControllerInterface controller = mArrayControllers.get(mArrayControllers.size() - 1);
				controller.setOnControllerLifecycleListener(null);
				controller.attach(mParentView, false);
			}
		}
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
		//aController.setOnControllerLifecycleListener(null);
		if(!aAnimated) {return;}
		
		if(mArrayControllers.size() > 0) {mArrayControllers.remove(mArrayControllers.size() - 1);}
	}
	
}
