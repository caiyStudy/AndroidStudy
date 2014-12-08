package com.hxrainbow.AndroidStudy;

import java.util.ArrayList;
import java.util.List;

import com.hxrainbow.AndroidStudy.domain.ActivityItem;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FunctionListActivity extends ListActivity {
	
	private String type;
	private ListView listView;
	private List<ActivityItem> activityItems;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.empty_list);
		type = getIntent().getStringExtra("type");
		listView = (ListView)findViewById(android.R.id.list);
		
		ArrayAdapter<ActivityItem> arrayAdapter = getFunctionAdapter();
		
		listView.setAdapter(arrayAdapter);
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ActivityItem ai = activityItems.get(position);	
				
				if(ai.isLink()){
					openBrowser(FunctionListActivity.this, ai.getType());
				}else{		
					invokeIntent(ai);
				}
			}});
	}
	
	public static boolean openBrowser(Activity act, String url) {
    	try{
   
	    	if(url == null) return false;
	    	
	    	Uri uri = Uri.parse(url);
	    	Intent intent = new Intent(Intent.ACTION_VIEW, uri);	    	
	    	act.startActivity(intent);
    	
	    	return true;
    	}catch(Exception e){
    		return false;
    	}
    }
	
	private ArrayAdapter<ActivityItem> getFunctionAdapter(){
		activityItems = new ArrayList<ActivityItem>();
		
		int[] ids = getResList();
		
		String[] names = getResources().getStringArray(ids[0]);
		String[] values = getResources().getStringArray(ids[1]);
		
		for(int i = 0; i < names.length; i++){
			String name = names[i];
			String value = values[i];
			if(value.startsWith("http")){
				activityItems.add(new ActivityItem(null, name, value, null));
			}else{
				String[] vs = value.split(":");
				String meta = null;
				if(vs.length > 2){
					meta = vs[2];
				}
				activityItems.add(makeActivity(vs[0], name, vs[1], meta));
			}
			
			
		}
		
		ArrayAdapter<ActivityItem> result = new ArrayAdapter<ActivityItem>(this, R.layout.list_item, activityItems){
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				
				if(convertView == null){
					convertView = getLayoutInflater().inflate(R.layout.list_item, null);
				}
				
				ActivityItem ai = (ActivityItem) getItem(position);
				
				String text = ai.getName();
				String meta = ai.getMeta();
				
				if(meta != null){
					text += "   <small><small><font color=\"red\">" + meta + "</font></small></small>";
				}
				
				Spanned span = Html.fromHtml(text);
				TextView nameView = (TextView)convertView.findViewById(R.id.name);
				nameView.setText(span);
				
				return convertView;
			}
		};
		return result;
	}
	
	private int[] getResList(){
		if("async".equals(type)){
			return new int[]{R.array.async_names, R.array.async_values};
		}else if("image".equals(type)){
			return new int[]{R.array.image_names, R.array.image_values};
		}else if("auth".equals(type)){
			return new int[]{R.array.auth_names, R.array.auth_values};
		}else if("xml".equals(type)){
			return new int[]{R.array.xml_names, R.array.xml_values};
		}else if("location".equals(type)){
			return new int[]{R.array.location_names, R.array.location_values};
		}else if("service".equals(type)){
			return new int[]{R.array.service_names, R.array.service_values};
		}else if("eventbus".equals(type)){
			return new int[]{R.array.eventbus_names, R.array.eventbus_values};
		}
		else{
			return new int[]{R.array.top_names, R.array.top_values};
		}
	}
	
	private ActivityItem makeActivity(String cls, String name, String type, String meta){
		Class c = null;
		try {
			c = Class.forName(cls);			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return new ActivityItem(c, name, type, meta);
	}
	
	private void invokeIntent(ActivityItem ai){
		
		Class<?> cls = ai.getActivityClass();
		String type = ai.getType();
		
		Intent intent = new Intent(this, cls);
		intent.putExtra("type", type);
		
		startActivity(intent);
	}
	
}
