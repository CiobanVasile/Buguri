package org.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Service implements Observable{
    private List<Observer> observers = new ArrayList<>();
    private final RepositoryProgramatoriORM programatoriRepo;

    private final RepositoryBuguriORM buguriRepo;
    private final RepositoryTesteriORM testeriRepo;

    private final RepositoryIstoricBuguriORM istoricBuguriRepo;

    public Service(RepositoryProgramatoriORM programatoriRepo, RepositoryBuguriORM buguriRepo, RepositoryTesteriORM testeriRepo, RepositoryIstoricBuguriORM istoricBuguriRepo) {
        this.programatoriRepo = programatoriRepo;
        this.buguriRepo = buguriRepo;
        this.testeriRepo = testeriRepo;
        this.istoricBuguriRepo = istoricBuguriRepo;
    }

    public Iterable<Buguri> sortBuguriAfterDate() {
        return buguriRepo.sortAfterDate();
    }
    // Metode pentru Programatori
    public void addProgramator(Programatori programator) {
        programatoriRepo.add(programator);
    }

    public Iterable<Buguri> getBuguriByDenumire(String denumire) {
        return buguriRepo.findByDenumire(denumire);
    }
    public void updateProgramator(UUID id, Programatori programator) {
        programatoriRepo.update(id, programator);
    }

    public Iterable<Programatori> getAllProgramatori() throws SQLException {
        return programatoriRepo.findAll();
    }

    public void deleteProgramator(UUID id) {
        programatoriRepo.delete(id);
    }

    public Programatori getProgramatorById(UUID id) {
        return programatoriRepo.findOne(id);
    }

    // Metode pentru Buguri
    public void addBug(Buguri bug) throws SQLException {
        buguriRepo.add(bug); notifyObservers();
    }
    public void addIstoricBug(Istoric_Buguri istoricBuguri) throws SQLException {
        istoricBuguriRepo.add(istoricBuguri); notifyObservers();
    }

    public void updateBug(UUID id,Buguri bug) throws SQLException {
        buguriRepo.update(id, bug);
        notifyObservers();
    }
    public void updateIstoricBug(UUID id,Istoric_Buguri bug) throws SQLException {
        istoricBuguriRepo.update(id, bug);
        notifyObservers();
    }

    public Iterable<Buguri> getAllBuguri() throws SQLException {
        return buguriRepo.findAll();
    }
    public Iterable<Istoric_Buguri> getAllIstoricBuguri() throws SQLException {
        return istoricBuguriRepo.findAll();
    }

    public void deleteBug(UUID id) throws SQLException {
        buguriRepo.delete(id);
        notifyObservers();
    }

    public Buguri getBugById(UUID id) {
        return buguriRepo.findOne(id);
    }

    // Metode pentru Testeri
    public void addTester(Tester tester) {
        testeriRepo.add(tester);
    }

    public void updateTester(UUID id, Tester tester) {
        testeriRepo.update(id, tester);
    }

    public Iterable<Tester> getAllTesteri() throws SQLException {
        return testeriRepo.findAll();
    }

    public void deleteTester(UUID id) {
        testeriRepo.delete(id);
    }

    public Tester getTesterById(UUID id) {
        return testeriRepo.findOne(id);
    }

    public Tester getTesterByEmail(String email) {
        return testeriRepo.findOnebyEmail(email);
    }

    public Programatori getProgramatorByEmail(String email) {
        return programatoriRepo.findOnebyEmail(email);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() throws SQLException {
        for (Observer o : observers)
            o.update();
    }

    private long lastBugTimestamp = 0; // Stores timestamp of last retrieved bug
}
