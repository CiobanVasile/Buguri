package org.example;

import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class RepositoryBuguriORM implements RepositoryBuguri{
    @Override
    public void add(Buguri elem) {

        HibernateUtils.getSessionFactory().inTransaction(session -> session.merge(elem));
    }

    public Iterable<Buguri> sortAfterDate(){
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            List<Buguri> buguriList = session.createSelectionQuery("from Buguri order by data desc",Buguri.class).getResultList();
            return buguriList;
        }
    }

    public Iterable<Buguri> findByDenumire(String denumire) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            List<Buguri> buguriList = session.createSelectionQuery("from Buguri where denumire = :denumire",Buguri.class)
                    .setParameter("denumire", denumire)
                    .getResultList();
            return buguriList;
        }
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
        HibernateUtils.getSessionFactory().inTransaction(session -> {
            Buguri buguri = session.find(Buguri.class, integer);
            session.delete(buguri);
        });

    }

    @Override
    public Buguri findOne(UUID integer) {
        return null;
    }

    public String getLatestBugTimestamp() {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            List<Buguri> buguriList = session.createSelectionQuery("from Buguri order by data desc",Buguri.class).getResultList();
            if(buguriList.size() == 0){
                return null;
            }
            return buguriList.get(0).getData();
        }
    }
}

