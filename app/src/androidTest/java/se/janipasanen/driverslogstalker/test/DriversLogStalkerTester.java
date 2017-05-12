package se.janipasanen.driverslogstalker.test;

import se.janipasanen.driverslogstalker.MainActivity;

import android.app.Instrumentation.ActivityMonitor;
import android.content.Context;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DriversLogStalkerTester  extends ActivityInstrumentationTestCase2<MainActivity> {

	public DriversLogStalkerTester() {
		super(MainActivity.class);
		// TODO Auto-generated constructor stub
	}

}
