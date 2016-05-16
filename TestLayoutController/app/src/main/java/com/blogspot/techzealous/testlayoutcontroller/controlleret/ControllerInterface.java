package com.blogspot.techzealous.testlayoutcontroller.controlleret;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

public interface ControllerInterface {
	
	public ViewGroup getParentView();
	public void setParentView(ViewGroup aParentView);
	
	/** Gets the root view of this layout */
	public View getLayout();
	
	/** Sets the root view of this layout */
	public void setLayout(int aLayoutResource);
	
	/** Sets a listener for the life cycle events of the Controller */
	public void setOnControllerLifecycleListener(OnControllerLifecycleListener aLifecycleListener);
	
	/** Gets the listener for the life cycle events of the Controller */
	public OnControllerLifecycleListener getOnControllerLifecycleListener();
	
	/** Sets animation which to be performed when attaching the Controller's view to the parent view. */
	public void setAnimationIn(Animation aAnimation);
	
	/** Sets animation which to be performed when detaching the Controller's view from the parent view. */
	public void setAnimationOut(Animation aAnimation);
	
	/** Attach the Controller's view to the parent view
	 * @param aAnimated boolean - play animation when attaching the view.
	 * 		The animation will be the one set with setAnimationIn(Animation aAnimation);
	 */
	public void attach(boolean aAnimated);
	
	/** Attach the Controller's view to the parent view
	 * @param aParentView ViewGroup - parent view to which to attach the Controller's view
	 * @param aAnimated boolean - play animation when attaching the view.
	 * 		The animation will be the one set with setAnimationIn(Animation aAnimation);
	 */
	public void attach(ViewGroup aParentView, boolean aAnimated);
	
	/** Attach the Controller's view to the parent view
	 * @param aParentView ViewGroup - parent view to which to attach the Controller's view
	 * @param aViewResource int - view resource which to use as the Controller's view.
	 * 		It will be set as the Controller's view and also used when detaching.
	 * @param aAnimated boolean - play animation when attaching the view.
	 * 		The animation will be the one set with setAnimationIn(Animation aAnimation);
	 */
	public void attach(ViewGroup aParentView, int aViewResource, boolean aAnimated);
	
	/** Attach the Controller's view to the parent view
	 * @param aParentView ViewGroup - parent view to which to attach the Controller's view
	 * @param aViewResource int - view resource which to use as the Controller's view.
	 * 		It will be set as the Controller's view and also used when detaching.
	 * @param aCtx Context - context which to use with the Controller
	 * @param aAnimated boolean - play animation when attaching the view.
	 * 		The animation will be the one set with setAnimationIn(Animation aAnimation);
	 */
	public void attach(ViewGroup aParentView, int aViewResource, Context aCtx, boolean aAnimated);
	
	/** Attach the Controller's view to the parent view
	 * @param aParentView ViewGroup - parent view to which to attach the Controller's view
	 * @param aAnimated boolean - play animation when attaching the view.
	 * 		The animation will be the one set with setAnimationIn(Animation aAnimation);
	 * @param aAtIndex int - index of the parents child views at which to add the Controller's view.
	 */
	public void attach(ViewGroup aParentView, boolean aAnimated, int aAtIndex);
	
	/** Detach the Controller's view from the parent view
	 * @param aAnimated boolean - play animation whne detaching the view.
	 * 		The animation will be the one set with setAnimationOut(Animation aAnimation);
	 */
	public void detach(boolean aAnimated);
}
