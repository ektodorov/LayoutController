package com.example.testfragment.controlleret;

public interface OnControllerLifecycleListener {

	public void onWillAttach(ControllerInterface aController);
	public void onWillDetach(ControllerInterface aController);
	public void onAttached(ControllerInterface aController, boolean aAnimated);
	public void onDetached(ControllerInterface aController, boolean aAnimated);
}
