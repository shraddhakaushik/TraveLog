package tests;

import exceptions.VacationTripException;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.fail;

public class VacationTest {
    private Vacation vacation;
    private Trip hike;
    private Trip day;
    private Trip overnight;
    private Date testDate =  new Date(2021, 5, 10);

    @BeforeEach
    public void setup() {
        vacation = new Vacation("test", 0, 0, "", testDate);
        hike = new Hike("a", 3, "none", 50,
                testDate, 7);
        day = new DayTrip("b", 4, "", 25,
                testDate, "picnic", 0);
        overnight = new OvernightTrip("b", 4, "", 25, testDate, 5, 0, "hotel blue");
    }

    @Test
    public void testAddTripDoesntContain() {
        try {
            vacation.addTrip(hike);
            //pass
        } catch (VacationTripException e) {
            fail("caught exception");
        }
    }

    @Test
    public void testAddTripContains() {
        try {
            vacation.addTrip(hike);
            try {
                vacation.addTrip(hike);
                fail("didn't catch exception");
            } catch (VacationTripException ex) {
                //pass
            }
        } catch (VacationTripException e) {
            fail("caught first exception");
        }
    }

    @Test
    public void testAddTripTwoDiff() {
        try {
            vacation.addTrip(hike);
            try {
                vacation.addTrip(day);
                //pass
            } catch (VacationTripException ex) {
                fail("caught second exception");
            }
        } catch (VacationTripException e) {
            fail("caught first exception");
        }
    }

    @Test
    public void testAddTripThreeDiff() {
        try {
            vacation.addTrip(hike);
            try {
                vacation.addTrip(day);
                try {
                    vacation.addTrip(overnight);
                    //pass
                } catch (VacationTripException exc) {
                    fail("caught third exception");
                }
            } catch (VacationTripException ex) {
                fail("caught second exception");
            }
        } catch (VacationTripException e) {
            fail("caught first exception");
        }
    }

    @Test
    public void testRemoveTripContains() {
        try {
            vacation.addTrip(hike);
            try {
                vacation.removeTrip(hike);
                //pass
            } catch (VacationTripException ex) {
                fail("caught second exception");
            }
        } catch (VacationTripException e) {
            fail("caught first exception");
        }
    }

    @Test
    public void testRemoveDoesntContain() {
        try {
            vacation.removeTrip(hike);
            fail("didn't catch exception");
        } catch (VacationTripException e) {
           //pass
        }
    }

    @Test
    public void testRemoveTwice() {
        try {
                vacation.addTrip(day);
                try {
                    vacation.removeTrip(day);
                    try {
                        vacation.removeTrip(day);
                        fail("didn't catch second remove exception");
                    } catch (VacationTripException exc) {
                        //pass
                    }
                } catch (VacationTripException ex) {
                    fail("caught first remove exception");
                }
            } catch (VacationTripException e) {
                fail("caught add trip exception");
            }
    }

}
