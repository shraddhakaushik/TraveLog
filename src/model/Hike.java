package model;

import java.util.Date;

public class Hike extends Trip
{
    private int difficulty;

    //Constructor for Hike where name, rating, notes, distance, and date serve the same purpose as in Trip and difficulty
    //refers to an integer value between 1 and 10 to reflect how difficult the hike was, 10 being "extremely difficult"
    public Hike(String name, int rating, String notes, int distance, Date date, int difficulty)
    {
        super(name, rating, notes, distance, date);
        this.difficulty = difficulty;
    }


    //getters and setters for fields

    public void setDifficulty(int difficulty)
    {
        this.difficulty = difficulty;
    }

    public int getDifficulty()
    {
        return difficulty;
    }

}
