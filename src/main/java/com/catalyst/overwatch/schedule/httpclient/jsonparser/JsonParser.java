package com.catalyst.overwatch.schedule.httpclient.jsonparser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.catalyst.overwatch.schedule.httpclient.JsonConstants.JSON_LIST_RESPONSE_NAME;

public class JsonParser implements IJsonParser {

    public JsonParser() {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public <T> T parseObject(String jsonBody, Class<T> type) {
        //check if it's an array
        if (jsonBody.charAt(0) == '[') {
            JSONArray json = new JSONArray(jsonBody);

            return fromJson(json.toString(), type);
        } else {
            try {
                JSONObject json = new JSONObject(jsonBody);
                return fromJson(json.toString(), type);
            } catch (JSONException e) {
                return null;
            }
        }
    }

    @Override
    public <T> List<T> parseList(String jsonBody, Class<T> type) {
        List<T> list = new ArrayList<>();

        if (jsonBody.charAt(0) == '[') {
            JSONArray json = new JSONArray(jsonBody);
            for (int i = 0; i < json.length(); i++) {
                list.add(fromJson(json.get(i).toString(), type));
            }

            return list;
        } else {
            JSONObject json = (new JSONObject(jsonBody)).getJSONObject(JSON_LIST_RESPONSE_NAME);
            //grab the name of the array since the _embedded object only has one key
            JSONArray objects = json.getJSONArray(json.keys().next());

            objects.iterator().forEachRemaining((jsonObj) -> {
                list.add(fromJson(jsonObj.toString(), type));
            });
        }

        return list;
    }
}
