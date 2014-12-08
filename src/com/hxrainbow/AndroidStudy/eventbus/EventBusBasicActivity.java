package com.hxrainbow.AndroidStudy.eventbus;

import com.hxrainbow.AndroidStudy.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class EventBusBasicActivity extends FragmentActivity {

	public static final String EVENTBUS_TAG = "eventbus";
	private Fragment[] mFragments;
	private Button[] mButtons;
	private int currentTabIndex;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(EVENTBUS_TAG, "EventBusBasicActivity onCreate");
		setContentView(R.layout.eventbus_basic);
		
		initView();
	}
	
	@Override
	protected void onDestroy() {
		Log.i(EVENTBUS_TAG, "EventBusBasicActivity onDestroy");
		super.onDestroy();
	}
	
	private void initView(){
		initMButton();
		initMFragment();
	}

	private void initMButton() {
		mButtons = new Button[3];
		mButtons[0] = (Button) findViewById(R.id.btn_conversation);
		mButtons[1] = (Button) findViewById(R.id.btn_address_list);
		mButtons[2] = (Button) findViewById(R.id.btn_setting);
		// 把第一个tab设为选中状态
		mButtons[0].setSelected(true);
	}
	
	private void initMFragment() {
		Fragment aFragment = new AFragment();
		Fragment bFragment = new BFragment();
		Fragment cFragment = new CFragment();
		mFragments = new Fragment[]{aFragment,bFragment,cFragment};
		this.getSupportFragmentManager().beginTransaction()
			.add(R.id.fragment_container, aFragment).add(R.id.fragment_container,  bFragment).add(R.id.fragment_container, cFragment)
			.hide(bFragment).hide(cFragment)
			.show(aFragment)
			.commit();
	}

	
	/**
	 * button点击事件
	 */
	public void onTabClicked(View view) {
		int clickIndex = -1;
		switch (view.getId()) {
		case R.id.btn_conversation:
			clickIndex = 0;
			break;
		case R.id.btn_address_list:
			clickIndex = 1;
			break;
		case R.id.btn_setting:
			clickIndex = 2;
			break;
		}
		
		if (currentTabIndex != clickIndex) {
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.hide(mFragments[currentTabIndex]);
			if (!mFragments[clickIndex].isAdded()) {
				ft.add(R.id.fragment_container, mFragments[clickIndex]);
			}
			ft.show(mFragments[clickIndex]).commit();
		}
		mButtons[currentTabIndex].setSelected(false);
		// 把当前tab设为选中状态
		mButtons[clickIndex].setSelected(true);
		currentTabIndex = clickIndex;
	}
}
