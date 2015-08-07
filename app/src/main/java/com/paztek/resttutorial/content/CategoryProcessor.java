package com.paztek.resttutorial.content;

import com.paztek.resttutorial.content.error.ProcessingError;
import com.paztek.resttutorial.content.model.Category;
import com.paztek.resttutorial.content.rest.Backend;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author matthieu
 *
 * Handle the synchronization between remote and local data.
 */
public class CategoryProcessor {

    private static final String TAG = CategoryProcessor.class.getSimpleName();

    protected Backend backend;

    protected final static String CATEGORIES_CONNECTION_ERR_MSG = "There has been a connection error while retrieving the categories";

    public CategoryProcessor(Backend backend) {
        this.backend = backend;
    }

    public void getCategories(final CategoryProcessorCallback callback) {
        Callback<List<Category>> backendCallback = new Callback<List<Category>>() {
            @Override
            public void success(List<Category> categories, Response response) {
                callback.onSuccess(categories);
            }

            @Override
            public void failure(RetrofitError error) {
                callback.onError(ProcessingError.networkError(CATEGORIES_CONNECTION_ERR_MSG, error));
            }
        };

        backend.getCategories(backendCallback);
    }

    public interface CategoryProcessorCallback {
        void onSuccess(List<Category> categories);
        void onError(ProcessingError error);
    }
}
