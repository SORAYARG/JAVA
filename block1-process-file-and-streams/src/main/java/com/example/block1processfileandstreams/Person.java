package com.example.block1processfileandstreams;

public class Person {

    private String name;
    private String town;
    private Integer age;

    public Person(String name, String town, Integer age) {
        this.name = name;
        this.town = town;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Name: " + (name != null ? name : "Unknown") +
                " Age: " + (age != null ? age : "Unknown") +
                " Town: " + (town != null && !town.isEmpty() ? town : "Unknown");
    }
}

