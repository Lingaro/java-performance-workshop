package com.lingaro.web.person.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Address {
    @Id
    @GeneratedValue
    public int id;
    @NotEmpty
    public String name;

    Address() {
    }

    public Address(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
