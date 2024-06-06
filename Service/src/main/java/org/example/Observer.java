package org.example;


import java.sql.SQLException;

public interface Observer {
    void update() throws SQLException;
}