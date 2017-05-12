/*
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
 */

package se.janipasanen.driverslogstalker;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;



	public class MainActivity extends FragmentActivity implements TabListener  {
		public final static String EXTRA_MESSAGE = "se.janipasanen.driverslogstalker.MESSAGE";
	
		// Construct an object of the DriverLogHelper class 
		DriversLogDatabaseAdapter driversLogDatabaseAdapter;
		
		ViewPager viewPager;
		ActionBar actionBar;
		Context context;






		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
					
			
			// Start the lookup addresses thread. It looks through the database if there are cells of the fromaddress and toaddress columns that are empty and if so
			// get the address using latitude and longitude if not empty and then update the address to the right columns.
			AddressLookup insertaddress = new AddressLookup();
			
			// Initialize/Call the DriversLogHelper and its constructor and pass this as context to create the database
			driversLogDatabaseAdapter = new DriversLogDatabaseAdapter(this);
			
	
			
			/** 
			 *  Tell SQLite to create the database since the database object that represent the physical database
			 *  file has not yet been accessed by calling getWritableDatabase() using the database object driversLogDatabaseAdapter and the
			 *  driversLogHelper class.
			 *  
			 *  without calling getWritabelDatabase() the methods onCreate() and onUpgrade will not be called in DriversLogHelper (SQLiteOpenHelper).
			 *  
			 *  Here the method getwritabledb is called which uses the database object DriversLogHelper. 
			 */
			driversLogDatabaseAdapter.getwritabledb();
			
			/**
			 * Type casting from {@link View} to {@link ViewPager}
			 * ViewPager is solely responsible for handling users swiping for switching fragments (fragment views)
			 * 
			 */
			viewPager=(ViewPager) findViewById(R.id.pager);
			
			FragmentManager is = getSupportFragmentManager();
			// Skulle även kunna vara viewPager.setAdapter(new MyAdapter(getSupportFramentManager()));
			viewPager.setAdapter(new MyAdapter(is));
			
			// Connect the position of the tabs with page swiping (FragmentManager and ActionBar)
			viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
		
				
				// Connect the position of the tabs with page swiping (FragmentManager and ActionBar)	
				@Override
				public void onPageSelected(int arg0) {
					// If page is 0 then tab should also be set to 0, if page is 1 then tab should also be set to one (0,1,2....)
					actionBar.setSelectedNavigationItem(arg0);
					Log.d("Jani", "onPageSelected at " + " position " + arg0);
				}
				
				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {
		
//					Log.d("Jani", "onPageScrolled at " + " position " + arg0 + "from" + arg1 + " with number of pixels= " + arg2);
				}
				
				@Override
				public void onPageScrollStateChanged(int arg0) {
					//
//					if(arg0==ViewPager.SCROLL_STATE_IDLE) {
//						Log.d("Jani", "onPageScrollStateChanged Idle");
//					}
//					if(arg0==ViewPager.SCROLL_STATE_DRAGGING){
//						Log.d("Jani", "onPageScrollStateChanged Dragging");
//					}
//					if(arg0==ViewPager.SCROLL_STATE_SETTLING){
//						Log.d("Jani", "onPageScrollStateChanged SETTLING");
//					};
					
				}
			});
			
			
			actionBar=getActionBar();
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			
			ActionBar.Tab tabLog = actionBar.newTab();
			tabLog.setText("Log");
			tabLog.setTabListener(this);
			
			ActionBar.Tab tabReport = actionBar.newTab();
			tabReport.setText("Report");
			tabReport.setTabListener(this);
			
			actionBar.addTab(tabLog);
			actionBar.addTab(tabReport);





		}

	
	
		
		@Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle presses on the action bar items
	        switch (item.getItemId()) {
	        	case R.id.action_new:
	        		openNew();
	        		return true;
	            case R.id.action_search:
	                openSearch();
	                return true;
	            case R.id.action_settings:
	                openSettings();
	                return true;
	            default:
	                return super.onOptionsItemSelected(item);
	        }
	    }
	    
	    private void openNew() {
			// TODO Auto-generated method stub
	    	
	    	Context context = getApplicationContext();
	    	CharSequence text = "New Item Action pressed";
	    	int duration = Toast.LENGTH_SHORT;

	    	Toast toast = Toast.makeText(context, text, duration);
	    	toast.show();
			
		}
	    
	    
	    private void openSettings() {
			// TODO Auto-generated method stub
	    	
	    	Context context = getApplicationContext();
	    	CharSequence text = "Settings Action pressed";
	    	int duration = Toast.LENGTH_SHORT;

	    	Toast toast = Toast.makeText(context, text, duration);
	    	toast.show();
		}


		private void openSearch() {
			// TODO Auto-generated method stub
			
			Context context = getApplicationContext();
			CharSequence text = "Search Action pressed";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
			
		}
		
	

//		/** Called when the user clicks the Send button */
//	    public void startLogging(View view) {
//	        // Do something in response to button
//	    	Intent intent = new Intent(this, DisplayMessageActivity.class);
//	    	EditText editText1 = (EditText) findViewById(R.id.edit_lat);
//	    	   	
//	    	String message1 = editText1.getText().toString();
//	    	
//	    	intent.putExtra(EXTRA_MESSAGE, message1);
//	    	
//	    	startActivity(intent);
//
//	    }

		
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			
			/**
			 * Below {@link Log} displays in which position that tab is and the name of the tab.  
			 *
			 */
//			Log.d("Jani", "onTabSelected at " + " position " + tab.getPosition() + " name " + tab.getText());
			// When tab 0 or 1 is selected change the pagefragment to the same nubmer 0 or 1. (0,1,2 ...)
			viewPager.setCurrentItem(tab.getPosition());
			
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			/**
			 * 
			 * Below {@link Log.d} displays in which position that tab is and the name of the tab.  
			 *
			 */
//			Log.d("Jani", "onTabUnselected at " + " position " + tab.getPosition() + " name " + tab.getText());
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			/**
			 * 
			 * Below {@link Log.d} displays in which position that tab is and the name of the tab.
			 *   
			 */
//			Log.d("Jani", "onTabReselected at " + " position " + tab.getPosition() + " name " + tab.getText());
		}




	}
	

	
	
	class MyAdapter extends FragmentPagerAdapter {

		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			Fragment fragment=null;
			if(arg0==0) {
				fragment=new FragmentForTabLog();
			}
			if(arg0==1) {
				fragment=new FragmentForTabReport();
			}
			return fragment;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 2;
		}
		

		
	}