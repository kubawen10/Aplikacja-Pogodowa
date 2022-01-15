package com;

import java.util.Random;

public class WeatherSensor {
    private Integer temperature = null;
    private Integer humidity = null;
    private Integer pressure = null;

    private boolean measureTemperature;
    private boolean measureHumidity;
    private boolean measurePressure;

    public WeatherSensor(boolean temperature, boolean humidity, boolean pressure) {
        measureTemperature = temperature;
        measureHumidity = humidity;
        measurePressure = pressure;
    }

    public boolean isMeasureTemperature() {
        return measureTemperature;
    }

    public boolean isMeasureHumidity() {
        return measureHumidity;
    }

    public boolean isMeasurePressure() {
        return measurePressure;
    }

    public void setMeasureTemperature(boolean measureTemperature) {
        this.measureTemperature = measureTemperature;
    }

    public void setMeasureHumidity(boolean measureHumidity) {
        this.measureHumidity = measureHumidity;
    }

    public void setMeasurePressure(boolean measurePressure) {
        this.measurePressure = measurePressure;
    }

    public WeatherSensor() {
        measureTemperature = true;
        measureHumidity = true;
        measurePressure = true;
    }

    public void measure() {
        Random random = new Random();
        if (measureTemperature) temperature = random.nextInt(101) - 50; //generate random temperature value between -50 and 50
        if (measureHumidity)    humidity = random.nextInt(101);         //generate random humidity value between 0 and 100
        if (measurePressure)    pressure = random.nextInt(101) + 950;   //generate random pressure value between 950 and 1050
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

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Measurements getMeasurements(){
        return new Measurements(temperature, humidity, pressure);
    }
}
