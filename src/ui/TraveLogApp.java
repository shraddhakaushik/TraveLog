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
    private int numUntitled = 1;

    public TraveLogApp() {
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
                searchTravel();
                break;
            case  "4":
                break;
            default:
                System.out.println("invalid input");
                menu();
        }
    }

    private void searchTravel() {
        System.out.println("Please type the name of the travel you would like to find");
        String input = typed.nextLine();
        if (travels.getTravels().containsKey(input)) {
            Travel t = travels.getTravels().get(input);
            System.out.println("Travel found!");
            System.out.println("Would you like to view or edit?");
            String in = typed.nextLine();
            if (in.equals("view")) {
                renderTravel(t);
            } else if (in.equals("edit")) {
                editTravel(t);
            }
        }
        menu();
    }

    private void editTravel(Travel t) {
        System.out.println("This is the travel currently:");
        renderTravel(t);
        System.out.println("Please indicate the field you would like to edit");
        String input = typed.nextLine();
        if (t.getClass().equals(Vacation.class)) {
            editVac((Vacation) t, input);
        } else if (t.getClass().equals(Hike.class)) {
            editHike((Hike) t, input);
        } else if (t.getClass().equals(DayTrip.class)) {
            editDay((DayTrip) t, input);
        } else if (t.getClass().equals(OvernightTrip.class)) {
            editOvernight((OvernightTrip) t, input);
        }
        System.out.println("Travel successfully edited!");
        renderTravel(t);
        saveTravelsJSON();
    }

    private void editOvernight(OvernightTrip o, String input) {
        switch (input) {
            case "name":
                setName(o);
                break;
            case "rating":
                setRating(o);
                break;
            case "notes":
                setNotes(o);
                break;
            case "distance":
                setDistance(o);
                break;
            case "date":
                setDate(o);
                break;
            case "days":
                setDays(o);
                break;
            case "cost":
                setCost(o);
                break;
            case "stay":
                setStay(o);
                break;
            default:
                throw new IllegalStateException("Invalid field");
        }
    }

    private void editDay(DayTrip d, String input) {
        switch (input) {
            case "name":
                setName(d);
                break;
            case "rating":
                setRating(d);
                break;
            case "notes":
                setNotes(d);
                break;
            case "distance":
                setDistance(d);
                break;
            case "date":
                setDate(d);
                break;
            case "type":
                setType(d);
                break;
            case "cost":
                setCost(d);
                break;
            default:
                throw new IllegalStateException("Invalid field");
        }

    }

    private void editHike(Hike h, String input) {
        switch (input) {
            case "name":
                setName(h);
                break;
            case "rating":
                setRating(h);
                break;
            case "notes":
                setNotes(h);
                break;
            case "distance":
                setDistance(h);
                break;
            case "date":
                setDate(h);
                break;
            case "difficulty":
                setDifficulty(h);
                break;
            default:
                throw new IllegalStateException("Invalid field");
        }
    }

    private void editVac(Vacation v, String input) {
        switch (input) {
            case "name":
                setName(v);
                break;
            case "duration":
                setDuration(v);
                break;
            case "cost":
                setCost(v);
                break;
            case "notes":
                setNotes(v);
                break;
            case "date":
                setDate(v);
                break;
            default:
                throw new IllegalStateException("Invalid field");
        }

    }

    private Travel createTravel(String type) {
        Travel t;
        switch (type) {
            case "hike":
                t = createHike();
                break;
            case "day trip":
                t = createDayTrip();
                break;
            case "overnight trip":
                t = createOvernightTrip();
                break;
            case "vacation":
                t = createVacation();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        addTravelToTravels(t);
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
        System.out.println("type: Overnight Trip");
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
        System.out.println("type: Hike");
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
        System.out.println("New Day Trip Successfully Created:");
        renderDay(day);
        return day;
    }

    private void renderDay(DayTrip day) {
        System.out.println("**************************");
        System.out.println("name: " + day.getName());
        System.out.println("type: Day Trip");
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
        System.out.println("New Vacation Successfully Created:");
        renderVacation(vac);
        return vac;
    }

    private void renderVacation(Vacation vac) {
        System.out.println("**************************");
        System.out.println("name: " + vac.getName());
        System.out.println("type: Vacation");
        System.out.println("duration: " + vac.getDuration() + " days");
        System.out.println("cost: $" + vac.getCost());
        System.out.println("notes: " + vac.getNotes());
        System.out.println("date: " + vac.getDate().toString());
        System.out.println("trips: " + vac.tripsToString().toString());
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
                addTravelToTravelsVac(t);
                break;
            case "day trip":
                t = createDayTrip();
                addTravelToTravelsVac(t);
                break;
            case "overnight trip":
                t = createOvernightTrip();
                addTravelToTravelsVac(t);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return t;
    }

    private void addTravelToTravelsVac(Travel t) {
        try {
            travels.addTravel(t);
        } catch (TravelsException e) {
            e.printStackTrace();
        }
    }

    private void setDuration(Vacation vac) {
        System.out.println("How many days was the vacation?");
        String input = typed.nextLine();
        if (input.equals("")) {
            vac.setDuration(0);
        } else {
            vac.setDuration(Integer.parseInt(input));
        }
        System.out.println("duration: " + vac.getDuration() + " days");
    }


    private void setStay(OvernightTrip night) {
        System.out.println("Enter the place at which you stayed below: ");
        String input = typed.nextLine();
        if (input.equals("")) {
            night.setStay("undocumented");
        } else {
            night.setStay(input);
        }
        System.out.println("stay: " + night.getStay());
    }

    private void setDays(OvernightTrip night) {
        System.out.println("Enter the number of days this trip took. Please enter an integer value " + night.getDays());
        String input = typed.nextLine();
        if (input.equals("")) {
            night.setDays(0);
        } else {
            night.setDays(Integer.parseInt(input));
        }
        System.out.println("days: " + night.getDays());
    }

    private void setCost(Travel t) {
        System.out.println("In dollars, enter the total cost of the trip");
        String input = typed.nextLine();
        if (input.equals("")) {
            t.setCost(0);
        } else {
            t.setCost(Integer.parseInt(input));
        }
        System.out.println("cost: $" + t.getCost());
    }

    private void setType(DayTrip day) {
        System.out.println("What kind of a day trip was it? (picnic, date, coffee, drive, lake, etc.)");
        String input = typed.nextLine();
        if (input.equals("")) {
            day.setType("undocumented");
        } else {
            day.setType(input);
        }
        System.out.println("type: " + day.getType());
    }

    private void setDifficulty(Hike hike) {
        System.out.println("Finally, enter the difficulty level of the hike on a scale of 1-10. Please enter an integer value");
        String input = typed.nextLine();
        if (input.equals("")) {
            hike.setDifficulty(0);
        } else {
            hike.setDifficulty(Integer.parseInt(input));
        }
        System.out.println("difficulty: " + hike.getDifficulty());
    }

    private void setDate(Travel t) {
        System.out.println("In order to log the date, first type the year below:");
        String yrInput = typed.nextLine();
        int yr = 0;
        if (!yrInput.equals("")) {
            yr = Integer.parseInt(yrInput);
        }

        System.out.println("now type the month number below:");
        String mnInput = typed.nextLine();
        int mn = 0;
        if (!mnInput.equals("")) {
            mn = Integer.parseInt(mnInput) - 1;
        }

        System.out.println("now type the date below:");
        String dtInput = typed.nextLine();
        int dt = 0;
        if (!dtInput.equals("")) {
            dt = Integer.parseInt(dtInput);
        }

        Calendar cal = new GregorianCalendar(yr, mn, dt);
        Date dte = cal.getTime();
        t.setDate(dte);
        System.out.println("date: " + t.getDate().toString());
    }

    private void setDistance(Trip t) {
        System.out.println("In kilometres, enter the distance from home. Please enter an integer value");
        String input = typed.nextLine();
        if (input.equals("")) {
            t.setDistance(0);
        } else {
            t.setDistance(Integer.parseInt(input));
        }
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
        String input = typed.nextLine();
        if (input.equals("")) {
            t.setRating(0);
        } else {
            t.setRating(Integer.parseInt(input));
        }
        System.out.println("rating: " + t.getRating() + "/5");
    }

    private void setName(Travel t) {
        System.out.println("Please enter the name below:");

        String input = typed.nextLine();
        if (input.equals("")) {
            t.setName("untitled" + numUntitled);
            numUntitled++;
        } else {
            t.setName(input);
        }
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
        } catch (TravelsException e) {
            System.out.println("Unable to read from file: " + JSON_STORE + "because of a TravelsException");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE + "because of an IOException");
        }
    }


}
