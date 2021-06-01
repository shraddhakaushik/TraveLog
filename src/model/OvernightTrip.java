package model;

import java.util.Date;

public class OvernightTrip extends Trip
{
    private int days;
    private int cost;
    private String stay;


    public OvernightTrip(String name, int rating, String notes, int distance, Date date, int days, int cost, String stay)
    {
        super(name, rating, notes, distance, date);
    }


}
