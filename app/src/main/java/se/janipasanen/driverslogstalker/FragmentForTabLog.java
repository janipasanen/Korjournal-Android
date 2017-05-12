/**
 * Copyright 2014 Jani Pasanen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * 
 */



package se.janipasanen.driverslogstalker;


import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.support.v4.app.Fragment;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass that controls the functionality of the logging fragment (tab) of the app.
 * 
 * @author Jani Pasanen
 * @version 1.0
 */
public class FragmentForTabLog extends Fragment {
	public final static String EXTRA_MESSAGE = "se.janipasanen.driverslogstalker.MESSAGE";
	
	private ToggleButton tB;
//    private static Context context;
    protected Criteria criteria;
    protected Context context;
    
    // Find the TextViews
    TextView textLat, textLong;
    
    
    // Find the EditText
	EditText edit_startodometer, edit_arriveodometer, edit_purposeoftrip;
    DriversLogDatabaseAdapter driversLogDatabaseAdapter;
		
	public FragmentForTabLog() {
		// Required empty public constructor
	
	}
	
	public void onStart() {
		super.onStart();
		
		
		// Initialise the TextViews
		textLat = (TextView) getActivity().findViewById(R.id.textLat);
		textLong = (TextView) getActivity().findViewById(R.id.textLong);
		
		
		// Create an object for the LocationManager to get Lat. and Long.
		LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
		criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_LOW);
		
		LocationListener ll = new mylocationlistener();
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60000, 300, ll);
		
	
		// Initialise the EditTexts

		edit_startodometer=(EditText) getActivity().findViewById(R.id.edit_startodometerValue);
		edit_arriveodometer=(EditText) getActivity().findViewById(R.id.edit_arriveodometerValue);
		edit_purposeoftrip=(EditText) getActivity().findViewById(R.id.edit_purposeoftripValue);
		
		driversLogDatabaseAdapter= new DriversLogDatabaseAdapter(getActivity());
		
		
		
		
		
		
//		/*
//		 * Initialize the toggle button on the fragment for tab log layout. (Type Mismatch: cannot convert from View to ToggleButton so must add cast to ToggleButton
//		 * when code was tB=findViewById(R.id.toggleLoggingButton);
//		 * 
//		 * After casting code is:
//		 * tB=(ToggleButton) findViewById(R.id.toggleLoggingButton);
//		 * 
//		 */
//		//tB=(ToggleButton) findViewById(R.id.toggleLoggingButton);
		
		
		
		/*  klura ut varför findViewById(Int) är odefinierad för FragmentForTabLog ;  return inflater var ovanför därför letar inte programmet
		 * längre ner i koden samt man måste använda getActivity() för att få findViewById.
		 *
		 */
		
		
		tB = (ToggleButton) getActivity().findViewById(R.id.toggleLoggingButton);
		
		
		
		// Tell the listener which toggle button to listen to
		
		tB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (tB.isChecked()) {
			        // write start data to database
			    	// Print out a message as a toast on the screen that this constructor was called.
//			        ToastMessage.toastmessage(getActivity(), "Start logging pressed");
			        
				        // Get the values from the EditTexts
				        String latitude=textLat.getText().toString();
						String longitude=textLong.getText().toString();
						String odometer=edit_startodometer.getText().toString();
						String purposeoftrip=edit_purposeoftrip.getText().toString();
						
						String fromaddress = "NOTHING";
						String toaddress = "NOTHING";
						

			        
						long id = driversLogDatabaseAdapter.insertStartLoggingData(latitude, longitude, odometer, purposeoftrip, fromaddress, toaddress);
						
//						if (id<0) {
//							ToastMessage.toastmessage(getActivity(), "Unsuccesful");
//						} else {
//							ToastMessage.toastmessage(getActivity(), "Succesfully inserted a row");
//						}
						
						
			        
//			    	Context context = getApplicationContext();
//			    	CharSequence text = "New Item Action pressed";
//			    	int duration = Toast.LENGTH_SHORT;
//
//			    	Toast toast = Toast.makeText(context, text, duration);
//			    	toast.show();
			        
				} else {
			        // write arrival data to database
			    	// Print out a message as a toast on the screen that this constructor was called.
					// getActivity() used here to get the this.context (The context of this tab/fragment.
					
//			        ToastMessage.toastmessage(getActivity(), "Stop logging pressed");		
			        

			        
				        // Get the values from the EditTexts
						String tolatitude=textLat.getText().toString();
						String tolongitude=textLong.getText().toString();
						String odometer2=edit_arriveodometer.getText().toString();
						
						int count = driversLogDatabaseAdapter.insertUpdateStopLoggingData(tolatitude, tolongitude, odometer2);
						
						if (count==0) {
							ToastMessage.toastmessage(getActivity(), "Unsuccesful");
						} else if (count>1){
							ToastMessage.toastmessage(getActivity(), "Unsuccesful, updated more then one row");
						} else {
							ToastMessage.toastmessage(getActivity(), "Trip succesfully logged");
						}
			        
			}
		    			
			/**
			 * compounButton is actually the name of the button that was clicked. In this case it is ToggleButton (The object name is tB) and the boolean, isChecked, defines is the button was set or unset, true or false.
			 * 
			 * This method controls what happens when the user presses on the "Start logging/Stop logging" toggle button.
			 * 
			 */
		
			}
	
		});
	}
	
	
	class mylocationlistener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			// get Lat. and Long. from the service
			if(location != null) {
				double pLat = location.getLatitude();
				double pLong = location.getLongitude();
				
				textLat.setText(Double.toString(pLat));
				textLong.setText(Double.toString(pLong));
			}
		}

		@Override
		public void onStatusChanged(String provider, int status,
				Bundle extras) {
			// TODO Auto-generated method stub
				
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
	}
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
	
		
		return inflater.inflate(R.layout.fragment_for_tab_log, container, false);
	}

	
	
}


