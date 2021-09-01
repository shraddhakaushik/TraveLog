package model;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

public class DayTrip extends Trip {
    private String type;

    //Constructor for DayTrip where name, rating, notes, distance, and date serve the same purpose as in Trip and
    //type refers to the type of excursion (eg. picnic, museum, etc) and dollars refers to the cost of the excursion in
    //dollars (0.00 if free)
    public DayTrip(String name, int rating, String notes, int distance, Date date, String type, int cost) {
        super(name, rating, notes, distance, date, cost);
        this.type = type; 
    }

    //getters and setters for fields

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public Class getSubclass() {
        return DayTrip.class;
    }


    // creates a JSON object for day trip
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("subclass", "DayTrip");
        json.put("name", name);
        json.put("rating", rating);
        json.put("notes", notes);
        json.put("distance", distance);
        json.put("day", Calendar.DAY_OF_MONTH);
        json.put("month", Calendar.MONTH);
        json.put("year", Calendar.YEAR);
        json.put("type", type);
        json.put("cost", cost);
        return json;
    }
}
