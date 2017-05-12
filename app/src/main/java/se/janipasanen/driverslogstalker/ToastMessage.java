package se.janipasanen.driverslogstalker;

import android.content.Context;
import android.widget.Toast;

/**
 * ToastMessaga.java is a class to create toast messages and is inteded to be re-usable from other classes and in that way reduce amount of coding needed the create a toast from a perticular method.
 * @author Jani Pasanen
 * @version 1.0
 */
public class ToastMessage {
	public static void toastmessage(Context context, String toastmessage) {
		Toast.makeText(context, toastmessage, Toast.LENGTH_SHORT).show();
		
	}
}
