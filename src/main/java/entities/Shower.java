package entities;

import java.util.UUID;

public final class Shower {

    private UUID id;
    private int number;
    private boolean free;

    public Shower(int number, boolean free) {
        this.number = number;
        this.free = free;
    }

    public Shower() {
        number = -1;
    }

    public UUID getId() {
        return id;
    }

    public void setFree() {
        this.free = true;
    }

    public void setNotFree() {
        this.free = false;
    }

    public int getNumber() {
        return number;
    }

    public String toString() {
        return "Shower number: " + this.number + " is free?: " + this.free + "\n";
    }
}
