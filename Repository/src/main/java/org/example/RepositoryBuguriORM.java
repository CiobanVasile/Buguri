package org.example;

import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.UUID;

public class RepositoryBuguriORM implements RepositoryBuguri{
    @Override
    public void add(Buguri elem) {

        HibernateUtils.getSessionFactory().inTransaction(session -> session.merge(elem));
    }

    public void update(UUID uuid, Buguri elem) {
        HibernateUtils.getSessionFactory().inTransaction(session -> session.merge(elem));

    }
    @Override
    public Iterable<Buguri> findAll() throws SQLException {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            List<Buguri> buguriList = session.createSelectionQuery("from Buguri",Buguri.class).getResultList();
            return buguriList;
        }
    }



    @Override
    public void delete(UUID integer) {

    }

    @Override
    public Buguri findOne(UUID integer) {
        return null;
    }

}

