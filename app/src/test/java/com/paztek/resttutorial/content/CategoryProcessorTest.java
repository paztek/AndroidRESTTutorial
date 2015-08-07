package com.paztek.resttutorial.content;

import com.paztek.resttutorial.BuildConfig;
import com.paztek.resttutorial.content.error.ProcessingError;
import com.paztek.resttutorial.content.model.Category;
import com.paztek.resttutorial.content.rest.Backend;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

/**
 * @author matthieu
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class CategoryProcessorTest {

    private static final String TAG = CategoryProcessorTest.class.getSimpleName();

    private CategoryProcessor processor;

    @Mock
    Backend backend;

    List<Category> categories;

    @Mock
    private CategoryProcessor.CategoryProcessorCallback callback;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        categories = new ArrayList<>();
        Category c1 = new Category("Category 1");
        categories.add(c1);
        Category c2 = new Category("Category 2");
        categories.add(c2);

        processor = new CategoryProcessor(backend);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void onSuccessCallbackIsCalledWhenCategoriesAreCorrectlyFetched() {
        // Make the backend respond with the list of categories
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Callback<List<Category>> backendCallback = (Callback<List<Category>>)args[0];
                backendCallback.success(categories, null);
                return null;
            }
        }).when(backend).getCategories((Callback<List<Category>>)anyObject());

        processor.getCategories(callback);
        verify(callback, times(1)).onSuccess(categories);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void onErrorCallbackIsCalledWhenCategoriesCantBeFetched() {
        // Make the backend respond with an error
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Callback<List<Category>> backendCallback = (Callback<List<Category>>)args[0];
                backendCallback.failure(RetrofitError.unexpectedError("Dummy message", new Exception()));
                return null;
            }
        }).when(backend).getCategories((Callback<List<Category>>)anyObject());

        processor.getCategories(callback);
        verify(callback, times(1)).onError((ProcessingError)anyObject());
    }

}
