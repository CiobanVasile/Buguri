package org.example;

import org.example.Entity;

import java.sql.SQLException;

public interface Repository<ID,T extends Entity<ID>> {
    void add(T elem);
    void update(ID id, T elem);

    Iterable<T> findAll() throws SQLException;
    void delete(ID id);
    T findOne(ID id);

}