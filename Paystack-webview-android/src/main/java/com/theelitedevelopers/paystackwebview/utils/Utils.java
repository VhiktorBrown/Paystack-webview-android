package com.theelitedevelopers.paystackwebview.utils;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

// Created by Victor on 12/28/2022.
// Copyright (c) 2022 Elite Developers.All rights reserved.

public class Utils {

    /**
     * Converts the metaData in form a string back into a JSONObject
     * to be passed/sent to Paystack
     * @param jsonString -> metaData in form a String
     * @return -> JSONObject
     */
    public static JSONObject convertFromStringToJson(String jsonString) {
        JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(jsonString);

                System.out.println(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
//        assert jsonObject != null;
//        return flattenMetadata(jsonObject);
        return jsonObject;
    }

    /**
     * Converts our metaData in form of the instance of a class into String to be passed
     * to next Activity through Intent.
     * @param metaData - instance of a Data class
     * @return -> String.
     */
    public static String convertToJsonToString(Object metaData){
        String jsonString = new Gson().toJson(metaData);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonString);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(jsonObject != null){
            return jsonObject.toString();
        }else {
            return "";
        }
    }

    public static JSONObject flattenMetadata(JSONObject jsonObject) {
        try {
            JSONObject metadata = new JSONObject();
            JSONObject nameValuePairs = jsonObject.getJSONObject("nameValuePairs");
            //JSONObject nameValuePairs = metadata.optJSONObject("nameValuePairs");

            for (Iterator<String> iterator = nameValuePairs.keys(); iterator.hasNext(); ) {
                String key = iterator.next();
                String value = nameValuePairs.getString(key);
                metadata.put(key, value);
            }
            metadata.remove("nameValuePairs");

            jsonObject.put("metadata", metadata);
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
