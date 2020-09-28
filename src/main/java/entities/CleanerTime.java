package entities;

import java.util.Date;
import java.util.UUID;

public final class CleanerTime {

    private UUID id;

    private Cleaner cleaner;
    private Date date;

    public CleanerTime(Cleaner cleaner, Date date) {
        this.cleaner = cleaner;
        this.date = date;
    }

    public CleanerTime() {
    }

    public UUID getId() {
        return id;
    }

    public Cleaner getCleaner() {
        return cleaner;
    }

    public Date getDate() {
        return date;
    }

    public String toString() {
        return this.cleaner.getName() + " works on " + this.date.toString() + "\n";
    }
}