package model;

import exceptions.VacationTripException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Vacation extends Travel {
    private List<Trip> trips;
    private int duration;


    //Constructor for Vacation where duration indicates the number of days spent on vacations, cost is the total cost of
    //the vacation, and notes are personal notes the user can leave for themself
    public Vacation(String name, int duration, int cost, String notes, Date date) {
        super(name, notes, date, cost);
        this.duration = duration;
        trips = new ArrayList<>();

    }

    //adds a new trip to trips if trips doesn't already contain the trip. Else, throws exception
    public void addTrip(Trip t) throws VacationTripException {
        if (!trips.contains(t)) {
            trips.add(t);
            assert trips.contains(t);
        } else {
            throw new VacationTripException();
        }
    }

    //removes trip if it exists in trips, else throws exception
    public void removeTrip(Trip t) throws VacationTripException {
        if (trips.contains(t)) {
            trips.remove(t);
        } else {
            throw new VacationTripException();
        }
    }


    //getters and setters for fields

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    @Override
    public Class getSubclass() {
        return Vacation.class;
    }

    public List<String> tripsToString() {
        List<String> names = new ArrayList<>();
        for (Trip t: trips) {
            names.add(t.getName());
        }
        return names;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("subclass", "Vacation");
        json.put("name", name);
        json.put("duration", duration);
        json.put("cost", cost);
        json.put("notes", notes);
        json.put("day", Calendar.DAY_OF_MONTH);
        json.put("month", Calendar.MONTH);
        json.put("year", Calendar.YEAR);
        json.put("trips", tripsToString().toString());
        for (Trip t : trips) {
            t.toJson();
        }
        return json;
    }
}
