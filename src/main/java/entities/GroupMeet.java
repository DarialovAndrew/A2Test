package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public final class GroupMeet {

    private UUID id;
    private List<Client> clients;
    private Trainer trainer;
    private Date date;
    private int maxClients;

    public GroupMeet(Trainer trainer, Date date, int maxClients) {
        this.clients = new ArrayList<Client>();
        this.trainer = trainer;
        this.date = date;
        this.maxClients = maxClients;
    }

    public GroupMeet() {
        maxClients = -1;
    }

    public UUID getId() {
        return id;
    }

    public void addClient(Client client) {
        this.clients.add(client);
    }

    public List<Client> getClients() {
        return clients;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public Date getDate() {
        return date;
    }

    public String toString() {
        String s = "";
        for (Client client : clients) {
            s += client.toString() + "\n";

        }
        return "GroupMeeting with " + this.trainer + " max participants: " + this.maxClients + " Date: " + this.date.toString() + "\nparticipants:\n" + s;
    }

}
