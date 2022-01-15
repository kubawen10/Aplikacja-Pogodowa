package com;

public interface Subject {
    public boolean registerObserver(Observer o, String location);
    public void removeObserver(Observer o, String location);
    public void notifyObservers(String location);
}
