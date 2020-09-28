package entities;

import java.util.UUID;

public final class Owner {

    private final String name;
    private UUID id;
    private int profit;

    public Owner(String name, int profit) {
        this.name = name;
        this.profit = profit;
    }

    public Owner() {
        name = "";
    }

    public UUID getId() {
        return id;
    }

    public void sayResults() {
        System.out.println("Current profit is:" + profit);
    }

    public void addMoney(int addition) {
        this.profit += addition;
    }

    public String toString() {
        return this.name + " has profit: " + this.profit + "\n";
    }
}