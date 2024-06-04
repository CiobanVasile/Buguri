package org.example;

import java.sql.SQLException;
import java.util.UUID;

public class Service {
    private final RepositoryProgramatoriORM programatoriRepo;
    private final RepositoryBuguriORM buguriRepo;
    private final RepositoryTesteriORM testeriRepo;

    public Service(RepositoryProgramatoriORM programatoriRepo, RepositoryBuguriORM buguriRepo, RepositoryTesteriORM testeriRepo) {
        this.programatoriRepo = programatoriRepo;
        this.buguriRepo = buguriRepo;
        this.testeriRepo = testeriRepo;
    }

    // Metode pentru Programatori
    public void addProgramator(Programatori programator) {
        programatoriRepo.add(programator);
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
    public void addBug(Buguri bug) {
        buguriRepo.add(bug);
    }

    public void updateBug(UUID id, Buguri bug) {
        buguriRepo.update(id, bug);
    }

    public Iterable<Buguri> getAllBuguri() throws SQLException {
        return buguriRepo.findAll();
    }

    public void deleteBug(UUID id) {
        buguriRepo.delete(id);
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
}
