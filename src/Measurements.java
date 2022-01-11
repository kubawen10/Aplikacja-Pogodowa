import java.util.Optional;

public class Measurements {
    private final Integer temperature;
    private final Integer humidity;
    private final Integer pressure;

    public Measurements(Integer temperature, Integer humidity, Integer pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public Optional<Integer> getTemperature() {
        return Optional.ofNullable(temperature);
    }

    public Optional<Integer> getHumidity() {
        return Optional.ofNullable(humidity);
    }

    public Optional<Integer> getPressure() {
        return Optional.ofNullable(pressure);
    }
}
