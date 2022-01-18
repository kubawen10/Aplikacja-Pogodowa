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

class KupaTest {
    @Test
    public void calculateAvgTest() {
        Subject s = new Csi();
        Kupa k = new Kupa(s, "Kuba");
        List<Integer> values = new ArrayList<>(Arrays.asList(5,6,7));
        assertEquals(6.0, k.calculateAvg(values));
    }

    @Test
    public void calculateMinTest() {
        Subject s = new Csi();
        Kupa k = new Kupa(s, "Kuba");
        List<Integer> values = new ArrayList<>(Arrays.asList(5,6,7));
        assertEquals(5, k.calculateMin(values));
    }

    @Test
    public void calculateMaxTest() {
        Subject s = new Csi();
        Kupa k = new Kupa(s, "Kuba");
        List<Integer> values = new ArrayList<>(Arrays.asList(5,6,7));
        assertEquals(7, k.calculateMax(values));
    }

    @Test
    public void updateTest(){
        //checking if measurements list size increases
        Subject csiMock = mock(Csi.class);
        Kupa k = new Kupa(csiMock, "Kuba");
        when(csiMock.registerObserver(k, "location")).thenReturn(true);
        k.subscribe("location");
        Measurements m = new Measurements(1,2,3);

        k.update("location", m);

        assertEquals(1, k.getMeas().get("location").size());
        k.update("location", m);

        assertEquals(2, k.getMeas().get("location").size());
    }

    @Test
    public void subscribeSuccessfulTest(){
        Subject csiMock = mock(Csi.class);
        Kupa k = new Kupa(csiMock, "Kuba");

        when(csiMock.registerObserver(k, "location")).thenReturn(true);
        k.subscribe("location");
        assertTrue(k.getMeas().containsKey("location"));
    }

    @Test
    public void subscribeUnsuccessfulTest(){
        Subject csiMock = mock(Csi.class);
        Kupa k = new Kupa(csiMock, "Kuba");

        when(csiMock.registerObserver(k, "location")).thenReturn(false);
        k.subscribe("location");
        assertFalse(k.getMeas().containsKey("location"));
    }

    @Test
    public void unsubscribeTest(){
        Csi csi = new Csi();
        Kupa k = new Kupa(csi, "Kuba");
        Kupa k1 = new Kupa(csi, "Adam");

        csi.addNewLocation("location");

        k.subscribe("location");
        k1.subscribe("location");

        k.unsubscribe("location");

        assertEquals(1, csi.getObservers().get("location").size());
        assertTrue(csi.getObservers().get("location").contains(k1));
        assertFalse(csi.getObservers().get("location").contains(k));
    }

    @Test
    public void averageValuesTest(){
        Measurements m1 = new Measurements(6,2,3);
        Measurements m2 = new Measurements(4,5,2);
        Measurements m3 = new Measurements(2,6,7);

        Csi csiMock = mock(Csi.class);
        Kupa k = new Kupa(csiMock, "Kuba");

        when(csiMock.registerObserver(k, "location")).thenReturn(true);

        k.subscribe("location");

        k.update("location", m1);
        k.update("location", m2);
        k.update("location", m3);

        HashMap<String, Optional<Double>> avg = k.averageValues("location");

        assertEquals((6+4+2)/3.0, avg.get("Temperature").get());
        assertEquals((2+5+6)/3.0, avg.get("Humidity").get());
        assertEquals((3+2+7)/3.0, avg.get("Pressure").get());
    }

    @Test
    public void averageValuesWIthNullsTest(){
        Measurements m1 = new Measurements(null,null,null);
        Measurements m2 = new Measurements(null,null,null);
        Measurements m3 = new Measurements(null,null,null);

        Csi csiMock = mock(Csi.class);
        Kupa k = new Kupa(csiMock, "Kuba");

        when(csiMock.registerObserver(k, "location")).thenReturn(true);

        k.subscribe("location");

        k.update("location", m1);
        k.update("location", m2);
        k.update("location", m3);

        HashMap<String, Optional<Double>> avg = k.averageValues("location");

        assertEquals(Optional.empty(), avg.get("Temperature"));
        assertEquals(Optional.empty(), avg.get("Humidity"));
        assertEquals(Optional.empty(), avg.get("Pressure"));
    }

    @Test
    public void minimalValuesTest(){
        Measurements m1 = new Measurements(6,1,3);
        Measurements m2 = new Measurements(4,5,2);
        Measurements m3 = new Measurements(-1,6,7);

        Csi csiMock = mock(Csi.class);
        Kupa k = new Kupa(csiMock, "Kuba");

        when(csiMock.registerObserver(k, "location")).thenReturn(true);

        k.subscribe("location");

        k.update("location", m1);
        k.update("location", m2);
        k.update("location", m3);

        HashMap<String, Optional<Integer>> min = k.minValues("location");

        assertEquals(-1, min.get("Temperature").get());
        assertEquals(1, min.get("Humidity").get());
        assertEquals(2, min.get("Pressure").get());
    }

    @Test
    public void minimalValuesWIthNullsTest(){
        Measurements m1 = new Measurements(null,null,null);
        Measurements m2 = new Measurements(null,null,null);
        Measurements m3 = new Measurements(null,null,null);

        Csi csiMock = mock(Csi.class);
        Kupa k = new Kupa(csiMock, "Kuba");

        when(csiMock.registerObserver(k, "location")).thenReturn(true);

        k.subscribe("location");

        k.update("location", m1);
        k.update("location", m2);
        k.update("location", m3);

        HashMap<String, Optional<Integer>> min = k.minValues("location");

        assertEquals(Optional.empty(), min.get("Temperature"));
        assertEquals(Optional.empty(), min.get("Humidity"));
        assertEquals(Optional.empty(), min.get("Pressure"));
    }

    @Test
    public void maxValuesTest(){
        Measurements m1 = new Measurements(6,1,3);
        Measurements m2 = new Measurements(4,5,2);
        Measurements m3 = new Measurements(-1,9,7);

        Csi csiMock = mock(Csi.class);
        Kupa k = new Kupa(csiMock, "Kuba");

        when(csiMock.registerObserver(k, "location")).thenReturn(true);

        k.subscribe("location");

        k.update("location", m1);
        k.update("location", m2);
        k.update("location", m3);

        HashMap<String, Optional<Integer>> max = k.maxValues("location");

        assertEquals(6, max.get("Temperature").get());
        assertEquals(9, max.get("Humidity").get());
        assertEquals(7, max.get("Pressure").get());
    }

    @Test
    public void maxValuesWIthNullsTest(){
        Measurements m1 = new Measurements(null,null,null);
        Measurements m2 = new Measurements(null,null,null);
        Measurements m3 = new Measurements(null,null,null);

        Csi csiMock = mock(Csi.class);
        Kupa k = new Kupa(csiMock, "Kuba");

        when(csiMock.registerObserver(k, "location")).thenReturn(true);

        k.subscribe("location");

        k.update("location", m1);
        k.update("location", m2);
        k.update("location", m3);

        HashMap<String, Optional<Integer>> max = k.minValues("location");

        assertEquals(Optional.empty(), max.get("Temperature"));
        assertEquals(Optional.empty(), max.get("Humidity"));
        assertEquals(Optional.empty(), max.get("Pressure"));
    }





}