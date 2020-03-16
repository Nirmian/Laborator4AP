package com.company;

public class Resident {
    private String name;
    private Boolean hired;

    public Resident(String name) {
        this.name = name;
    }

    public Resident(String name, Boolean hired) {
        this.name = name;
        this.hired = hired;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHired() {
        return hired;
    }

    public void setHired(Boolean hired) {
        this.hired = hired;
    }

    @Override
    public String toString() {
        return name;
    }
}
