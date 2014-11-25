package com.example.testfragment.controlleret;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

public interface ControllerInterface {
	
	public ViewGroup getParentView();
	public void setParentView(ViewGroup aParentView);
	
	public View getLayout();
	public void setLayout(int aLayoutResource);
	
	public void setOnControllerLifecycleListener(OnControllerLifecycleListener aLifecycleListener);
	public OnControllerLifecycleListener getOnControllerLifecycleListener();
	
	public void setAnimationIn(Animation aAnimation);
	public void setAnimationOut(Animation aAnimation);
	
	public void attach(boolean aAnimated);
	public void attach(ViewGroup aParentView, boolean aAnimated);
	public void attach(ViewGroup aParentView, int aViewResource, boolean aAnimated);
	public void attach(ViewGroup aParentView, int aViewResource, Context aCtx, boolean aAnimated);
	public void attach(ViewGroup aParentView, boolean aAnimated, int aAtIndex);
	public void detach(boolean aAnimated);
}
