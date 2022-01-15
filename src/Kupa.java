import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Kupa implements Observer {
    HashMap<String, List<Measurements>> meas;
    Subject subject;

    public Kupa(Subject subject) {
        meas = new HashMap<>();
        this.subject = subject;
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

    public void displaySubscriptions() {
        for (String location : meas.keySet()) {
            System.out.println(location);
        }
    }

    public void averageValues(String location) {
        List<Measurements> m = meas.get(location);
        int mSize = m.size();
        double avg;

        if (m.get(0).getTemperature().isPresent()) {
            avg = 0;
            for (int i = 0; i < mSize; i++) {
                avg += m.get(i).getTemperature().get();
            }
            System.out.println("Average temperature: " + String.format("%.2f", avg / mSize));
        } else System.out.println("Average temperature: N/A");

        if (m.get(0).getHumidity().isPresent()) {
            avg = 0;
            for (int i = 0; i < mSize; i++) {
                avg += m.get(i).getHumidity().get();
            }
            System.out.println("Average humidity: " + String.format("%.2f", avg / mSize));
        } else System.out.println("Average humidity: N/A");

        if (m.get(0).getPressure().isPresent()) {
            avg = 0;
            for (int i = 0; i < mSize; i++) {
                avg += m.get(i).getPressure().get();
            }
            System.out.println("Average pressure: " + String.format("%.2f", avg / mSize));
        } else System.out.println("Average pressure: N/A");
    }

    public void minValues(String location) {
        List<Measurements> m = meas.get(location);
        int mSize = m.size();
        int min;

        if (m.get(0).getTemperature().isPresent()) {
            min = 100;
            for (int i = 0; i < mSize; i++) {
                if (m.get(i).getTemperature().get() < min) min = m.get(i).getTemperature().get();
            }
            System.out.println("Minimum temperature: " + min);
        } else System.out.println("Minimum temperature: N/A");

        if (m.get(0).getHumidity().isPresent()) {
            min = 101;
            for (int i = 0; i < mSize; i++) {
                if (m.get(i).getHumidity().get() < min) min = m.get(i).getHumidity().get();
            }
            System.out.println("Minimum humidity: " + min);
        } else System.out.println("Minimum humidity: N/A");

        if (m.get(0).getPressure().isPresent()) {
            min = 1100;
            for (int i = 0; i < mSize; i++) {
                if (m.get(i).getPressure().get() < min) min = m.get(i).getPressure().get();
            }
            System.out.println("Minimum pressure: " + min);
        } else System.out.println("Minimum pressure: N/A");
    }

    public void maxValues(String location) {
        List<Measurements> m = meas.get(location);
        int mSize = m.size();
        int max;

        if (m.get(0).getTemperature().isPresent()) {
            max = -100;
            for (int i = 0; i < mSize; i++) {
                if (m.get(i).getTemperature().get() > max) max = m.get(i).getTemperature().get();
            }
            System.out.println("Maximum temperature: " + max);
        } else System.out.println("Maximum temperature: N/A");

        if (m.get(0).getHumidity().isPresent()) {
            max = -1;
            for (int i = 0; i < mSize; i++) {
                if (m.get(i).getHumidity().get() > max) max = m.get(i).getHumidity().get();
            }
            System.out.println("Maximum humidity: " + max);
        } else System.out.println("Maximum humidity: N/A");

        if (m.get(0).getPressure().isPresent()) {
            max = 900;
            for (int i = 0; i < mSize; i++) {
                if (m.get(i).getPressure().get() > max) max = m.get(i).getPressure().get();
            }
            System.out.println("Maximum pressure: " + max);
        } else System.out.println("Maximum pressure: N/A");
    }


}
