package tests;

import exceptions.TravelsException;
import exceptions.VacationTripException;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TravelsTest {
    private Travels travels;
    private Date testDate = new Date(05, Calendar.MAY, 2020);
    private Trip t1 = new Hike("hike", 4, "", 25, testDate, 5);
    private Trip t2 = new Hike("frog", 4, "", 25, testDate, 5);
    private Vacation v1 = new Vacation("vac", 4, 70, "", testDate);
    private Vacation v2 = new Vacation("ation", 4, 70, "", testDate);

    @BeforeEach
    public void setup()
    {
        travels = new Travels();
    }

    @Test
    public void testAddTravelTripDoesntContain() {
        try {
            travels.addTravel(t1);
            assertTrue(travels.getMap().containsKey(t1.getName()));
            assertEquals(t1, travels.getMap().get(t1.getName()));
        } catch (TravelsException e) {
            fail("caught exception");
        }
    }

    @Test
    public void testAddTravelTripContains() {
        try {
            travels.addTravel(t1);
            assertTrue(travels.getMap().containsKey(t1.getName()));
            assertEquals(t1, travels.getMap().get(t1.getName()));
            try {
                travels.addTravel(t1);
                fail("didn't catch second exception");
            } catch (TravelsException e1) {
                //pass
                assertTrue(travels.getMap().containsKey(t1.getName()));
                assertEquals(t1, travels.getMap().get(t1.getName()));
            }
        } catch (TravelsException e) {
            fail("caught exception");
        }
    }

    @Test
    public void testAddTravelMultipleSeparateTrips() {
        try {
            travels.addTravel(t1);
            assertTrue(travels.getMap().containsKey(t1.getName()));
            assertEquals(t1, travels.getMap().get(t1.getName()));
            try {
                travels.addTravel(t2);
                assertTrue(travels.getMap().containsKey(t2.getName()));
                assertEquals(t2, travels.getMap().get(t2.getName()));
            } catch (TravelsException e1) {
                fail("caught second exception");
            }
        } catch (TravelsException e) {
            fail("caught exception");
        }
    }

    @Test
    public void testAddTravelEmptyVacationDoesntContain() {
        try {
            travels.addTravel(v1);
            assertTrue(travels.getMap().containsKey(v1.getName()));
            assertEquals(v1, travels.getMap().get(v1.getName()));
            assertEquals(0, v1.getTrips().size());
        } catch (TravelsException e) {
            fail("caught exception");
        }
    }

    @Test
    public void testAddTravelVacationDoesntContain() {
        try {
            try {
                v1.addTrip(t1);
            } catch (VacationTripException e) {
                fail("look into vacation implementation");
            }
            travels.addTravel(v1);
            assertTrue(travels.getMap().containsKey(v1.getName()));
            assertEquals(v1, travels.getMap().get(v1.getName()));
            assertTrue(travels.getMap().containsKey(t1.getName()));
            assertEquals(t1, travels.getMap().get(t1.getName()));
        } catch (TravelsException e) {
            fail("caught exception");
        }
    }

    @Test
    public void testAddTravelDoesntContainVacationContainsTrips() {
        try {
            try {
                v1.addTrip(t1);
                v1.addTrip(t2);
            } catch (VacationTripException e) {
                fail("look into vacation implementation");
            }

            try {
                travels.addTravel(t1);
                travels.addTravel(t2);
            } catch (TravelsException ex) {
                fail("caught exception through trip");
            }
            travels.addTravel(v1);
            assertTrue(travels.getMap().containsKey(v1.getName()));
            assertEquals(v1, travels.getMap().get(v1.getName()));
            assertTrue(travels.getMap().containsKey(t1.getName()));
            assertEquals(t1, travels.getMap().get(t1.getName()));
            assertTrue(travels.getMap().containsKey(t2.getName()));
            assertEquals(t2, travels.getMap().get(t2.getName()));
        } catch (TravelsException e) {
            fail("caught exception through vacation");
        }
    }

    @Test
    public void testAddTravelDoesntContainVacationContainsOneTrip() {
        try {
            try {
                v1.addTrip(t1);
                v1.addTrip(t2);
            } catch (VacationTripException e) {
                fail("look into vacation implementation");
            }

            try {
                travels.addTravel(t1);
            } catch (TravelsException ex) {
                fail("caught exception through trip");
            }
            travels.addTravel(v1);
            assertTrue(travels.getMap().containsKey(v1.getName()));
            assertEquals(v1, travels.getMap().get(v1.getName()));
            assertTrue(travels.getMap().containsKey(t1.getName()));
            assertEquals(t1, travels.getMap().get(t1.getName()));
            assertTrue(travels.getMap().containsKey(t2.getName()));
            assertEquals(t2, travels.getMap().get(t2.getName()));
        } catch (TravelsException e) {
            fail("caught exception through vacation");
        }
    }

    @Test
    public void testAddTravelContainsVacation() {
        try {
            try {
                v1.addTrip(t1);
                v1.addTrip(t2);
            } catch (VacationTripException e) {
                fail("look into vacation implementation");
            }

            try {
                travels.addTravel(v1);
            } catch (TravelsException ex) {
                fail("caught exception through adding vacation");
            }
            travels.addTravel(v1);
            fail("didn't catch exception");
        } catch (TravelsException e) {
            assertTrue(travels.getMap().containsKey(v1.getName()));
            assertEquals(v1, travels.getMap().get(v1.getName()));
            assertTrue(travels.getMap().containsKey(t1.getName()));
            assertEquals(t1, travels.getMap().get(t1.getName()));
            assertTrue(travels.getMap().containsKey(t2.getName()));
            assertEquals(t2, travels.getMap().get(t2.getName()));
        }
    }

    /*
    test cases for remove:
        - remove a trip contained
        - remove a trip not contained
        - remove a vacation contained
        - remove a vacation not contained
        - remove a travel from an empty list
        - remove one contained one not contained
        - remove two contained
     */

    @Test
    public void testRemoveTravelTripContained() {
        try {
            travels.addTravel(t1);
            try {
                travels.removeTravel(t1);
            } catch (TravelsException ex) {
                fail("caught exception in removing");
            }
        } catch (TravelsException e) {
            fail("caught exception in adding");
        }
    }
}
