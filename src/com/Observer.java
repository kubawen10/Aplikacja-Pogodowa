package com;

public interface Observer {
    public void update(String location, Measurements m);//, int humidity, int pressure);
    public void unsubscribe(String location);
    public void subscribe(String location);
}
