public class Main {
    public static void main(String[] args) {
        Csi csi = new Csi();
        csi.addNewLocation("Legnica");
        csi.addNewLocation("Wroclaw");

        Kupa k1 = new Kupa(csi);
        k1.subscribe("Legnica");
        k1.subscribe("Nie istnieje");
        Kupa k2 = new Kupa(csi);
        k2.subscribe("Wroclaw");
        k2.subscribe("Legnica");
        csi.newMeasurements();
    }
}
