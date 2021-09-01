package tests.persistence;

import exceptions.TravelsException;
import model.*;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Travels t = reader.read();
            fail("IOException e");
        } catch (IOException e) {
            //pass
        } catch (TravelsException t) {
            fail("caught travels exception");
        }
    }

    @Test
    void testReaderEmptyTravels() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTravels.json");
        try {
            Travels t = reader.read();
            assertEquals("Travels", t.getName());
            assertEquals(0, t.getMap().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (TravelsException t) {
            fail("caught travels exception");
        }
    }

    @Test
    void testReaderGeneralTravels() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTravels");
        try {
            Travels t = reader.read();
            assertEquals(4, t.getTravels().size());
            checkDay("DayTrip", "bob day", 1, "", 1, 1, 1, 2001, "picnic", 1, (DayTrip) t.getTravels().get("bob day"));
            checkHike("Hike", "bob hike", 1, "", 1, 1, 1, 2001, 1, (Hike) t.getTravels().get("bob hike"));
            checkOvernight("OvernightTrip", "bob overnight", 1, "", 1, 1, 1, 2001, 1, 1, "hotel", (OvernightTrip) t.getTravels().get("bob overnight"));
            checkVacation("Vacation", "bob vacation", 1, 1,"", 1, 1, 2001, (Vacation) t.getTravels().get("bob vacation"));
        } catch (IOException e) {
            fail("couldn't read from file");
        } catch (TravelsException e) {
            fail("caught travels exception");
        }
    }


}
