package model;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

public class OvernightTrip extends Trip {
    private int days;
    private int cost;
    private String stay;


    //Constructor for OvernightTrip where name, rating, notes, distance, and date are the same as in Trip, days is an
    //unrestricted integer value of the number of days spent at the location, cost is the dollar amount the trip cost
    //including stay and expenses, and stay is the place at which they spent the night
    public OvernightTrip(String name, int rating, String notes, int distance, Date date, int days, int cost, String stay) {
        super(name, rating, notes, distance, date, cost);
        this.stay = stay;
        this.days = days;
    }

    //getters and setters for fields

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getStay() {
        return stay;
    }

    public void setStay(String stay) {
        this.stay = stay;
    }

    @Override
    public Class getSubclass() {
        return OvernightTrip.class;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("subclass", "OvernightTrip");
        json.put("name", name);
        json.put("rating", rating);
        json.put("notes", notes);
        json.put("distance", distance);
        json.put("day", Calendar.DAY_OF_MONTH);
        json.put("month", Calendar.MONTH);
        json.put("year", Calendar.YEAR);
        json.put("days", days);
        json.put("cost", cost);
        json.put("stay", stay);
        return json;
    }
}
