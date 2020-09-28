package entities;

import java.util.UUID;


public final class Apparatus {

    boolean free = true;
    private UUID id;
    private String name;
    private int age;

    public Apparatus() {
        name = "";
    }


    public Apparatus(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public boolean isFree() {
        return free;
    }

    public UUID getId() {
        return id;
    }

    public void setFree() {
        this.free = true;
    }

    public String getName() {
        return name;
    }

    public void setNotFree() {
        this.free = false;
    }

    public String toString() {
        return this.name + " age: " + this.age + " is free?: " + this.free + "\n";
    }
}
