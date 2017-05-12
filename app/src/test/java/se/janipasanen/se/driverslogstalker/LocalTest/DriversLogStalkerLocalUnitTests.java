package se.janipasanen.se.driverslogstalker.LocalTest; /**
 * Created by jani on 2017-05-12.
 */

import org.junit.Test;
import java.util.regex.Pattern;

import se.janipasanen.driverslogstalker.FragmentForTabLog;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class DriversLogStalkerLocalUnitTests {

    FragmentForTabLog fftl = new FragmentForTabLog();
    String latSend = "12.44443";
    String longSend = "23.5645644";
    String falseTestData = "00.00000";


    @Test
    public void TestThatShouldFail() {
        fftl.setLatLong(latSend,longSend);
        String latitude = fftl.getLat();
        assertTrue(falseTestData.equals(latitude));


    }

    @Test
    public void TestLatEqualsSetData() {
        fftl.setLatLong(latSend,longSend);
        String latitude = fftl.getLat();
        assertTrue(latSend.equals(latitude));



    }

    @Test
    public void testLongEqualsSetData() {
        fftl.setLatLong(latSend,longSend);
        String longitude = fftl.getLong();
        assertTrue(longSend.equals(longitude));
    }



}
