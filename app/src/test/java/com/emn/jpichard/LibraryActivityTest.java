package com.emn.jpichard;

import org.assertj.android.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import static org.junit.Assert.*;

/**
 * Created by Joris on 20/02/2016.
 */
@RunWith(CustomRoboelectricTestRunner.class)
public class LibraryActivityTest {


    LibraryActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(LibraryActivity.class);
    }

    @Test
    public void testFragmentsAccueil() throws Exception {

        Assertions.assertThat(activity.findViewById(R.id.fragment_books)).isVisible();
        Assertions.assertThat(activity.findViewById(R.id.fragment_details)).isNotVisible();
    }

}