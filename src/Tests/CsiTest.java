package Tests;


import com.Csi;
import com.Kupa;
import com.Measurements;
import com.Subject;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

public class CsiTest {
    @Test
    public void addNewLocationTest(){
        Csi csi = new Csi();

        csi.addNewLocation("location");

        assertTrue(csi.getLocations().containsKey("location"));
        assertTrue(csi.getObservers().containsKey("location"));

        assertTrue(csi.getLocations().get("location").isMeasureTemperature());
        assertTrue(csi.getLocations().get("location").isMeasureHumidity());
        assertTrue(csi.getLocations().get("location").isMeasurePressure());
    }

    @Test
    public void addNewLocationParametrizedTest(){
        Csi csi = new Csi();

        csi.addNewLocation("location", true, false, false);

        assertTrue(csi.getLocations().containsKey("location"));
        assertTrue(csi.getObservers().containsKey("location"));

        assertTrue(csi.getLocations().get("location").isMeasureTemperature());
        assertFalse(csi.getLocations().get("location").isMeasureHumidity());
        assertFalse(csi.getLocations().get("location").isMeasurePressure());
    }

    @Test
    public void registerObserverTest(){
        Csi csi = new Csi();
        Kupa k = new Kupa(csi, "Kuba");

        csi.addNewLocation("location");

        assertFalse(csi.registerObserver(k, "doesntExist"));
        assertTrue(csi.registerObserver(k, "location"));

        //already subscribed
        assertFalse(csi.registerObserver(k, "location"));
    }

    @Test
    public void removeObserverTest(){
        Csi csi = new Csi();
        Kupa k = new Kupa(csi, "Kuba");
        Kupa k1 = new Kupa(csi, "chada");

        csi.addNewLocation("location");

        csi.registerObserver(k,"location");
        csi.registerObserver(k1, "location");

        csi.removeObserver(k, "location");
        assertEquals(1, csi.getObservers().get("location").size());

        csi.removeObserver(k, "location");
        assertEquals(1, csi.getObservers().get("location").size());

        csi.removeObserver(k1, "location");
        assertEquals(0, csi.getObservers().get("location").size());
    }

    @Test
    public void notifyObserversTest(){
        Csi csi = new Csi();
        Kupa k = new Kupa(csi, "Kuba");
        csi.addNewLocation("location");

        k.subscribe("location");

        csi.getLocations().get("location").measure();

        csi.notifyObservers("location");

        assertEquals(1, k.getMeas().get("location").size());
    }

    @Test
    public void availableLocationsTest(){
        Csi csi = new Csi();
        csi.addNewLocation("location 1", true, false, true);
        csi.addNewLocation("location 2", false, true, false);
        HashMap<String, String> d = csi.availableLocationsAndSensors();

        assertEquals("TP", d.get("location 1"));
        assertEquals("H", d.get("location 2"));
    }


}
