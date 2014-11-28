package com.example.testfragment.controlleret;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class ControllerEt implements ControllerInterface {

	private static final String LOG = "ControllerEt";
	private ViewGroup mParentView;
	private View mViewLayout;
	private LayoutInflater mInflater;
	private Animation mAnimIn;
	private Animation mAnimOut;
	private OnControllerLifecycleListener mLifecycleListener;
	private Handler mHandler;
	
	public ControllerEt()
	{
		super();
		mHandler = new Handler();
	}
	
	public ControllerEt(Context aCtx)
	{
		super();
		mHandler = new Handler();
		mInflater = (LayoutInflater) aCtx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public ControllerEt(Context aCtx, int aLayoutResource)
	{
		super();
		mHandler = new Handler();
		mInflater = (LayoutInflater) aCtx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mViewLayout = mInflater.inflate(aLayoutResource, null, false);
	}
	
	public ControllerEt(Context aCtx, int aLayoutResource, ViewGroup aParentView)
	{
		super();
		mHandler = new Handler();
		mInflater = (LayoutInflater) aCtx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mParentView = aParentView;
		mViewLayout = mInflater.inflate(aLayoutResource, mParentView, false);
	}
	
	/* ControllerInterface */
	@Override
	public ViewGroup getParentView() {
		return mParentView;
	}

	@Override
	public void setParentView(ViewGroup aParentView) {
		mParentView = aParentView;
	}

	@Override
	public View getLayout()
	{
		return mViewLayout;
	}
	
	@Override
	public void setLayout(int aLayoutResource)
	{
		mViewLayout = mInflater.inflate(aLayoutResource, null, false);
	}
	
	@Override
	public void setOnControllerLifecycleListener(OnControllerLifecycleListener aLifecycleListener) 
	{
		mLifecycleListener = aLifecycleListener;
	}

	@Override
	public OnControllerLifecycleListener getOnControllerLifecycleListener() 
	{
		return mLifecycleListener;
	}
	
	@Override
	public void attach(boolean aAnimated) 
	{
		ViewParent viewParent = mViewLayout.getParent();
		if(viewParent != null) {
			Log.e(LOG, "attach(boolean aAnimated) failed the view already has a parent - " + viewParent);
			return;
		}
		
		if(mLifecycleListener != null) {mLifecycleListener.onWillAttach(this);}
		
		mParentView.addView(mViewLayout);
		if(aAnimated) {
			mViewLayout.startAnimation(mAnimIn);
		} else {
			if(mLifecycleListener != null) {mLifecycleListener.onAttached(this, false);}
		}
	}
	
	@Override
	public void attach(ViewGroup aParentView, boolean aAnimated)
	{
		ViewParent viewParent = mViewLayout.getParent();
		if(viewParent != null) {
			Log.e(LOG, "attach(ViewGroup aParentView, boolean aAnimated) failed the view already has a parent - " + viewParent);
			return;
		}
		
		if(mLifecycleListener != null) {mLifecycleListener.onWillAttach(this);}
		
		mParentView = aParentView;
		mParentView.addView(mViewLayout);
		if(aAnimated) {
			mViewLayout.startAnimation(mAnimIn);
		} else {
			if(mLifecycleListener != null) {mLifecycleListener.onAttached(this, false);}
		}
	}
	
	@Override
	public void attach(ViewGroup aParentView, int aViewResource, boolean aAnimated)
	{
		if(mLifecycleListener != null) {mLifecycleListener.onWillAttach(this);}
		
		mParentView = aParentView;
		mViewLayout = mInflater.inflate(aViewResource, aParentView, true);
		if(aAnimated) {
			mViewLayout.startAnimation(mAnimIn);
		} else {
			if(mLifecycleListener != null) {mLifecycleListener.onAttached(this, false);}
		}
	}
	
	@Override
	public void attach(ViewGroup aParentView, int aViewResource, Context aCtx, boolean aAnimated)
	{		
		if(mLifecycleListener != null) {mLifecycleListener.onWillAttach(this);}
		
		mParentView = aParentView;
		mInflater = (LayoutInflater) aCtx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mViewLayout = mInflater.inflate(aViewResource, aParentView, true);
		if(aAnimated) {
			mViewLayout.startAnimation(mAnimIn);
		} else {
			if(mLifecycleListener != null) {mLifecycleListener.onAttached(this, false);}
		}
	}
	
	@Override
	public void attach(ViewGroup aParentView, boolean aAnimated, int aAtIndex)
	{
		ViewParent viewParent = mViewLayout.getParent();
		if(viewParent != null) {
			Log.e(LOG, "attach(ViewGroup aParentView, boolean aAnimated, int aAtIndex) failed the view already has a parent - " + viewParent);
			return;
		}
		
		if(mLifecycleListener != null) {mLifecycleListener.onWillAttach(this);}
		
		mParentView = aParentView;
		mParentView.addView(mViewLayout, aAtIndex);
		if(aAnimated) {
			mViewLayout.startAnimation(mAnimIn);
		} else {
			if(mLifecycleListener != null) {mLifecycleListener.onAttached(this, false);}
		}
	}

	@Override
	public void detach(boolean aAnimated) {
		if(mLifecycleListener != null) {mLifecycleListener.onWillDetach(this);}
		if(aAnimated) {
			mViewLayout.startAnimation(mAnimOut);
		} else {
			mParentView.removeView(mViewLayout);
			if(mLifecycleListener != null) {mLifecycleListener.onDetached(this, false);}
		}
	}
	
	

	@Override
	public void setAnimationIn(Animation aAnimation) {
		mAnimIn = aAnimation;
		mAnimIn.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				//do nothing
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				//do nothing
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				if(mLifecycleListener != null) {mLifecycleListener.onAttached(ControllerEt.this, true);}
			}
		});
	}

	@Override
	public void setAnimationOut(Animation aAnimation) {
		mAnimOut = aAnimation;
		mAnimOut.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				//do nothing
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				//do nothing
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						mParentView.removeView(mViewLayout);
						if(mLifecycleListener != null) {mLifecycleListener.onDetached(ControllerEt.this, true);}
					}
				});
			}
		});
	}

}
