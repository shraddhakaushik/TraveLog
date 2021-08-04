package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class TraveLogApp {
    private static final String JSON_STORE = "./data/savedNotes.json";
    private Scanner typed;
    private Travels travels = new Travels();
    private JsonWriter writer;
    private JsonReader reader;
    private boolean keepGoing = true;

    public TraveLogApp() {
        //vacation = new Vacation("untitled vacation", 0, 0, "", null);
        typed = new Scanner(System.in);
        reader = new JsonReader(JSON_STORE);
        writer = new JsonWriter(JSON_STORE);
        initiate();
    }

    private void initiate() {
        System.out.println("**** TraveLog ****");
        System.out.println("Would you like to log a hike, a day trip, an overnight trip, or a vacation?");
        createTravel(typed.nextLine());
    }

    private void createTravel(String type) {
        switch (type) {
            case "hike":
                createHike();
                break;
            case "day trip":
                createDayTrip();
                break;
            case "overnight trip":
                createOvernightTrip();
                break;
            case "vacation":
                //stub
                break;
        }
    }

    private void createOvernightTrip() {
        OvernightTrip night = new OvernightTrip("untitled overnight trip", 0, "", 0, null, 0, 0, "");
        setName(night);
        setRating(night);
        setNotes(night);
        setDistance(night);
        setDate(night);
        setDays(night);
        setCost(night);
        setStay(night);
        System.out.println("New Overnight Trip Successfully Created:");
        System.out.println("**************************");
        System.out.println("name: " + night.getName());
        System.out.println("rating: " + night.getRating());
        System.out.println("notes: " + night.getNotes());
        System.out.println("distance: " + night.getDistance());
        System.out.println("date: " + night.getDate().toString());
        System.out.println("days: " + night.getDays());
        System.out.println("cost: " + night.getCost());
        System.out.println("stay: " + night.getStay());
        System.out.println("**************************");
    }

    private void createHike() {
        Hike hike = new Hike("untitled hike", 0, "", 0, null, 0);
        setName(hike);
        setRating(hike);
        setNotes(hike);
        setDistance(hike);
        setDate(hike);
        setDifficulty(hike);
        System.out.println("New Hike Successfully Created:");
        System.out.println("**************************");
        System.out.println("name: " + hike.getName());
        System.out.println("rating: " + hike.getRating());
        System.out.println("notes: " + hike.getNotes());
        System.out.println("distance: " + hike.getDistance());
        System.out.println("date: " + hike.getDate().toString());
        System.out.println("difficulty: " + hike.getDifficulty());
        System.out.println("**************************");
    }

    private void createDayTrip() {
        DayTrip day = new DayTrip("untitled day trip", 0, "", 0, null, "", 0);
        setName(day);
        setRating(day);
        setNotes(day);
        setDistance(day);
        setDate(day);
        setType(day);
        setCost(day);
        System.out.println("New Day Trip Successfully Created:");
        System.out.println("**************************");
        System.out.println("name: " + day.getName());
        System.out.println("rating: " + day.getRating());
        System.out.println("notes: " + day.getNotes());
        System.out.println("distance: " + day.getDistance());
        System.out.println("date: " + day.getDate().toString());
        System.out.println("type: " + day.getType());
        System.out.println("dollars: " + day.getCost());
        System.out.println("**************************");
    }

    private void setStay(OvernightTrip night) {
        System.out.println("Enter the place at which you stayed below: ");
        night.setStay(typed.nextLine());
        System.out.println("stay: " + night.getStay());
    }

    private void setDays(OvernightTrip night) {
        System.out.println("Enter the number of days this trip took. Please enter an integer value " + night.getDays());
        night.setDays(Integer.parseInt(typed.nextLine()));
        System.out.println("days: " + night.getDays());
    }

    private void setCost(Travel t) {
        System.out.println("In dollars, enter the total cost of the trip");
        t.setCost(Integer.parseInt(typed.nextLine()));
        System.out.println("cost: " + t.getCost());
    }

    private void setType(DayTrip day) {
        System.out.println("What kind of a day trip was it? (picnic, date, coffee, drive, lake, etc.)");
        day.setType(typed.nextLine());
        System.out.println("type: " + day.getType());
    }

    private void setDifficulty(Hike hike) {
        System.out.println("Finally, enter the difficulty level of the hike on a scale of 1-10. Please enter an integer value");
        hike.setDifficulty(Integer.parseInt(typed.nextLine()));
        System.out.println("difficulty: " + hike.getDifficulty());
    }

    private void setDate(Travel t) {
        System.out.println("In order to log the date, first type the year below:");
        int yr = Integer.parseInt(typed.nextLine());
        System.out.println("now type the month number below:");
        int mn = Integer.parseInt(typed.nextLine()) - 1;
        System.out.println("now type the date below:");
        int dt = Integer.parseInt(typed.nextLine());
        Calendar cal = new GregorianCalendar(yr, mn, dt);
        Date dte = cal.getTime();
        t.setDate(dte);
        System.out.println("date: " + t.getDate().toString());
    }

    private void setDistance(Trip t) {
        System.out.println("In kilometres, enter the distance from home. Please enter an integer value");
        String dist = typed.nextLine();
        t.setDistance(Integer.parseInt(dist));
        System.out.println("distance: " + t.getDistance());
    }

    private void setNotes(Travel t) {
        System.out.println("Enter any key notes about the travel. Type DONE when finished notes");
        boolean done = false;
        while(!done) {
            String notes = typed.nextLine();
            if (notes.equals("DONE")) {
                done = true;
            } else {
                t.setNotes(notes);
            }
        }
        System.out.println("notes: " + t.getNotes());
    }

    private void setRating(Trip t) {
        System.out.println("Enter a rating of the trip on a scale of 1-5. Please enter an integer value");
        String rating = typed.nextLine();
        t.setRating(Integer.parseInt(rating));
        System.out.println("rating: " + t.getRating());
    }

    private void setName(Travel t) {
        System.out.println("Please enter the name below:");
        String name = typed.nextLine();
        t.setName(name);
        System.out.println("name: " + t.getName());
    }


}
