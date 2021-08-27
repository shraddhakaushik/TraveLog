package ui;

import exceptions.TravelsException;
import exceptions.VacationTripException;
import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class TraveLogApp {
    private static final String JSON_STORE = "./data/savedTravels.json";
    private Scanner typed;
    private Travels travels = new Travels();
    private JsonWriter writer;
    private JsonReader reader;
    private boolean keepGoing = true;

    public TraveLogApp() {
        //vacation = new Vacation("untitled vacation", 0, 0, "", null);
        typed = new Scanner(System.in);
        initiate();
    }

    private void initiate() {
        System.out.println("**** TraveLog ****");
        reader = new JsonReader(JSON_STORE);
        writer = new JsonWriter(JSON_STORE);
        loadTravelsJSON();
        menu();
    }

    private void menu() {
        System.out.println("Select a number among the following options:");
        System.out.println("1. Log new travel");
        System.out.println("2. View all saved travels");
        System.out.println("3. Search saved travels");
        System.out.println("4. Close program");
        String in = typed.nextLine();
        switch (in) {
            case "1":
                System.out.println("Would you like to log a hike, a day trip, an overnight trip, or a vacation?");
                createTravel(typed.nextLine());
            case "2":
                System.out.println("Travels: ");
                for (Travel t: travels.getTravels().values()) {
                    renderTravel(t);
                }
                menu();
            case "3":
                //TODO
                break;
            case  "4":
                break;
            default:
                System.out.println("invalid input");
                menu();
        }
    }

    private Travel createTravel(String type) {
        Travel t;
        switch (type) {
            case "hike":
                t = createHike();
                addTravelToTravels(t);
                break;
            case "day trip":
                t = createDayTrip();
                addTravelToTravels(t);
                break;
            case "overnight trip":
                t = createOvernightTrip();
                addTravelToTravels(t);
                break;
            case "vacation":
                t = createVacation();
                addTravelToTravels(t);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        menu();
        return t;
    }

    private void addTravelToTravels(Travel t) {
        try {
            travels.addTravel(t);
            saveTravelsJSON();
        } catch (TravelsException e) {
            e.printStackTrace();
        }
    }

    private OvernightTrip createOvernightTrip() {
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
        renderOvernight(night);
        return night;
    }

    private void renderTravel(Travel t) {
        if (t.getClass().equals(Hike.class)) {
            renderHike((Hike) t);
        } else if (t.getClass().equals(OvernightTrip.class)) {
            renderOvernight((OvernightTrip) t);
        } else if (t.getClass().equals(DayTrip.class)) {
            renderDay((DayTrip) t);
        } else if (t.getClass().equals(Vacation.class)) {
            renderVacation((Vacation) t);
        }
    }

    private void renderOvernight(OvernightTrip night) {
        System.out.println("**************************");
        System.out.println("name: " + night.getName());
        System.out.println("rating: " + night.getRating() + "/5");
        System.out.println("notes: " + night.getNotes());
        System.out.println("distance: " + night.getDistance() + "km");
        System.out.println("date: " + night.getDate().toString());
        System.out.println("days: " + night.getDays());
        System.out.println("cost: $" + night.getCost());
        System.out.println("stay: " + night.getStay());
        System.out.println("**************************");
    }

    private Hike createHike() {
        Hike hike = new Hike("untitled hike", 0, "", 0, null, 0);
        setName(hike);
        setRating(hike);
        setNotes(hike);
        setDistance(hike);
        setDate(hike);
        setDifficulty(hike);
        System.out.println("New Hike Successfully Created:");
        renderHike(hike);
        return hike;
    }

    private void renderHike(Hike hike) {
        System.out.println("**************************");
        System.out.println("name: " + hike.getName());
        System.out.println("rating: " + hike.getRating() + "/5");
        System.out.println("notes: " + hike.getNotes());
        System.out.println("distance: " + hike.getDistance() + "km");
        System.out.println("date: " + hike.getDate().toString());
        System.out.println("difficulty: " + hike.getDifficulty() + "/10");
        System.out.println("**************************");
    }

    private DayTrip createDayTrip() {
        DayTrip day = new DayTrip("untitled day trip", 0, "", 0, null, "", 0);
        setName(day);
        setRating(day);
        setNotes(day);
        setDistance(day);
        setDate(day);
        setType(day);
        setCost(day);
        renderDay(day);
        return day;
    }

    private void renderDay(DayTrip day) {
        System.out.println("New Day Trip Successfully Created:");
        System.out.println("**************************");
        System.out.println("name: " + day.getName());
        System.out.println("rating: " + day.getRating() + "/5");
        System.out.println("notes: " + day.getNotes());
        System.out.println("distance: " + day.getDistance() + "km");
        System.out.println("date: " + day.getDate().toString());
        System.out.println("type: " + day.getType());
        System.out.println("dollars: $" + day.getCost());
        System.out.println("**************************");
    }

    private Vacation createVacation() {
        Vacation vac = new Vacation("untitled vacation", 0, 0, "", null);
        setName(vac);
        setDuration(vac);
        setCost(vac);
        setNotes(vac);
        setDate(vac);
        boolean keepAsking = true;
        String trips = "";
        trips = addTripsToVac(vac, keepAsking, trips);
        renderVacation(vac);
        return vac;
    }

    private void renderVacation(Vacation vac) {
        System.out.println("New Vacation Successfully Created:");
        System.out.println("**************************");
        System.out.println("name: " + vac.getName());
        System.out.println("duration: " + vac.getDuration() + " days");
        System.out.println("cost: $" + vac.getCost());
        System.out.println("notes: " + vac.getNotes());
        System.out.println("date: " + vac.getDate().toString());
        System.out.println("trips: " + vac.getTrips().toString());
        System.out.println("**************************");
    }

    private String addTripsToVac(Vacation vac, boolean keepAsking, String trips) {
        while (keepAsking) {
            System.out.println("Would you like to add trips to this vacation? Type YES or NO");
            String input = typed.nextLine();
            if (input.equals("YES")) {
                System.out.println("Will you be adding a hike, day trip, or overnight trip");
                try {
                    Trip trip = (Trip) createTravelForVac(typed.nextLine());
                    vac.addTrip(trip);
                    trips = trips + trip.getName() + " ";
                } catch (VacationTripException e) {
                    e.printStackTrace();
                }
            } else if (input.equals("NO")){
                keepAsking = false;
                break;
            } else {
                System.out.println("invalid input");
            }
        }
        return trips;
    }

    private Object createTravelForVac(String type) {
        Travel t;
        switch (type) {
            case "hike":
                t = createHike();
                addTravelToTravels(t);
                break;
            case "day trip":
                t = createDayTrip();
                addTravelToTravels(t);
                break;
            case "overnight trip":
                t = createOvernightTrip();
                addTravelToTravels(t);
                break;
            case "vacation":
                t = createVacation();
                addTravelToTravels(t);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return t;
    }

    private void setDuration(Vacation vac) {
        System.out.println("How many days was the vacation?");
        vac.setDuration(Integer.parseInt(typed.nextLine()));
        System.out.println("duration: " + vac.getDuration() + " days");
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
        System.out.println("cost: $" + t.getCost());
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
        System.out.println("distance: " + t.getDistance() + " km");
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
        System.out.println("rating: " + t.getRating() + "/5");
    }

    private void setName(Travel t) {
        System.out.println("Please enter the name below:");
        String name = typed.nextLine();
        t.setName(name);
        System.out.println("name: " + t.getName());
    }

    private void saveTravelsJSON() {
        try {
            writer.open();
            writer.write(travels);
            writer.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadTravelsJSON() {
        try {
            travels = reader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException | TravelsException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}
