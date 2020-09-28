package entities;

import java.util.UUID;

public final class Locker {

    private UUID id;
    private int number;
    private boolean free = true;

    public Locker() {
        number = -1;
    }

    public Locker(int number) {
        this.number = number;
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
        return "Locker number: " + this.number + " is free?: " + this.free + "\n";
    }
}
