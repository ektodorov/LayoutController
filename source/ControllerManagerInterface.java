package com.blogspot.techzealous.testlayoutcontroller.controlleret;

import android.view.ViewGroup;

import java.util.ArrayList;

public interface ControllerManagerInterface {
	
	/** Sets the parent view which to use for this ContrllerManager. 
	 * Each ControllerManager should be used with only parent view. If we have more than one parent view to which we want to add child views we
	 * should use separate ControllerManager which to control the view hierarchy for each parent view.
	 */
	public void setParentView(ViewGroup aParentView);
	
	/** Gets the parent view which this ControllerManager uses. */
	public ViewGroup getParentView();

	/** Adds (pushes) a new view to the stack of child views of the parent view this ControllerManager manages.
	 * @param aController ControllerInterface - controller containing the view we are adding.
	 * @param aAnimated boolean - indicates if the transition should be animated.
	 * @return boolean - if the ControllerInterface's view can be added to the parent view.
	 * 		If we have set the ControllerManager to wait for transitions to finish it won't add a new child view until the previous
	 * 		transition has finished. In this case the push method will return false, which means we have to wait for the transition to finish and then
	 * 		call push again.
	 */
	public boolean push(ControllerInterface aController, boolean aAnimated);
	
	/** Removes (pops) the top most child view from the stack of child views of the parent view this ControllerManager manages.
	 * @param aAnimated boolean - indicates if the transition should be animated.
	 * @return boolean - if the ControllerInterface's view can be removed from the parent view.
	 * 		If we have set the ControllerManager to wait for transitions to finish it won't remove a child view until the previous
	 * 		transition has finished. In this case the pop method will return false, which means we have to wait for the transition to finish and then
	 * 		call pop again.
	 */
	public boolean pop(boolean aAnimated);
	
	/** Sets if the ControllerManager should wait for the transitions when performing push, pop (adding, removing) child views should complete,
	 * before allowing a new push, pop (addition, removal) to be possible.
	 * Push, pop (additions, removals) can be put on a queue, or not. It is up to you which suites you best.
	 * @param aShouldWait boolean - indicates if the ControllerManager should wait for transitions to complete.
	 */
	public void setShouldWaitTransitionsToFinish(boolean aShouldWait);
	public boolean getShouldWaitTransitionsToFinish();
	
	/** Gets if the transition of push, pop (adding, removing) a child view to the parent view has completed. */
	public boolean getIsTransitionFinished();
	
	/** Gets the count of ControllerInterface layout controllers that this ControllerManager has on it's stack */
	public int getControllersCount();
	
	/** Gets a ControllerInterface layout controller from the stack of this ControllerManager.
	 * @param aIndex int - the index of the ControllerInterface layout controller in the stack.
	 * @return ControllerInterface - layout controller from the stack of this ControllerManager
	 */
	public ControllerInterface getController(int aIndex);
	
	/** Gets the stack of ControllerInterface layout controllers, that this ControllerManager manages. */
	public ArrayList<ControllerInterface> getControllers();
	
	/** Adds a ControllerInterface layout controller to the stack of this ControllerManager.
	 * This is not meant to be used to add on the top of the stack, we have push for that. This method is available if we want to
	 * change the ControllerInterface layout controllers that are not visible, below the top controller.
	 * @param aIndex int - at which index of the stack to add a controller
	 */
	public void add(ControllerInterface aController, int aIndex);
	
	/** Removes a ControllerInterface layout controller from the stack of this ControllerManager.
	 * This is not meant to be used to remove of the top of the stack, we have pop for that. This method is available if we want to
	 * change the ControllerInterface layout controllers that are not visible, below the top controller.
	 * @param aIndex int - at which index of the stack to remove a controller
	 */
	public void remove(int aIndex);
	
	/** Removes a ControllerInterface layout controller from the stack of this ControllerManager.
	 * This is not meant to be used to remove of the top of the stack, we have pop for that. This method is available if we want to
	 * change the ControllerInterface layout controllers that are not visible, below the top controller.
	 * @param aController ControllerInterface - which controller to remove
	 */
	public void remove(ControllerInterface aController);
}
