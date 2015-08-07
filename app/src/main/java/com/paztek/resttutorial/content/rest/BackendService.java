package com.paztek.resttutorial.content.rest;

import retrofit.RestAdapter;

/**
 * @author matthieu
 *
 * "Factory" for our Retrofit backend service
 */
public class BackendService {

    private static final String TAG = BackendService.class.getSimpleName();

    private static final String API_URL = "http://api.ourbackend.cool";

    public static Backend getService() {
        return new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .build()
                .create(Backend.class);
    }
}
