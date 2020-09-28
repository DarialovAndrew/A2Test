package entities;

import java.util.UUID;

public final class Client {

    private final String name;
    private final boolean VIP;
    private UUID id;
    private boolean present;

    public Client(String name, boolean present, boolean VIP) {
        this.name = name;
        this.present = present;
        this.VIP = VIP;
    }

    public Client() {
        name = "";
        VIP = false;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public boolean isVIP() {
        return VIP;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void work(Apparatus apparatus) {
        System.out.println(this.name + " I am working on: " + apparatus.getName());
        apparatus.setNotFree();
    }

    public void locker(Locker locker) {
        System.out.println("I am using locker: " + locker.getNumber());
    }

    public void shower(Shower shower) {
        System.out.println("I am using shower: " + shower.getNumber());
    }

    public void train() {
        System.out.println(this.name + " I am training");
    }

    public String toString() {
        return this.name + " present: " + this.present + " VIP: " + this.VIP + "\n";
    }
}
