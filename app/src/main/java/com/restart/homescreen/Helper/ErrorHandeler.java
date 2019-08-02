package com.restart.homescreen.Helper;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.restart.homescreen.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by Ahmed El Genedy on 02-Aug-19.
 * Copyright (C) 2019 SuperGenedy. supergenedy@gmail.com All Rights Reserved.
 */
public class ErrorHandeler {

    private Context context;

    public void handel(Context context, VolleyError error) {
        this.context = context;
        if (error != null) {
            Log.e("ResponseError", "" + error);
            Log.e("ErrorMessage", "" + error.getMessage());

            if (error.networkResponse != null) {
                Log.e("ErrorNetworkRes", "" + error.networkResponse);
                Log.e("ErrorCodeRes", "" + error.networkResponse.statusCode);

                NetworkResponse response = error.networkResponse;

                try {
                    JSONObject errorObj = new JSONObject(new String(response.data));

                    if (response.statusCode == 400 || response.statusCode == 405 || response.statusCode == 500 || response.statusCode == 401) {
                        try {
                            if (errorObj.optString("message") == null
                                    || errorObj.optString("message").isEmpty()
                                    || errorObj.optString("message").equalsIgnoreCase("null")) {
                                displayMessage(context.getString(R.string.something_went_wrong));
                            } else {
                                displayMessage(errorObj.optString("message"));
                            }
                        } catch (Exception e) {
                            displayMessage(context.getString(R.string.something_went_wrong));
                        }
                    } else if (response.statusCode == 422) {
                        String json = trimMessage(new String(response.data));
                        if (json != null && !json.equals("")) {
                            displayMessage(json);
                        } else {
                            displayMessage(context.getString(R.string.please_try_again));
                        }

                    } else {
                        displayMessage(context.getString(R.string.please_try_again));
                    }

                } catch (Exception e) {
                    displayMessage(context.getString(R.string.something_went_wrong));
                }
            } else {
                displayMessage(context.getString(R.string.please_check_connection));
            }
        } else {
            displayMessage(context.getString(R.string.something_went_wrong));
        }
    }

    private void displayMessage(String message) {
        new AlertHelper().show(context, "Oops!", message).show();
    }


    private static String trimMessage(String json) {
        String trimmedString = "";

        try {
            JSONObject jsonObject = new JSONObject(json).getJSONObject("errors");
            Iterator<String> iter = jsonObject.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                try {
                    JSONArray value = jsonObject.getJSONArray(key);
                    for (int i = 0, size = value.length(); i < size; i++) {
                        Log.e("Errors in Form", "" + value.getString(i));
                        trimmedString += value.getString(i);
                        if (i < size - 1) {
                            trimmedString += '\n';
                        }
                    }
                } catch (JSONException e) {
                    trimmedString += jsonObject.optString(key);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        Log.e("Trimmed", "" + trimmedString);

        return trimmedString;
    }
}

