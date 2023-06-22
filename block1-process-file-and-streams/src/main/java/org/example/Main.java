package org.example;

import Person.Person;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        try {
            PersonFileReader();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void leerArchivo()
            throws IOException {
        Path path = Paths.get("src\main\resources\people.csv");  //obtener el path/ruta donde está el archivo
        BufferedReader reader = Files.newBufferedReader(path);     //accede a dicho archivo
        String line = reader.readLine();  //lee la línea


        //Siempre que tengamos un throw exception, tenemos que atrapar dicha excepción donde llamo al método con un try catch. En este caso es en el main.


        while ((line = reader.readLine()) != null) {
            System.out.println(line);

            String[] datosPersona = line.split(":");
            line = reader.readLine();
            addPerson(line);

        }

    }

    //aquí es donde va la información que hemos sacado del string anterior
    public static Person addPerson(String infoPerson) {
        Person person = new Person();
        String[] dataPerson = infoPerson.split(":");
        //creamos un objeto person de la clase Person y ese string metemos la información del documento

        person.setName(dataPerson[0]);
        person.setTown(dataPerson[1]);
        person.setAge ((dataPerson.length < 3) ? 0 : Integer.parseInt(dataPerson[2]));


        ArrayList<Person> listPerson = new ArrayList<>();
        listPerson.add(person);
        checkCode(infoPerson);


        return person;
    }

    private static void checkCode (String check){
        long count = check.chars().filter(character -> character == ':').count();
        if(count == 2){ //Te enseña el primer erroneo;
            String [] datos = check.split(":");
            if (datos[0].isBlank() || datos[0].isEmpty()){ //si el primer campo está en blanco o vacío:
                System.out.println("Falta el nombre de uno de los campos");
            }
        }
    }

    private static void checkAge (ArrayList<Person> AgeList){
        List<Person> listPerson = AgeList.stream()
                .filter(ed -> !ed.getName().startsWith("a")).toList();
        List<Person> listAge = AgeList.stream()
                .filter(ed -> ed.getAge() < 25).toList();
        Optional<Person> listMadrid = AgeList.stream()
                .filter(ed -> !ed.getTown().equals("Madrid")).findFirst();
        Optional<Person> listBcn = AgeList.stream()
                .filter(ed -> !ed.getTown().equals("Barcelona")).findFirst();
        System.out.println(listPerson);
        System.out.println(listAge);
        System.out.println(listMadrid);
        System.out.println(listBcn);
    }


}
