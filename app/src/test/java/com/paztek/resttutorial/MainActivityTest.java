package com.paztek.resttutorial;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.fest.assertions.api.ANDROID.assertThat;

/**
 * @author matthieu
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {

    private static final String TAG = MainActivityTest.class.getSimpleName();

    private MainActivity activity;

    @Before
    public void setup() {
        activity = Robolectric.buildActivity(MainActivity.class).create().get();
    }

    @Test
    public void activityAppearsAsExpectedInitially() {
        assertThat(activity.helloWorldTextView).hasText(R.string.hello_world);
    }
}
