package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        String filePath = "src/main/resources/people.csv";

        try {
            List<Person> people = PersonFileReader.readPeopleFromFile(filePath);

            System.out.println("People younger than 25 years old:");
            List<Person> filteredPeople = PersonFilter.filterByAge(people, 0, 25);
            printPeople(filteredPeople);

            System.out.println("\nPeople whose names don't start with 'A':");
            filteredPeople = PersonFilter.filterByName(people, "A");
            printPeople(filteredPeople);

            System.out.println("\nFirst person from Madrid:");
            Optional<Person> firstPersonFromMadrid = PersonFilter.findFirstByTown(people, "Madrid");
            firstPersonFromMadrid.ifPresent(Main::printPerson);

            System.out.println("\nFirst person from Barcelona:");
            Optional<Person> firstPersonFromBarcelona = PersonFilter.findFirstByTown(people, "Barcelona");
            firstPersonFromBarcelona.ifPresent(Main::printPerson);

        } catch (InvalidLineFormatException e) {
            System.out.println("Invalid line format: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printPeople(List<Person> people) {
        for (Person person : people) {
            printPerson(person);
        }
    }

    private static void printPerson(Person person) {
        System.out.println("Name: " + person.getName() +
                ". Town: " + (person.getTown() != null ? person.getTown() : "Unknown") +
                ". Age: " + (person.getAge() != null ? person.getAge() : "Unknown"));
    }
}
