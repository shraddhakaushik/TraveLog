package tests.persistence;

import exceptions.TravelsException;
import exceptions.VacationTripException;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {
    private Date date = new Date();
    private Calendar cal;

    @BeforeEach
    void setupDate() {
        cal = new GregorianCalendar(2002, Calendar.SEPTEMBER, 18);
        date = cal.getTime();
    }

    @Test
    void testWriterInvalidFile() {
        try {
            Travels t = new Travels();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testWriterEmptyTravels() {
        try {
            Travels t = new Travels();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTravels.json");
            writer.open();
            writer.write(t);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTravels.json");
            t = reader.read();
            assertEquals("Travels", t.getName());
            assertEquals(0, t.getTravels().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (TravelsException e) {
            fail("Caught travels exception");
        }
    }

    @Test
    void testWriterGeneralTravels() {
        try {
            Hike hike = new Hike("hike", 5, "", 5, date, 5);
            DayTrip dayTrip = new DayTrip("day", 5, "", 5, date, "date", 20);
            OvernightTrip overnightTrip = new OvernightTrip("overnight", 5, "", 5, date, 5, 5, "camp");
            Vacation vacation = new Vacation("vacay", 5, 5, "", date);
            Travels t = new Travels();
            t.addTravel(hike);
            t.addTravel(dayTrip);
            t.addTravel(overnightTrip);
            t.addTravel(vacation);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTravels.json");
            writer.open();
            writer.write(t);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTravels.json");
            t = reader.read();
            assertEquals("Travels", t.getName());
            assertEquals(4, t.getTravels().size());
            checkHike("Hike", "hike", 5, "", 5, 18, 8, 2002 - 1900, 5, hike);
            checkDay("DayTrip", "day", 5, "", 5, 18, 8, 2002 - 1900, "date", 20, dayTrip);
            checkOvernight("OvernightTrip", "overnight", 5, "", 5, 18, 8, 2002 - 1900, 5, 5, "camp", overnightTrip);
            checkVacation("Vacation", "vacay", 5, 5, "", 18, 8, 2002 - 1900, vacation);

        } catch (TravelsException e) {
            fail("caught one of the travel exceptions");
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterVacationSubtrip() {
        try {
            Travels t = new Travels();
            Vacation vacation = new Vacation("vacay", 5, 5, "", date);
            DayTrip dayTrip = new DayTrip("day", 5, "", 5, date, "date", 20);
            vacation.addTrip(dayTrip);
            t.addTravel(vacation);

            JsonWriter writer = new JsonWriter("./data/testWriterVacationSubtrip.json");
            writer.open();
            writer.write(t);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterVacationSubtrip.json");
            t = reader.read();
            assertEquals("Travels", t.getName());
            assertEquals(2, t.getTravels().size());
            assertEquals(1, vacation.getTrips().size());
            checkVacation("Vacation", "vacay", 5, 5, "", 18, 8, 2002 - 1900, vacation);
            checkDay("DayTrip", "day", 5, "", 5, 18, 8, 2002 - 1900, "date", 20, dayTrip);

        } catch (VacationTripException ve) {
            fail("ve");
        } catch (FileNotFoundException fe) {
            fail("fe");
        } catch (TravelsException te) {
            fail("te");
        } catch (IOException e) {
            fail(e);
        }
    }

}
