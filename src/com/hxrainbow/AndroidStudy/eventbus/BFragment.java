package com.hxrainbow.AndroidStudy.eventbus;

import com.hxrainbow.AndroidStudy.R;
import com.hxrainbow.AndroidStudy.domain.eventbus.Event1To1;
import com.hxrainbow.AndroidStudy.domain.eventbus.Event1To2;

import de.greenrobot.event.EventBus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;



public class BFragment extends Fragment{
	
	private TextView textView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(EventBusBasicActivity.EVENTBUS_TAG, "BFragment onCreate");
		EventBus.getDefault().register(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(EventBusBasicActivity.EVENTBUS_TAG, "BFragment onCreateView");
		View fragment = (View) inflater.inflate(R.layout.eventbus_b_fragment, container,false);
		initView(fragment);
		
		return fragment;
	}

	@Override
	public void onDestroy() {
		Log.i(EventBusBasicActivity.EVENTBUS_TAG, "BFragment onDestroy");
		EventBus.getDefault().unregister(this);
		super.onDestroy();
	}
	
	private void initView(View fragment) {
		textView = (TextView)fragment.findViewById(R.id.btv);
	}
	
	public void onEvent(Event1To1 event){
		String s = textView.getText().toString();
		String message = s + "接收到PostThread事件："+event.toString();
		textView.setText(Html.fromHtml(message));
	}
	
	public void onEventMainThread(Event1To1 event){
		String s = textView.getText().toString();
		String message = s + "接收到MainThread事件："+event.toString();
		textView.setText(Html.fromHtml(message));
	}
	
	public void onEventBackgroundThread(Event1To1 event){
		final String eventStr = event.toString();
		this.getActivity().runOnUiThread(new Runnable(){
			@Override
			public void run() {
				String s = textView.getText().toString();
				final String message = s + "接收到BackgroundThread事件："+eventStr;
				textView.setText(Html.fromHtml(message));
			}});
	}
	
	public void onEventAsync(Event1To1 event){
		final String eventStr = event.toString();
		this.getActivity().runOnUiThread(new Runnable(){
			@Override
			public void run() {
				String s = textView.getText().toString();
				final String message = s + "接收到Async事件："+eventStr;
				textView.setText(Html.fromHtml(message));
			}});
	}
	
	public void onEvent(Event1To2 event){
		textView.setText("接收到事件："+event.toString());
	}
}
