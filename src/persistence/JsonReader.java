package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    //constructs reader to read from source file:
    public JsonReader(String source) {
        this.source = source;
    }

    //reads and returns Travels from file and throws IOException if reading error occurs
    public Travels read() throws IOException, TravelsException {
        String data = readSource(source);
        JSONObject object = new JSONObject(data);
        return parseTravels(object);
    }

    //reads and returns source file as string
    private String readSource(String source) throws IOException {
        StringBuilder builder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s));
        }
        return builder.toString();
    }

    //parses Travels from JSON object and returns the object
    private Travels parseTravels(JSONObject object) throws TravelsException {
        String name = object.getString("name");
        Travels t = new Travels();
        addTravels(t, object);
        return t;
    }

    //parses travels from JSON object and adds them to Travels
    private void addTravels(Travels t, JSONObject object) throws TravelsException {
        JSONArray array = object.getJSONArray("travels");
        for (Object json: array) {
            JSONObject nextTravel = (JSONObject) json;
            addTravel(t, nextTravel);
        }
    }

    //parses travel from JSON object and adds it to Travels
    private void addTravel(Travels t, JSONObject object) throws TravelsException {
        String name = object.getString("name");
        String notes = object.getString("notes");
        int day = object.getInt("day");
        int month = object.getInt("month");
        int year = object.getInt("year");
        String subclass = object.getString("subclass");
        Date date = new Date(day, month, year);

        switch (subclass) {
            case "Vacation":
                addVacation(name, notes, date, object, t);
                break;
            case "DayTrip":
                addDayTrip(name, notes, date, object, t);
                break;
            case "Hike":
                addHike(name, notes, date, object, t);
                break;
            case "OvernightTrip":
                addOvernightTrip(name, notes, date, object, t);
                break;
        }

    }

    private void addOvernightTrip(String name, String notes, Date date, JSONObject object, Travels t) throws TravelsException {
        int rating = object.getInt("rating");
        int distance = object.getInt("distance");
        int days = object.getInt("days");
        int cost = object.getInt("cost");
        String stay = object.getString("stay");
        Travel overnight = new OvernightTrip(name, rating, notes, distance, date, days, cost, stay);
        t.addTravel(overnight);

    }

    private void addHike(String name, String notes, Date date, JSONObject object, Travels t) throws TravelsException {
        int rating = object.getInt("rating");
        int distance = object.getInt("distance");
        int difficulty = object.getInt("difficulty");
        Travel hike = new Hike(name, rating, notes, distance, date, difficulty);
        t.addTravel(hike);
    }

    private void addDayTrip(String name, String notes, Date date, JSONObject object, Travels t) throws TravelsException {
        int rating = object.getInt("rating");
        int distance = object.getInt("rating");
        String type = object.getString("type");
        int dollars = object.getInt("dollars");
        Travel day = new DayTrip(name, rating, notes, distance, date, type, dollars);
        t.addTravel(day);
    }

    private void addVacation(String name, String notes, Date date, JSONObject object, Travels t) throws TravelsException {
        int duration = object.getInt("duration");
        int cost = object.getInt("cost");
        Travel vacation = new Vacation(name, duration, cost, notes, date);
        t.addTravel(vacation);
    }

}
