package com.theelitedevelopers.paystackwebview.utils;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

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

}
