package com.company;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;

public class Hospital implements Comparable<Hospital>{
    private String name;
    private int capacity;
    private HashSet<Resident> personnel = new HashSet<Resident>();

    public Hospital(String name) {
        this.name = name;
    }

    public Hospital(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public HashSet<Resident> getPersonnel() {
        return personnel;
    }

    public void addResident(Resident resident) {
        personnel.add(resident);
        capacity--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hospital hospital = (Hospital) o;
        return name.equals(hospital.name);
    }

    @Override
    public String toString() { return name; }

    @Override
    public int compareTo(Hospital h) {
        return (this.getName().compareTo(h.getName()));
    }
}
