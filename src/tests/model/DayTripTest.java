package tests.model;

import model.DayTrip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DayTripTest {
    private DayTrip dayTrip;
    private Calendar cal = new GregorianCalendar(2012, Calendar.MAY, 21);
    private Date date = cal.getTime();

    @BeforeEach
    public void setup() {
        dayTrip = new DayTrip("a", 3, ":)", 20, date, "picnic", 5);
    }

    @Test
    public void testConstructor() {
        assertEquals("a", dayTrip.getName());
        assertEquals(3, dayTrip.getRating());
        assertEquals(":)", dayTrip.getNotes());
        assertEquals(20, dayTrip.getDistance());
        assertEquals(date, dayTrip.getDate());
        assertEquals("picnic", dayTrip.getType());
        assertEquals(5, dayTrip.getDollars());
    }

    @Test
    public void testSetName() {
        dayTrip.setName("b");
        assertEquals("b", dayTrip.getName());
    }

    @Test
    public void testSetRating() {
        dayTrip.setRating(8);
        assertEquals(8, dayTrip.getRating());
    }

    @Test
    public void testSetNotes() {
        dayTrip.setNotes("b");
        assertEquals("b", dayTrip.getNotes());
    }

    @Test
    public void testSetDistance() {
        dayTrip.setDistance(55);
        assertEquals(55, dayTrip.getDistance());
    }

    @Test
    public void testSetDate() {
        Calendar cal = new GregorianCalendar(2002, Calendar.SEPTEMBER, 18);
        Date date2 = cal.getTime();
        dayTrip.setDate(date2);
        assertEquals(date2, dayTrip.getDate());
    }

}
