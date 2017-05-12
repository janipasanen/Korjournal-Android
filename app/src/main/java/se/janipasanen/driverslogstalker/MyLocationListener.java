package se.janipasanen.driverslogstalker;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by jani on 2017-05-12.
 */

class MyLocationListener implements LocationListener {

    // Datatyp för lagring av data för gps koordinat för senare inmatning till db.
    private String textLatLocal;
    private String textLongLocal;



    @Override
    public void onLocationChanged(Location location) {
        // get Lat. and Long. from the service
        if(location != null) {
            double pLat = location.getLatitude();
            double pLong = location.getLongitude();

            textLatLocal = Double.toString(pLat);
            textLongLocal = Double.toString(pLong);

            FragmentForTabLog fft = new FragmentForTabLog();
            fft.setLatLong(textLatLocal, textLongLocal);

            //	textLat.setText(Double.toString(pLat));
            //	textLong.setText(Double.toString(pLong));
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
