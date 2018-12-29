package com.lingaro.web.person.entity;

import javax.persistence.*;

@Entity
@NamedEntityGraph(name = Person.FETCH_ADDRESS, attributeNodes = @NamedAttributeNode("address"))
public class Person {

    public static final String FETCH_ADDRESS = "Person.address";
    private int id;
    private String name;
    private String surname;
    private Address address;

    Person() {
    }

    public Person(String name, String surname, Address address) {
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    @Override
    public String toString() {
        return name + " " + surname + " @ " + getAddress();
    }


    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
