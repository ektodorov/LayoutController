package com.example.testfragment.controlleret;

import java.util.ArrayList;

import android.view.ViewGroup;

public interface ControllerManagerInterface {
	
	public void setParentView(ViewGroup aParentView);
	public ViewGroup getParentView();

	public void push(ControllerInterface aController, boolean aAnimated);
	public void pop(boolean aAnimated);
	
	public int getControllersCount();
	public ControllerInterface getController(int aIndex);
	public ArrayList<ControllerInterface> getControllers();
	
	public void add(ControllerInterface aController, int aIndex);
	public void remove(int aIndex);
	public void remove(ControllerInterface aController);
}
