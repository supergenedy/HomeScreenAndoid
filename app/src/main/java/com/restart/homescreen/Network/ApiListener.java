package com.restart.homescreen.Network;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by Ahmed El Genedy on 02-Aug-19.
 * Copyright (C) 2019 SuperGenedy. supergenedy@gmail.com All Rights Reserved.
 */
public class ApiListener {
    public interface ResponseObjectListener {
        void onSuccessObjectResponse(JSONObject response);

        void onErrorResponse(VolleyError error);
    }
}
