package org.example;

public class Main {

    public static void main(String[] args) {
        RepositoryProgramatori programatoriRepository = new RepositoryProgramatoriORM();
        Programatori p1 = new Programatori("Sile","Sile");

        programatoriRepository.add(p1);
        //System.out.println(programatoriRepository.findOne(1));

    }
}
