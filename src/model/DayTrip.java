package model;

import org.json.JSONObject;

import java.util.Date;

public class DayTrip extends Trip {
    private String type;
    private int dollars;

    //Constructor for DayTrip where name, rating, notes, distance, and date serve the same purpose as in Trip and
    //type refers to the type of excursion (eg. picnic, museum, etc) and dollars refers to the cost of the excursion in
    //dollars (0.00 if free)
    public DayTrip(String name, int rating, String notes, int distance, Date date, String type, int dollars) {
        super(name, rating, notes, distance, date);
        this.type = type;
        this.dollars = dollars;
    }

    //getters and setters for fields

    public void setType(String type) {
        this.type = type;
    }


    public void setDollars(int dollars) {
        this.dollars = dollars;
    }

    public String getType() {
        return type;
    }

    public int getDollars() {
        return dollars;
    }

    @Override
    public Class getSubclass() {
        return DayTrip.class;
    }

    @Override
    public JSONObject toJson() {
        return null; //TODO stub
    }
}
