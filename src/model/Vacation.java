package model;

import java.util.ArrayList;
import java.util.List;

public class Vacation extends Travel
{
    private List<Trip> trips;
    private int duration;
    private int cost;
    private String notes;

    //Constructor for Vacation where duration indicates the number of days spent on vacations, cost is the total cost of
    //the vacation, and notes are personal notes the user can leave for themself
    public Vacation(int duration, int cost, String notes)
    {
        this.duration = duration;
        trips = new ArrayList<>();
        this.cost = cost;
        this.notes = notes;
    }

    //adds a new trip to trips if trips doesn't already contain the trip. Else, throws exception
    public void addTrip(Trip t) throws VacationTripException
    {
        if (!trips.contains(t))
        {
            trips.add(t);
            assert trips.contains(t);
        } else
        {
            throw new VacationTripException();
        }
    }

    //removes trip if it exists in trips, else throws exception
    public void removeTrip(Trip t) throws VacationTripException
    {
        if (trips.contains(t))
        {
            trips.remove(t);
        } else
        {
            throw new VacationTripException();
        }
    }


    //getters and setters for fields

    public int getDuration()
    {
        return duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public int getCost()
    {
        return cost;
    }

    public void setCost(int cost)
    {
        this.cost = cost;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }


}
