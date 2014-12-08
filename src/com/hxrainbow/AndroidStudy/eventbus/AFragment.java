package com.hxrainbow.AndroidStudy.eventbus;

import com.hxrainbow.AndroidStudy.R;
import com.hxrainbow.AndroidStudy.domain.eventbus.Event1To1;
import com.hxrainbow.AndroidStudy.domain.eventbus.Event1To2;

import de.greenrobot.event.EventBus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;



public class AFragment extends Fragment{
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(EventBusBasicActivity.EVENTBUS_TAG, "AFragment onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(EventBusBasicActivity.EVENTBUS_TAG, "AFragment onCreateView");
		View fragment =  inflater.inflate(R.layout.eventbus_a_fragment, container,false);
		initView(fragment);
		return fragment;
	}

	@Override
	public void onDestroy() {
		Log.i(EventBusBasicActivity.EVENTBUS_TAG, "AFragment onDestroy");
		super.onDestroy();
	}
	
	private void initView(View fragment) {
		Button buttonFirst = (Button)fragment.findViewById(R.id.buttonFirst);
		buttonFirst.setText("点我发送"+Event1To1.class.getSimpleName()+"事件");
		buttonFirst.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				EventBus.getDefault().post(new Event1To1("一对一事件"));
			}});
		
		Button buttonSecond = (Button)fragment.findViewById(R.id.buttonSecond);
		buttonSecond.setText("点我发送"+Event1To2.class.getSimpleName()+"事件");
		buttonSecond.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				EventBus.getDefault().post(new Event1To2("一对多事件"));
			}});
	}
	
}
