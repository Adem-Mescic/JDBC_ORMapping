package com.example.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    public IntegerProperty id = new SimpleIntegerProperty();

    public StringProperty name = new SimpleStringProperty();
    public StringProperty wohnort = new SimpleStringProperty();

    public Person(){

    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getWohnort() {
        return wohnort.get();
    }

    public StringProperty wohnortProperty() {
        return wohnort;
    }

    public void setWohnort(String wohnort) {
        this.wohnort.set(wohnort);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", wohnort='" + wohnort + '\'' +
                '}';
    }
}
