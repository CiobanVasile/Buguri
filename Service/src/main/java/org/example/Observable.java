package org.example;


import java.sql.SQLException;

public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers() throws SQLException;
}