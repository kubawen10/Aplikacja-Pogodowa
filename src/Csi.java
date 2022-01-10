import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Csi implements Subject{
    HashMap<String, Sensor> locations;
    HashMap<String, List<Observer>> observers;

    public Csi(){
        locations = new HashMap<>();
        observers = new HashMap<>();
    }

    public void addNewLocation(String name, boolean trackTemperature, boolean trackHumidity, boolean trackPressure){
        if(locations.containsKey(name)) return;

        locations.put(name, new Sensor());
        observers.put(name, new ArrayList<>());
    }

    @Override
    public void registerObserver(Observer o, String location) {
        if(!observers.containsKey(location)) {
            System.out.println("This location isn't yet tracked by our system.");
            return;
        }

        if((observers.get(location)).contains(o)){
            System.out.println("You are already subscribed to this location.");
            return;
        }

        observers.get(location).add(o);
        System.out.println("Successfully subscribed to " + location);
    }

    @Override
    public void removeObserver(Observer o, String location) {

    }

    @Override
    public void notifyObservers(String location) {

    }
}
