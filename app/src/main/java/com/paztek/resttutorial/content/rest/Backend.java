package com.paztek.resttutorial.content.rest;

import com.paztek.resttutorial.content.model.Category;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * @author matthieu
 *
 * Define the available API endpoints on the backend
 */
public interface Backend {

    @GET("/categories")
    void getCategories(Callback<List<Category>> callback);
}
