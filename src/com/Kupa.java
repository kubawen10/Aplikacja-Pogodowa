package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


public class Kupa implements Observer {
    private HashMap<String, List<Measurements>> meas;
    private final Subject subject;
    private final String name;

    public Kupa(Subject subject, String name) {
        meas = new HashMap<>();
        this.subject = subject;
        this.name=name;
    }

    @Override
    public String toString(){
        return "Kupa: " + name;
    }

    @Override
    public void update(String location, Measurements m) {
        meas.get(location).add(m);
    }

    @Override
    public void unsubscribe(String location) {
        subject.removeObserver(this, location);
    }

    @Override
    public void subscribe(String location) {
        boolean successful = subject.registerObserver(this, location);

        if (!successful) return;
        meas.put(location, new ArrayList<>());
    }

    public List<String> subscriptions(){
        return new ArrayList<>(meas.keySet());
    }

    public double calculateAvg(List<Integer> list){
        double sum = 0;
        for(Integer i: list){
            sum+=i;
        }
        return sum/list.size();
    }

    public int calculateMin(List<Integer> list){
        int min = 100000;
        for(Integer i: list){
            if (i<min) min = i;
        }
        return min;
    }

    public int calculateMax(List<Integer> list){
        int max = -100000;
        for(Integer i: list){
            if (i>max) max = i;
        }
        return max;
    }

    public void createValueLists(List<Integer> t, List<Integer> h, List<Integer> p, String location){
        List<Measurements> m = meas.get(location);

        for(Measurements measurements: m) {
            if (measurements.getTemperature().isPresent()) {
                t.add(measurements.getTemperature().get());
            }
            if (measurements.getHumidity().isPresent()) {
                h.add(measurements.getHumidity().get());
            }
            if (measurements.getPressure().isPresent()) {
                p.add(measurements.getPressure().get());
            }
        }

    }

    public HashMap<String, Optional<Double>> averageValues(String location) {
        double avgTemperature;
        double avgHumidity;
        double avgPressure;
        HashMap<String, Optional<Double>> data = new HashMap<>();

        List<Integer> temperatureValues = new ArrayList<>();
        List<Integer> humidityValues = new ArrayList<>();
        List<Integer> pressureValues = new ArrayList<>();

        createValueLists(temperatureValues, humidityValues, pressureValues, location);

        if(temperatureValues.size()>0) {
            avgTemperature = calculateAvg(temperatureValues);
            data.put("Temperature", Optional.of(avgTemperature));
        }else data.put("Temperature", Optional.empty());

        if(humidityValues.size()>0) {
            avgHumidity = calculateAvg(humidityValues);
            data.put("Humidity", Optional.of(avgHumidity));
        }else data.put("Humidity", Optional.empty());

        if(pressureValues.size()>0) {
            avgPressure = calculateAvg(temperatureValues);
            data.put("Pressure", Optional.of(avgPressure));
        }else data.put("Pressure", Optional.empty());

        return data;
    }

    public HashMap<String, Optional<Integer>> minValues(String location) {
        int minTemperature;
        int minHumidity;
        int minPressure;
        HashMap<String, Optional<Integer>> data = new HashMap<>();

        List<Integer> temperatureValues = new ArrayList<>();
        List<Integer> humidityValues = new ArrayList<>();
        List<Integer> pressureValues = new ArrayList<>();

        createValueLists(temperatureValues, humidityValues, pressureValues, location);

        if(temperatureValues.size()>0) {
            minTemperature = calculateMin(temperatureValues);
            data.put("Temperature", Optional.of(minTemperature));
        }else data.put("Temperature", Optional.empty());

        if(humidityValues.size()>0) {
            minHumidity = calculateMin(humidityValues);
            data.put("Humidity", Optional.of(minHumidity));
        }else data.put("Humidity", Optional.empty());

        if(pressureValues.size()>0) {
            minPressure = calculateMin(temperatureValues);
            data.put("Pressure", Optional.of(minPressure));
        }else data.put("Pressure", Optional.empty());

        return data;
    }

    public HashMap<String, Optional<Integer>> maxValues(String location) {
        int maxTemperature;
        int maxHumidity;
        int maxPressure;
        HashMap<String, Optional<Integer>> data = new HashMap<>();

        List<Integer> temperatureValues = new ArrayList<>();
        List<Integer> humidityValues = new ArrayList<>();
        List<Integer> pressureValues = new ArrayList<>();

        createValueLists(temperatureValues, humidityValues, pressureValues, location);

        if(temperatureValues.size()>0) {
            maxTemperature = calculateMax(temperatureValues);
            data.put("Temperature", Optional.of(maxTemperature));
        }else data.put("Temperature", Optional.empty());

        if(humidityValues.size()>0) {
            maxHumidity = calculateMax(humidityValues);
            data.put("Humidity", Optional.of(maxHumidity));
        }else data.put("Humidity", Optional.empty());

        if(pressureValues.size()>0) {
            maxPressure = calculateMax(temperatureValues);
            data.put("Pressure", Optional.of(maxPressure));
        }else data.put("Pressure", Optional.empty());

        return data;
    }

    public String getName(){
        return name;
    }

    public HashMap<String, List<Measurements>> getMeas() {
        return meas;
    }
}
