package model;

import org.json.JSONObject;

import java.util.Date;

public abstract class Travel {
    protected int cost;
    protected String notes;
    protected String name;
    protected Date date;

    //Constructor for Travel where name refers to the name of the travel, notes refers to any miscellaneous notes about
    //the travel, date is the date on which the travel began/took place, and cost refers to the total cost of the travel
    public Travel(String name, String notes, Date date, int cost) {
        this.name = name;
        this.notes = notes;
        this.date = date;
        this.cost = cost;
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
    public int getCost() { return cost; }
    public void setCost(int cost) { this.cost = cost; }
}
