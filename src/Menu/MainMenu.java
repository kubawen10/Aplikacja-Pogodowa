package Menu;

import com.Csi;
import com.JsonMaker;
import com.Kupa;

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
                    System.out.println("Name new Kupa: ");
                    String name = scanner.stringChoice();
                    kupas.add(new Kupa(csi, name));
                    break;

                case 2:
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
                    System.out.println("Location: ");
                    String location = scanner.stringChoice();

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
                k.subscribe(chooseLocation());
                break;

            case 2:
                k.unsubscribe(chooseLocation());
                break;

            case 3:
                JsonMaker.saveMeasurements(k);
                break;

            case 4:
                System.out.println(" to do zrobienia");
                break;

            default:
                Messages.wrongNumber();
        }
    }

    private String chooseLocation(){
        Messages.printLocations(csi.availableLocationsAndSensors());
        System.out.println("Location: ");
        String location = scanner.stringChoice();
        return location;
    }


}
