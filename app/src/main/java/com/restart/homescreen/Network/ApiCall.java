package com.restart.homescreen.Network;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.restart.homescreen.Network.ApiListener.ResponseObjectListener;
import com.restart.homescreen.Restart;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ahmed El Genedy on 02-Aug-19.
 * Copyright (C) 2019 SuperGenedy. supergenedy@gmail.com All Rights Reserved.
 */
public class ApiCall {

    public static void getHomeData(ResponseObjectListener responseListener) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URLConstants.HOME, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                responseListener.onSuccessObjectResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseListener.onErrorResponse(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("X-Requested-With", "XMLHttpRequest");
                return headers;
            }
        };
        Restart.getInstance().addToRequestQueue(jsonObjectRequest);
    }

}
