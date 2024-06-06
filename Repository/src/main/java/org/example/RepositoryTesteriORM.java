package org.example;

import org.hibernate.Session;

import java.sql.SQLException;
import java.util.UUID;

public class RepositoryTesteriORM implements RepositoryTesteri{
    @Override
    public void add(Tester elem) {
        HibernateUtils.getSessionFactory().inTransaction(session -> session.persist(elem));
    }

    public void update(UUID uuid, Tester elem) {
        HibernateUtils.getSessionFactory().inTransaction(session -> session.merge(elem));

    }
    @Override
    public Iterable<Tester> findAll() throws SQLException {
        return null;
    }

    @Override
    public void delete(UUID integer) {

    }

    @Override
    public Tester findOne(UUID integer) {
        return null;
    }

    public Tester findOnebyEmail(String username) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Tester tester = session.createSelectionQuery("from Tester where username=:username", Tester.class)
                    .setParameter("username",username)
                    .getSingleResultOrNull();
            if(tester == null)
                return null;
            return tester;
        }
    }
}

