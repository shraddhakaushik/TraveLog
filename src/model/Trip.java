package model;

import java.util.Date;

public abstract class Trip extends Travel {
    protected int rating;
    protected int distance;


    //Constructor for Trip where name represents the name of the location, rating represents an integer value between
    //1 and 5 to indicate how enjoyable the trip was, 5 being "very enjoyable". Notes represents any extra notes the
    //user might want to leave to themselves about the trip, and distance represents how far from home the location is
    //in kilometres.
    public Trip(String name, int rating, String notes, int distance, Date date, int cost) {
        super(name, notes, date, cost);
        this.rating = rating;
        this.distance = distance;

    }

    //methods for setting fields:

    public void setRating(int rating) {
        this.rating = rating;
    }


    public void setDistance(int distance) {
        this.distance = distance;
    }



    //methods for getting fields:



    public int getRating() {
        return rating;
    }



    public int getDistance() {
        return distance;
    }

}
