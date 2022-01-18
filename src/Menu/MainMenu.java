package Menu;

import com.Csi;
import com.JsonMaker;
import com.Kupa;
import com.google.gson.internal.bind.util.ISO8601Utils;

import java.util.*;

public class MainMenu {
    MyScanner scanner;

    Csi csi;
    List<Kupa> kupas;

    public MainMenu(Csi csi) {
        this.csi = csi;
        scanner = new MyScanner();
        kupas = new ArrayList<>();
    }

    public void run() {
        boolean run = true;
        int choice;

        while (run) {
            Messages.mainMessage();

            choice = scanner.intChoice();

            switch (choice) {
                case 0:
                    //stop application
                    run = false;
                    try {
                        Thread.sleep(1000);
                        csi.stopServer();
                        csi.waitFinish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;

                case 1:
                    //add new kupa
                    System.out.println("Name new Kupa: ");
                    String name = scanner.stringChoice();
                    kupas.add(new Kupa(csi, name));
                    break;

                case 2:
                    //choose kupa
                    Messages.printKupas(kupas);
                    if (kupas.size() == 0) {
                        System.out.println("No kupa to choose from");
                        break;
                    }

                    int number = scanner.intChoice();
                    if (number <= 0 || number > kupas.size()) {
                        System.out.println("Wrong number");
                        break;
                    }

                    chosenKupaMenu(kupas.get(number-1));
                    break;

                case 3:
                    //create new sensor location
                    System.out.println("Location: ");
                    String location = scanner.stringChoice();

                    if(csi.availableLocationsAndSensors().containsKey(location)){
                        System.out.println("This location already exists");
                        break;
                    }

                    Messages.newSensorMessage();
                    String params = scanner.stringChoice().toLowerCase();

                    boolean t = params.contains("t");
                    boolean h = params.contains("h");
                    boolean p = params.contains("p");

                    if(!t && !h && !p){
                        System.out.println("Wrong parameters");
                        break;
                    }

                    csi.addNewLocation(location, t, h, p);
                    break;

                case 4:
                    Messages.printLocations(csi.availableLocationsAndSensors());
                    break;

                default:
                    Messages.wrongNumber();
            }
        }
    }

    private void chosenKupaMenu(Kupa k){
        Messages.kupaMenu();

        int choice = scanner.intChoice();

        switch(choice){
            case 1:
                //subscribe chosen kupa to location
                k.subscribe(chooseLocationFromCsi());
                break;

            case 2:
                //unsubscribe chosen kupa from location
                k.unsubscribe(chooseLocationFromSubscribed(k.subscriptions()));
                break;

            case 3:
                Messages.printSubscriptions(k.subscriptions());
            case 4:
                //save measurements of chosen kupa to json file
                JsonMaker.saveMeasurements(k);
                break;

            case 5:
                //calculation menu
                if(k.subscriptions().size()==0){
                    System.out.println("No data to process");
                    break;
                }
                calculationMenu(k);
                break;

            default:
                Messages.wrongNumber();
        }
    }

    private void calculationMenu(Kupa k){
        String location = chooseLocationFromSubscribed(k.subscriptions());

        if(!k.getMeas().containsKey(location)){
            System.out.println("Wrong location");
            return;
        }
        Messages.chooseCalculation();
        int choice = scanner.intChoice();



        switch(choice){
            case 1:
                HashMap<String, Optional<Double>> avg = k.averageValues(location);
                for(Map.Entry<String, Optional<Double>> entry: avg.entrySet()){
                    System.out.println(entry.getKey() + ": " +
                            (entry.getValue().isPresent() ? String.format("%.2f", entry.getValue().get()) : "N/A"));
                }
                break;

            case 2:
                printIntegerMap(k.minValues(location));
                break;

            case 3:
                printIntegerMap(k.maxValues(location));
                break;

            default:
                Messages.wrongNumber();
        }
    }

    private void printIntegerMap(HashMap<String, Optional<Integer>> map){
        for(Map.Entry<String, Optional<Integer>> entry: map.entrySet()){
            System.out.println(entry.getKey() + ": " +
                    (entry.getValue().isPresent() ? String.format("%d", entry.getValue().get()) : "N/A"));
        }

    }

    private String chooseLocationFromCsi(){
        Messages.printLocations(csi.availableLocationsAndSensors());
        System.out.println("Location: ");
        String location = scanner.stringChoice();
        return location;
    }

    private String chooseLocationFromSubscribed(List<String> s){
        Messages.printSubscriptions(s);
        System.out.println("Location: ");
        String location = scanner.stringChoice();
        return location;
    }

}
