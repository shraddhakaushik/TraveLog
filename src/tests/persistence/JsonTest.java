package tests.persistence;

import model.DayTrip;
import model.Hike;
import model.OvernightTrip;
import model.Vacation;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkDay(String subclass, String name, int rating, String notes, int distance, int day, int month, int year, String type, int dollars, DayTrip t) {
        assertEquals(subclass, "DayTrip");
        assertEquals(name, t.getName());
        assertEquals(rating, t.getRating());
        assertEquals(notes, t.getNotes());
        assertEquals(distance, t.getDistance());
        assertEquals(day, t.getDate().getDate());
        assertEquals(month, t.getDate().getMonth());
        assertEquals(year, t.getDate().getYear());
        assertEquals(type, t.getType());
        assertEquals(dollars, t.getCost());
    }

    protected void checkHike(String subclass, String name, int rating, String notes, int distance, int day, int month, int year, int difficulty, Hike t) {
        assertEquals(subclass, "Hike");
        assertEquals(name, t.getName());
        assertEquals(rating, t.getRating());
        assertEquals(notes, t.getNotes());
        assertEquals(distance, t.getDistance());
        assertEquals(day, t.getDate().getDate());
        assertEquals(month, t.getDate().getMonth());
        assertEquals(year, t.getDate().getYear());
        assertEquals(difficulty, t.getDifficulty());
    }

    protected void checkOvernight(String subclass, String name, int rating, String notes, int distance, int day, int month, int year, int days, int cost, String stay, OvernightTrip t) {
        assertEquals(subclass, "OvernightTrip");
        assertEquals(name, t.getName());
        assertEquals(rating, t.getRating());
        assertEquals(notes, t.getNotes());
        assertEquals(distance, t.getDistance());
        assertEquals(day, t.getDate().getDate());
        assertEquals(month, t.getDate().getMonth());
        assertEquals(year, t.getDate().getYear());
        assertEquals(days, t.getDays());
        assertEquals(cost, t.getCost());
        assertEquals(stay, t.getStay());
    }

    protected void checkVacation(String subclass, String name, int duration, int cost, String notes, int day, int month, int year, Vacation t) {
        assertEquals(subclass, "Vacation");
        assertEquals(name, t.getName());
        assertEquals(notes, t.getNotes());
        assertEquals(day, t.getDate().getDate());
        assertEquals(month, t.getDate().getMonth());
        assertEquals(year, t.getDate().getYear());
    }


}
