package com.blogspot.techzealous.testlayoutcontroller.controlleret;

public interface OnControllerLifecycleListener {

	/** Called before the view of the controller will be attached (added) to the parent view.
	 * @param aController ControllerInterface - the controller which view will be attached to the parent view.
	 */
	public void onWillAttach(ControllerInterface aController);
	
	/** Called before the view of the controller will be detached (removed) from it's parent view.
	 * @param aController ControllerInterface - the controller which view will be detached to the parent view.
	 */
	public void onWillDetach(ControllerInterface aController);
	
	/** Called after the view of the controller has been attached (added) to the parent view.
	 * @param aController ControllerInterface - the controller which view has been attached to the parent view.
	 */
	public void onAttached(ControllerInterface aController, boolean aAnimated);
	
	/** Called after the view of the controller has been detached (removed) from it's parent view.
	 * @param aController ControllerInterface - the controller which view has been detached to the parent view.
	 */
	public void onDetached(ControllerInterface aController, boolean aAnimated);
}
