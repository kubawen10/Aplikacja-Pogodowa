import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Csi implements Subject{
    private HashMap<String, Sensor> locations;
    private HashMap<String, List<Observer>> observers;

    public Csi(){
        locations = new HashMap<>();
        observers = new HashMap<>();
    }

    public void addNewLocation(String name){//, boolean trackTemperature, boolean trackHumidity, boolean trackPressure){
        if(locations.containsKey(name)) return;

        locations.put(name, new Sensor());
        observers.put(name, new ArrayList<>());
    }

    public void newMeasurements(){
        for(Map.Entry<String, Sensor> entry: locations.entrySet()){
            entry.getValue().measure();
            notifyObservers(entry.getKey());
        }
    }

    @Override
    public boolean registerObserver(Observer o, String location) {
        if(!observers.containsKey(location)) {
            System.out.println("This location isn't yet tracked by our system.");
            return false;
        }

        if((observers.get(location)).contains(o)){
            System.out.println("You are already subscribed to this location.");
            return false;
        }

        observers.get(location).add(o);
        System.out.println("Successfully subscribed to " + location);
        return true;
    }

    @Override
    public void removeObserver(Observer o, String location) {
        if(!observers.containsKey(location)) {
            System.out.println("This location isn't yet tracked by our system.");
            return;
        }
        if(!((observers.get(location)).contains(o))){
            System.out.println("You are not subscribed to this location.");
            return;
        }
        observers.get(location).remove(o);
        System.out.println("Successfully removed from " + location);
    }

    @Override
    public void notifyObservers(String location) {
        List<Observer> o = observers.get(location);
        int t = locations.get(location).getTemperature();

        for(Observer observer: o){
            observer.update(location, t);
        }

    }
}
