package model;

import org.json.JSONObject;

import java.util.Date;

public abstract class Travel {
    protected String notes;
    protected String name;
    protected Date date;

    public Travel(String name, String notes, Date date) {
        this.name = name;
        this.notes = notes;
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getName() { return name; }
    public String getNotes() { return notes; }
    public Date getDate() { return date; }
    public abstract Class getSubclass();
    public  abstract JSONObject toJson();
}
