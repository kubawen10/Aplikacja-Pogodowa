public interface Observer {
    public void update(String location, int temperature);//, int humidity, int pressure);
    public void unsubscribe(String location);
    public void subscribe(String location);
}
