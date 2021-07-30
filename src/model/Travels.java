package model;

import exceptions.TravelsException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Travels {

    private Map<String, Travel> travels;
    String name;
    JSONObject json = new JSONObject();
    JSONArray jsonArray = new JSONArray();

    public Travels() {
        travels = new HashMap<>();
        name = "Travels";
    }

    /* if travels doesn't already contain the given travel, then puts travel in travels hashmap with travel name as key.
    if the travel is a vacation then checks and adds each trip within vacations to travels if not already contained.
    else throws exception
    */
    public void addTravel(Travel t) throws TravelsException
    {
        if (!travels.containsKey(t.getName())) {
            travels.put(t.getName(), t);
            if (t.getClass().equals(Vacation.class)) {
                for (Trip trip : ((Vacation) t).getTrips()) {
                    if (!travels.containsKey(trip.getName())) {
                        travels.put(trip.getName(), trip);
                    }
                }
            }
        } else throw new TravelsException();
    }

    //if travels contains the given travel, removes it from the hashmap else throws exception
    public void removeTravel(Travel t) throws TravelsException {
        if (travels.containsKey(t.getName())) {
            travels.remove(t.getName());
        } else throw new TravelsException();
    }

    public Map<String, Travel> getTravels() {
        return travels;
    }

    public Map getMap() {
        return travels;
    }

    public String getName() {
        return name;
    }

    public JSONObject toJson() {
        json.put("name", name);
        json.put("travels", travelsToJson());
        return json;
    }

    private JSONArray travelsToJson() {
        for (Travel t : travels.values()) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }
}
