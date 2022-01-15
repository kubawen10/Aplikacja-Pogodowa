import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Csi csi = new Csi();
        csi.addNewLocation("Legnica", true, true ,true);
        csi.addNewLocation("Wroclaw", true, false ,true);
//        csi.addNewLocation("Wroclaw");
//
        Kupa k1 = new Kupa(csi, "Kuba");
        k1.subscribe("Legnica");
        k1.subscribe("Wroclaw");
//        k1.subscribe("Nie istnieje");
//        Kupa k2 = new Kupa(csi);
//        k2.subscribe("Wroclaw");
//        k2.subscribe("Legnica");
        //Measurements m = new Measurements(1, null, 2);
        //System.out.println(m.getPressure().get());

        Thread.sleep(2000);
        HashMap<String, Optional<Double>> a = k1.averageValues("Legnica");
        System.out.println(a);
        //JsonMaker.saveMeasurements(k1);



        //gdy zastopuje aplikacje
        try {
            Thread.sleep(1000);
            csi.stopServer();
            csi.waitFinish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
