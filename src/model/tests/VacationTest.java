package model.tests;

import model.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.Date;

public class VacationTest
{
    private Vacation vacation;
    private Trip hike;
    private Trip day;
    private Trip overnight;

    @BeforeEach
    public void setup() {
        vacation = new Vacation(0, 0, "");
        hike = new Hike("a", 3, "none", 50,
                new Date(2021, 5, 10), 7);
        day = new DayTrip("b", 4, "", 25,
                new Date(2021, 5, 10), "picnic", 0);
        overnight = new OvernightTrip("b", 4, "", 25,
                new Date(2021, 5, 10), 5, 0, "hotel blue");
    }
}
