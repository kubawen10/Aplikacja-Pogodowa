import java.util.Random;

public class Sensor {
    private int temperature;
    private int humidity;
    private int pressure;

    public void measure() {
        Random random = new Random();
        temperature = random.nextInt(101) - 50; //generate random temperature value between -50 and 50
        humidity = random.nextInt(101);         //generate random humidity value between 0 and 100
        pressure = random.nextInt(101) + 950;   //generate random pressure value between 950 and 1050
    }

    public int getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getPressure() {
        return pressure;
    }
}
