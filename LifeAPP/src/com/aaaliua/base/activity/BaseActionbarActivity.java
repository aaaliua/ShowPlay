package com.aaaliua.base.activity;



import com.aaaliua.lifeapp.R;
import com.dazhongcun.utils.Utils;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

/**
 * L的基类   有Toolbar
 * @author Administrator
 *
 */
@SuppressLint("NewApi")
public abstract class BaseActionbarActivity extends ActionBarActivity {

	public Toolbar mToolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 if (getLayoutResource() > 0) {
			 setContentView(getLayoutResource());
			 
			  mToolbar = (Toolbar) findViewById(R.id.toolbar);
			  if(mToolbar != null){
				  //把Toolbar设置成默认的actionbar
				  setSupportActionBar(mToolbar);
			  }
		 }
	}
	
	@Override
	public void setSupportActionBar(@Nullable Toolbar toolbar) {
		try{
			
			if(Utils.hasLollipop())
				toolbar.setElevation(1f);
			super.setSupportActionBar(toolbar);
		}catch(Throwable t){
			
		}
	}
	
	
	protected abstract int getLayoutResource();
}
