public interface Subject {
    public void registerObserver(Observer o, String location);
    public void removeObserver(Observer o, String location);
    public void notifyObservers(String location);
}
