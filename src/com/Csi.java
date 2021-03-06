package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Csi implements Subject {
    private HashMap<String, WeatherSensor> locations;
    private HashMap<String, List<Observer>> observers;

    private Thread t1;
    boolean shouldContinue = true;
    private Object synchronizer = new Object();

    public Csi() {
        locations = new HashMap<>();
        observers = new HashMap<>();

        Runnable r = this::runInternal;
        t1 = new Thread(r);
        t1.start();
    }

    public HashMap<String, String> availableLocationsAndSensors(){
        HashMap<String, String> data = new HashMap<>();
        for(Map.Entry<String, WeatherSensor> entry: locations.entrySet()){
            String sensors ="";
            if(entry.getValue().isMeasureTemperature()) sensors +="T";
            if(entry.getValue().isMeasureHumidity()) sensors +="H";
            if(entry.getValue().isMeasurePressure()) sensors +="P";
            data.put(entry.getKey(), sensors);
        }
        return data;
    }

    public void runInternal() {
        while (shouldContinue) {
            //synchronized bc of possible list changes
            synchronized(synchronizer){
                for (Map.Entry<String, WeatherSensor> entry : locations.entrySet()) {
                    entry.getValue().measure();
                    notifyObservers(entry.getKey());
                }
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopServer(){
        shouldContinue = false;
    }

    public void waitFinish() {
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addNewLocation(String name) {
        if (locations.containsKey(name)) return;

        synchronized (synchronizer) {
            locations.put(name, new WeatherSensor());
            observers.put(name, new ArrayList<>());
        }
    }

    public void addNewLocation(String name, boolean trackTemperature, boolean trackHumidity, boolean trackPressure) {
        if (locations.containsKey(name)) return;

        synchronized (synchronizer) {
            locations.put(name, new WeatherSensor(trackTemperature, trackHumidity, trackPressure));
            observers.put(name, new ArrayList<>());
        }
    }

    @Override
    public boolean registerObserver(Observer o, String location) {
        if (!observers.containsKey(location)) {
            System.out.println("This location isn't yet tracked by our system.");
            return false;
        }

        if ((observers.get(location)).contains(o)) {
            System.out.println("You are already subscribed to this location.");
            return false;
        }

        //to powinno byc zssynchronizowane
        synchronized (synchronizer) {
            observers.get(location).add(o);
        }

        System.out.println("Successfully subscribed to " + location);
        return true;
    }

    @Override
    public void removeObserver(Observer o, String location) {
        if (!observers.containsKey(location)) {
            System.out.println("This location isn't yet tracked by our system.");
            return;
        }
        if (!((observers.get(location)).contains(o))) {
            System.out.println("You are not subscribed to this location.");
            return;
        }

        //to powinno byc zsynchronizowane
        synchronized (synchronizer) {
            observers.get(location).remove(o);
        }

        System.out.println("Successfully removed from " + location);
    }

    @Override
    public void notifyObservers(String location) {
        List<Observer> o = observers.get(location);
        Measurements m = locations.get(location).getMeasurements();

        synchronized (synchronizer){
            for (Observer observer : o) {
                observer.update(location, m);
            }
        }
    }

    public HashMap<String, List<Observer>> getObservers() {
        return observers;
    }

    public HashMap<String, WeatherSensor> getLocations() {
        return locations;
    }
}
