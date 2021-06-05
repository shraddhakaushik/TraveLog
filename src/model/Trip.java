package model;

import java.util.Date;

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

    //methods for setting fields:

    public void setName(String name)
    {
        this.name = name;
    }

    public void setRating(int rating)
    {
        this.rating = rating;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public void setDistance(int distance)
    {
        this.distance = distance;
    }

    public void setDate(Date date)
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

}
