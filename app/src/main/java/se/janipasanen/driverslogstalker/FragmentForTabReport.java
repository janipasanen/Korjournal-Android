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

import android.content.Context;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class FragmentForTabReport extends Fragment {

	DriversLogDatabaseAdapter driversLogDatabaseAdapter;
	private Button buttonVA;
	
	
	public FragmentForTabReport() {
		// Required empty public constructor
	}

	public void onStart() {
		super.onStart();
		driversLogDatabaseAdapter = new DriversLogDatabaseAdapter(getActivity());

		buttonVA = (Button) getActivity().findViewById(R.id.viewAllData);
		
	
		// Tell the listener which toggle button to listen to
		
		buttonVA.setOnClickListener(new View.OnClickListener() {
	
			@Override
			public void onClick(View view) {
				try {
					// TODO NullPointerException on String data
						
		
					String data = driversLogDatabaseAdapter.getAllData();
					// Print out a message as a toast on the screen that onUpgrade was called.
					 ToastMessage.toastmessage(getActivity(), data);	
					
				} catch (Exception e) {
					  // Will log error if an error was catched
					  Log.e("Jani", "Exception : " + e.toString());
					  e.printStackTrace();
					  
				}
				
			}
		}); 	
}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
	
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_for_tab_report, container,
				false);
	}

	
	
}
