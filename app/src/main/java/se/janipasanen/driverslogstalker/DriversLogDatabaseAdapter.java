package se.janipasanen.driverslogstalker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DriversLogDatabaseAdapter  {
	
	DriversLogHelper helper;
	public DriversLogDatabaseAdapter(Context context) {
		helper=new DriversLogHelper(context);
		
	}
	
	public void getwritabledb() {
		SQLiteDatabase db = helper.getWritableDatabase();
	}
	
	
	public long insertStartLoggingData (String latitude, String longitude, String odometer, String purposeoftrip) {
		
		
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues contentValues=new ContentValues();
		contentValues.put(DriversLogHelper.COLUMN_FROMLAT, latitude);
		contentValues.put(DriversLogHelper.COLUMN_FROMLONG, longitude);
		contentValues.put(DriversLogHelper.COLUMN_STARTODOMETER, odometer);
		contentValues.put(DriversLogHelper.COLUMN_PURPOSEOFTRIP, purposeoftrip);
			
		
		long id = db.insert(DriversLogHelper.TABLE_NAME, null, contentValues);
		helper.close();
		return id;
		
	}
	
//	public int insertUpdateStopLoggingData (String tolatitude, String tolongitude, String odometer2) {
	public int insertUpdateStopLoggingData (String tolatitude, String tolongitude, String odometer2) {
		
		// Update driverslogstalkerdb.driverslog where _id =(SELECT max(_id) FROM driverslog
		
//		String rowSelection = "=(SELECT max(_id)";
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues contentValues=new ContentValues();

	
		
		contentValues.put(DriversLogHelper.COLUMN_TOLAT, tolatitude);
		contentValues.put(DriversLogHelper.COLUMN_TOLONG, tolongitude);
		contentValues.put(DriversLogHelper.COLUMN_ARRIVEODOMETER, odometer2);
		
//		String[] whereArgs={rowSelection};
		int count = db.update(DriversLogHelper.TABLE_NAME, contentValues, DriversLogHelper.COLUMN_ID+" = (SELECT max(_id) FROM driverslog)", null);

		return count;
		
	}
	
		
	public void updateFromAddress() {
		
	}
	
	
	public void updateToAddress() {
		
	}
	
	
	public void deleteRow() {
		
	}

	public String getAllData() {
		
		SQLiteDatabase db = helper.getWritableDatabase();
		
		// select _id, fromaddress, toaddress, startodometer, arriveodometer, purposeoftrip
		String[] columns = {DriversLogHelper.COLUMN_ID, DriversLogHelper.COLUMN_FROMADDRESS, DriversLogHelper.COLUMN_TOADDRESS, 
				DriversLogHelper.COLUMN_STARTODOMETER, DriversLogHelper.COLUMN_ARRIVEODOMETER, DriversLogHelper.COLUMN_PURPOSEOFTRIP  };
		
		Cursor cursor = db.query(DriversLogHelper.TABLE_NAME, columns, null, null, null, null, null, null);
		
		StringBuffer buffer = new StringBuffer();
		while(cursor.moveToNext()) {
			int index1 = cursor.getColumnIndex(DriversLogHelper.COLUMN_ID);
			int index2 = cursor.getColumnIndex(DriversLogHelper.COLUMN_FROMADDRESS);
			int index3 = cursor.getColumnIndex(DriversLogHelper.COLUMN_TOADDRESS);
			int index4 = cursor.getColumnIndex(DriversLogHelper.COLUMN_STARTODOMETER);
			int index5 = cursor.getColumnIndex(DriversLogHelper.COLUMN_ARRIVEODOMETER);
			int index6 = cursor.getColumnIndex(DriversLogHelper.COLUMN_PURPOSEOFTRIP);
			
			int cid=cursor.getInt(index1);
			String from=cursor.getString(index2);
			String to=cursor.getString(index3);
			int startodometer=cursor.getInt(index4);
			int arriverodometer=cursor.getInt(index5);
			String purposeoftrip=cursor.getString(index6);
			
			
			buffer.append(cid + " " + from + " " + to + " " + startodometer + " " + arriverodometer + " " + purposeoftrip + "\n");
//			cursor.close();
//			helper.close();
		}
		return buffer.toString();
		
	}
	
		public String getAllData2() {
			
//		Log.i("Jani", "getAllData2 called");	
			
			SQLiteDatabase db = helper.getReadableDatabase();
			
			// select _id
			String[] columns = { helper.COLUMN_ID  };
			
			Cursor cursor = db.query(helper.TABLE_NAME, columns, null, null, null, null, null, null);
			
			StringBuffer buffer = new StringBuffer();
			cursor.moveToFirst();
			while(cursor.moveToNext()) {
							
				int index1 = cursor.getColumnIndex(DriversLogHelper.COLUMN_ID);
				int cid = cursor.getInt(index1);
				buffer.append(cid + "\n");
			
			}
			return buffer.toString();
		
		
	}

		public String getLatitudeSQL() {
			
//			Log.i("Jani", "getAllData2 called");	
				
				SQLiteDatabase db = helper.getReadableDatabase();
				
				// select _id
				String[] columns = { helper.COLUMN_ID  };
				
				Cursor cursor = db.query(helper.TABLE_NAME, columns, null, null, null, null, null, null);
				
				StringBuffer buffer = new StringBuffer();
				cursor.moveToFirst();
				while(cursor.moveToNext()) {
								
					int index1 = cursor.getColumnIndex(DriversLogHelper.COLUMN_ID);
					int cid = cursor.getInt(index1);
					buffer.append(cid + "\n");
				
				}
				return buffer.toString();
			
			
		}

		public String getLongitudeSQL() {
			
//			Log.i("Jani", "getAllData2 called");	
				
				SQLiteDatabase db = helper.getReadableDatabase();
				
				// select _id
				String[] columns = { helper.COLUMN_ID  };
				
				Cursor cursor = db.query(helper.TABLE_NAME, columns, null, null, null, null, null, null);
				
				StringBuffer buffer = new StringBuffer();
				cursor.moveToFirst();
				while(cursor.moveToNext()) {
								
					int index1 = cursor.getColumnIndex(DriversLogHelper.COLUMN_ID);
					int cid = cursor.getInt(index1);
					buffer.append(cid + "\n");
				
				}
				return buffer.toString();
			
			
		}
	
	
	
	static class DriversLogHelper extends SQLiteOpenHelper {
	
	      private static final String DATABASE_NAME = "driverslogstalker.db";
		  private static final String TABLE_NAME = "driverslog";
		  private static final String COLUMN_ID   = "_id";
		  private static final String COLUMN_FROMLAT = "fromlat";
		  private static final String COLUMN_FROMLONG = "fromlong";
		  private static final String COLUMN_TOLAT = "tolat";
		  private static final String COLUMN_TOLONG = "tolong";
		  private static final String COLUMN_FROMADDRESS = "fromaddress";
		  private static final String COLUMN_TOADDRESS = "toaddress";
		  private static final String COLUMN_STARTODOMETER = "startodometer";
		  private static final String COLUMN_ARRIVEODOMETER = "arriveodometer";
		  private static final String COLUMN_PURPOSEOFTRIP = "purposeoftrip";
		  private static final String COLUMN_STARTDATE = "startdate";
		  private static final String COLUMN_STARTTIME = "starttime";
		  private static final String COLUMN_ARRIVEDATE = "arrivedate";
		  private static final String COLUMN_ARRIVETIME = "arrivetime";
		  private static final String COLUMN_CREATIONTIMESTAMP = "creationtimestamp";
		  
		  
		  private static final String COORDINATE_TYPE = " REAL";
		  private static final String TEXT_TYPE = " TEXT";
		  private static final String INT_TYPE = " INTEGER";
		  private static final String DATE_TYPE = " NUMERIC DEFAULT CURRENT_DATE";
		  private static final String TIME_TYPE = " NUMERIC DEFAULT CURRENT_TIME";
		  private static final String TIMESTAMP_TYPE  = " NUMERIC DEFAULT CURRENT_TIMESTAMP";
		 
		  private Context context;
		  
		  // TODO Don't forget to change database version if database schema is changed. Current version is 1.
		  private static final int    DATABASE_VERSION = 1;
		  
		  
		  

		  // Database creation sql statement
		  private static final String DATABASE_CREATE = "CREATE TABLE "
		      + TABLE_NAME + "(" + COLUMN_ID
		      + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
		      + COLUMN_FROMLAT + COORDINATE_TYPE + ", " 
		      + COLUMN_FROMLONG + COORDINATE_TYPE + ", "
		      + COLUMN_TOLAT + COORDINATE_TYPE + ", " 
		      + COLUMN_TOLONG + COORDINATE_TYPE + ", "
		      + COLUMN_FROMADDRESS + TEXT_TYPE + ", " 
		      + COLUMN_TOADDRESS + TEXT_TYPE + ", "
		      + COLUMN_STARTODOMETER + INT_TYPE + ", " 
		      + COLUMN_ARRIVEODOMETER + INT_TYPE + ", "
		      + COLUMN_PURPOSEOFTRIP + TEXT_TYPE + ", " 
		      + COLUMN_STARTDATE + DATE_TYPE + ", " 
		      + COLUMN_STARTTIME + TIME_TYPE + ", "
		      + COLUMN_ARRIVEDATE + DATE_TYPE + ", " 
		      + COLUMN_ARRIVETIME + TIME_TYPE + ", "
		      + COLUMN_CREATIONTIMESTAMP + TIMESTAMP_TYPE
		      + ");";

		  public DriversLogHelper(Context context) {
		    super(context, DATABASE_NAME, null, DATABASE_VERSION);
		    this.context=context;
		    
		    // Print out a message as a toast on the screen that this constructor was called.
//		    ToastMessage.toastmessage(context, "Constructor DriversLogHelper called");
		  }

		  @Override
		  public void onCreate(SQLiteDatabase db) {
			  try {  
					Log.w("Jani", " SQL: " + DATABASE_CREATE); 
				    db.execSQL(DATABASE_CREATE);
				    
				    // Print out a message as a toast on the screen that onCreate was called.
//				    ToastMessage.toastmessage(context, "onCreate called");
		    
			  } catch(SQLException e){
					 // e.printStackTrace();
					  
					  // Will log error if an error was catched
					  Log.e("Jani", "Exception occured while creating database: " + e + " with SQL statement: " +DATABASE_CREATE, e);
					  
					  // Print out the exception as a toast on the screen (Call the toastmessage method in the ToastMessage class).
					  ToastMessage.toastmessage(context, ""+e);
				  }
		  }

		  
		  
		  @Override
		  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			  try {
					// Print out a message as a toast on the screen that onUpgrade was called.
//					ToastMessage.toastmessage(context, "onUpgrade called");			
						  
					  // Log.w will output and log a warning 
				    Log.w("Jani",
				        "Upgrading database from version " + oldVersion + " to "
				            + newVersion + ", which will destroy all old data");
				    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
				    onCreate(db);
		    
		  
		    		    
				} catch (SQLException e) {
					 // Will log error if an error was catched
					  Log.e("Jani", DATABASE_CREATE + "SQLException: " + e, e);
					
					 // Print out the exception as a toast on the screen (Call the toastmessage method in the ToastMessage class).
					  ToastMessage.toastmessage(context, ""+e);
				}
		}
		
	}

}