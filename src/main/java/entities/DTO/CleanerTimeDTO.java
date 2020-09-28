package entities.DTO;


import entities.Cleaner;

import java.util.Date;

public class CleanerTimeDTO {
    private Cleaner cleaner;
    private Date date;

    public Cleaner getCleaner() {
        return cleaner;
    }

    public void setCleaner(Cleaner cleaner) {
        this.cleaner = cleaner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}