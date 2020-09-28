package entities.DTO;


import entities.Client;
import entities.Trainer;

import java.util.Date;
import java.util.List;

public class GroupMeetDTO {
    private List<Client> clients;
    private Trainer trainer;
    private Date date;
    private int maxClients;

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMaxClients() {
        return maxClients;
    }

    public void setMaxClients(int maxClients) {
        this.maxClients = maxClients;
    }
}

