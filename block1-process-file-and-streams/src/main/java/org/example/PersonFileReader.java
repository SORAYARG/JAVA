package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PersonFileReader {
    public static List<Person> readPeopleFromFile(String filePath) throws IOException, InvalidLineFormatException {
        List<Person> people = new ArrayList<>();

        Path path = Paths.get(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(path.toFile()));

        String line;
        int lineNumber = 0;
        while ((line = reader.readLine()) != null) {
            try {
                Person person = parsePersonFromLine(line);
                people.add(person);
            } catch (InvalidLineFormatException e) {
                throw new InvalidLineFormatException("Error in line " + lineNumber + ": " + e.getMessage());
            }
            lineNumber++;
        }

        reader.close();

        return people;
    }

    private static Person parsePersonFromLine(String line) throws InvalidLineFormatException {
        String[] fields = line.split(":");

        if (fields.length < 1 || fields.length > 3) {
            throw new InvalidLineFormatException("Invalid number of fields");
        }

        String name = fields[0].trim();
        if (name.isEmpty()) {
            throw new InvalidLineFormatException("Name field is empty");
        }

        String town = null;
        Integer age = null;

        if (fields.length >= 2 && !fields[1].trim().isEmpty()) {
            town = fields[1].trim();
        }

        if (fields.length == 3 && !fields[2].trim().isEmpty()) {
            try {
                age = Integer.parseInt(fields[2].trim());
            } catch (NumberFormatException e) {
                throw new InvalidLineFormatException("Invalid age value: " + fields[2]);
            }
        }

        return new Person(name, town, age);
    }
}
