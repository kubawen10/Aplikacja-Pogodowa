import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Kupa implements Observer {
    HashMap<String, List<Integer>> meas;
    Subject subject;

    public Kupa(Subject subject) {
        meas = new HashMap<>();
        this.subject = subject;
    }

    @Override
    public void update(String location, int temperature){//, int humidity, int pressure) {
        meas.get(location).add(temperature);
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

    public void displaySubscriptions(){
        for (String location: meas.keySet()){
            System.out.println(location);
        }
    }
}
