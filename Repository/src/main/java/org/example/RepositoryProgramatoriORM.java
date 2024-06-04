package org.example;

import org.example.Programatori;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.UUID;

public class RepositoryProgramatoriORM implements RepositoryProgramatori{

    @Override
    public void add(Programatori elem) {
        HibernateUtils.getSessionFactory().inTransaction(session -> session.persist(elem));
    }


    @Override
    public void update(UUID integer, Programatori elem) {
        HibernateUtils.getSessionFactory().inTransaction(session -> session.merge(elem));

    }

    @Override
    public Iterable<Programatori> findAll() throws SQLException {
        return null;
    }

    @Override
    public void delete(UUID integer) {

    }

    @Override
    public Programatori findOne(UUID integer) {
        return null;
    }

    public Programatori findOnebyEmail(String username) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Programatori programatori = session.createSelectionQuery("from Programatori where username=:username",Programatori.class)
                    .setParameter("username",username)
                    .getSingleResultOrNull();
            if(programatori == null)
                return null;
            return programatori;
        }
    }
}
