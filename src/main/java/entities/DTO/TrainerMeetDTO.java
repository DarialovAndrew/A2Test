package entities.DTO;

import entities.Client;
import entities.Trainer;

import java.util.Date;

public class TrainerMeetDTO {
    private Trainer trainer;
    private Client client;
    private Date date;

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
