package com.hxrainbow.AndroidStudy.eventbus;

import com.hxrainbow.AndroidStudy.R;
import com.hxrainbow.AndroidStudy.domain.eventbus.Event1To2;

import de.greenrobot.event.EventBus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class CFragment extends Fragment{
	
	private TextView textView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(EventBusBasicActivity.EVENTBUS_TAG, "CFragment onCreate");
		EventBus.getDefault().register(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(EventBusBasicActivity.EVENTBUS_TAG, "CFragment onCreateView");
		View fragment = (View) inflater.inflate(R.layout.eventbus_c_fragment, container,false);
		initView(fragment);
		
		return fragment;
	}

	@Override
	public void onDestroy() {
		Log.i(EventBusBasicActivity.EVENTBUS_TAG, "CFragment onDestroy");
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}
	
	private void initView(View fragment) {
		textView = (TextView)fragment.findViewById(R.id.ctv);
	}
	
	public void onEvent(Event1To2 event){
		textView.setText("接收到事件："+event.toString());
	}
}
