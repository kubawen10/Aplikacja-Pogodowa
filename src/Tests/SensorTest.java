package Tests;

import com.Measurements;
import com.WeatherSensor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import static org.junit.jupiter.api.Assertions.*;

class SensorTest {
    @Test
    public void getMeasurementsTest() {
        WeatherSensor weatherSensor = new WeatherSensor(true, true, false);

        weatherSensor.measure();
        Measurements m = weatherSensor.getMeasurements();

        assertThrows(NoSuchElementException.class, ()->{m.getPressure().get();});
        assertEquals(Optional.empty(), m.getPressure());
        assertTrue(m.getTemperature().isPresent());
        assertTrue(m.getHumidity().isPresent());
        assertFalse(m.getPressure().isPresent());
    }
}