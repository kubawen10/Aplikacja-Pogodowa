package Menu;

import com.Kupa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Messages {
    public static void mainMessage(){
        System.out.println("\n\n\n\n\n\n\n\n");
        System.out.println("1. Add Kupa");
        System.out.println("2. Choose Kupa");
        System.out.println("3. Add new sensor location");
        System.out.println("4. Print all sensor locations");
        System.out.println("0. Quit");
    }

    public static void printKupas(List<Kupa> kupas){
        for(int i = 0 ; i<kupas.size();i++){
            System.out.println((i+1) + ". " + kupas.get(i));
        }
    }

    public static void printLocations(HashMap<String, String> locations){
        for(Map.Entry<String, String> data: locations.entrySet()){
            System.out.println(data.getKey() + " " + data.getValue());
        }
    }

    public static void newSensorMessage(){
        System.out.println("What parameters do you want to measure?");
        System.out.println("T - for Temperature");
        System.out.println("H - for Humidity");
        System.out.println("P - for Pressure");
        System.out.println("eg TH for temperature and humidity");
    }

    public static void kupaMenu(){
        System.out.println("1. Subscribe to new location");
        System.out.println("2. Unsubscribe location");
        System.out.println("3. Save measurements");
        System.out.println("4. Process measurements");
    }

    public static void wrongNumber(){
        System.out.println("Wrong number");
    }
}
