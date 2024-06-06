package org.example;

import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class RepositoryIstoricBuguriORM implements RepositoryIstoricBuguri{
    @Override
    public void add(Istoric_Buguri elem) {
        HibernateUtils.getSessionFactory().inTransaction(session -> session.merge(elem));
    }

    public void update(UUID uuid, Istoric_Buguri elem) {
        HibernateUtils.getSessionFactory().inTransaction(session -> session.merge(elem));

    }
    @Override
    public Iterable<Istoric_Buguri> findAll() throws SQLException {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            List<Istoric_Buguri> buguriList = session.createSelectionQuery("from Istoric_Buguri ",Istoric_Buguri.class).getResultList();
            return buguriList;
        }
    }


    @Override
    public void delete(UUID integer) {

    }

    @Override
    public Istoric_Buguri findOne(UUID integer) {
        return null;
    }

}

