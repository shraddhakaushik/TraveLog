package model;

import java.util.Date;
import java.util.Objects;

public abstract class Trip extends Travel
{
    private String name;
    private int rating;
    private String notes;
    private int distance;
    private Date date;

    //Constructor for Trip where name represents the name of the location, rating represents an integer value between
    //1 and 5 to indicate how enjoyable the trip was, 5 being "very enjoyable". Notes represents any extra notes the
    //user might want to leave to themselves about the trip, and distance represents how far from home the location is
    //in kilometres.
    public Trip(String name, int rating, String notes, int distance, Date date)
    {
        this.name = name;
        this.rating = rating;
        this.notes = notes;
        this.distance = distance;
        this.date = date;
    }

    //methods for changing fields:

    public void changeName(String name)
    {
        this.name = name;
    }

    public void changeRating(int rating)
    {
        this.rating = rating;
    }

    public void changeNotes(String notes)
    {
        this.notes = notes;
    }

    public void changeDistance(int distance)
    {
        this.distance = distance;
    }

    public void changeDate(Date date)
    {
        this.date = date;
    }

    //methods for getting fields:

    public String getName()
    {
        return name;
    }

    public int getRating()
    {
        return rating;
    }

    public String getNotes()
    {
        return notes;
    }

    public int getDistance()
    {
        return distance;
    }

    public Date getDate()
    {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return  distance == trip.distance &&
                Objects.equals(name, trip.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rating, notes, distance, date);
    }
}
