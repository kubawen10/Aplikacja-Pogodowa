public class Main {
    public static void main(String[] args) throws InterruptedException {
        Csi csi = new Csi();
        csi.addNewLocation("Legnica", true, false ,true);
//        csi.addNewLocation("Wroclaw");
//
        Kupa k1 = new Kupa(csi);
        k1.subscribe("Legnica");
//        k1.subscribe("Nie istnieje");
//        Kupa k2 = new Kupa(csi);
//        k2.subscribe("Wroclaw");
//        k2.subscribe("Legnica");
        //Measurements m = new Measurements(1, null, 2);
        //System.out.println(m.getPressure().get());

        Thread.sleep(5000);
        k1.averageValues("Legnica");

        //gdy zastopuje aplikacje
        try {
            Thread.sleep(8000);
            csi.stopServer();
            csi.waitFinish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
