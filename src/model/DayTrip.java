package model;

import java.util.Date;

public class DayTrip extends Trip
{
    private String type;
    private int dollars;

    //Constructor for DayTrip where name, rating, notes, and distance serve the same purpose as in Trip and
    //type refers to the type of excursion (eg. picnic, museum, etc) and dollars refers to the cost of the excursion in
    //dollars (0.00 if free)
    public DayTrip(String name, int rating, String notes, int distance, Date date, String type, int dollars)
    {
        super(name, rating, notes, distance, date);
        this.type = type;
        this.dollars = dollars;
    }

    //sets this.type to type
    public void changeType(String type)
    {
        this.type = type;
    }

    //sets this.dollars to dollars
    public void changeDollars(int dollars)
    {
        this.dollars = dollars;
    }

    public String getType()
    {
        return type;
    }

    public int getDollars()
    {
        return dollars;
    }


}
