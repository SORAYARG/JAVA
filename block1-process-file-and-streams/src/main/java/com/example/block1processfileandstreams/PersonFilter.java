package com.example.block1processfileandstreams;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonFilter {
    public static Optional<Person> findFirstByTown(List<Person> people, String town) {
        return people.stream()
                .filter(person -> person.getTown() != null && person.getTown().equalsIgnoreCase(town))
                .findFirst();
    }

    public static List<Person> filterByAge(List<Person> people, Integer minAge, Integer maxAge) {
        return people.stream()
                .filter(person -> person.getAge() != null && person.getAge() >= minAge && person.getAge() <= maxAge)
                .collect(Collectors.toList());
    }

    public static List<Person> filterByName(List<Person> people, String startingLetter) {
        return people.stream()
                .filter(person -> person.getName() != null && !person.getName().startsWith(startingLetter))
                .collect(Collectors.toList());
    }
}
