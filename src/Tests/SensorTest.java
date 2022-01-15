package Tests;

import com.Measurements;
import com.WeatherSensor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import static org.junit.jupiter.api.Assertions.*;

class SensorTest {
    @Test
    public void getMeasurementsTest() {
        WeatherSensor weatherSensor = new WeatherSensor(true, true, false);

    }
}