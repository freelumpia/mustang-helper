package com.cpe.musty.intent.availability;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.cpe.musty.utility.JsonReader;
import com.google.common.collect.ImmutableList;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FloorChecker {

    private static final String STATUS_KEY = "status";

    public long findAvailableComputers(final String floorUtterance) {
        final String endpoint = getEndpointForUtterance(floorUtterance);
        final JSONObject floorJson = getJsonFromEndpoint(endpoint);
        final List<JSONObject> computers = convertToComputerList(floorJson);

        return computers.stream()
                .filter(o -> o.getInt(STATUS_KEY) == 0).count();
    }

    private String getEndpointForUtterance(final String utterance) {
        // For now, just return the first floor endpoint.
        // In the long run, we should be parsing the utterance and mapping it to a floor
        // i.e. "one", "first", etc. all map to the below endpoint
        return "http://lib.calpoly.edu/api/availability/1st";
    }

    private JSONObject getJsonFromEndpoint(final String endpoint) {
        try {
            return JsonReader.readJsonFromUrl(endpoint);
        } catch (Exception e) {
            throw new RuntimeException("Invalid endpoint: " + endpoint, e);
        }
    }

    private List<JSONObject> convertToComputerList(final JSONObject floorMap) {
        return floorMap.toMap().values().stream()
                .map(o -> new JSONObject((Map) o))
                .filter(o -> o.has(STATUS_KEY))
                .collect(ImmutableList.toImmutableList());
    }
}
