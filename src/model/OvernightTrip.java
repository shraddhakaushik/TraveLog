package model;

import org.json.JSONObject;

import java.util.Date;

public class OvernightTrip extends Trip {
    private int days;
    private int cost;
    private String stay;


    //Constructor for OvernightTrip where name, rating, notes, distance, and date are the same as in Trip, days is an
    //unrestricted integer value of the number of days spent at the location, cost is the dollar amount the trip cost
    //including stay and expenses, and stay is the place at which they spent the night
    public OvernightTrip(String name, int rating, String notes, int distance, Date date, int days, int cost, String stay) {
        super(name, rating, notes, distance, date);
        this.stay = stay;
        this.cost = cost;
        this.days = days;
    }

    //getters and setters for fields

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
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
        json.put("day", date.getDate());
        json.put("month", date.getMonth());
        json.put("year", date.getYear());
        json.put("days", days);
        json.put("cost", cost);
        json.put("stay", stay);
        return json;
    }
}
