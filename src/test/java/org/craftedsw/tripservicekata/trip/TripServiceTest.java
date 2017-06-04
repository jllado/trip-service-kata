package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class TripServiceTest {

    private static final User NOT_LOGGED_IN_USER = null;
    private static final User LOGGED_IN_USER = new User();
    private static final User ANY_USER = new User();
    private static final User USER_WITHOUT_FRIENDS = new User();

    @Spy
    private TripService service = new TripService();

    @Test(expected = UserNotLoggedInException.class)
    public void throw_an_exception_when_not_logged_in_user() throws Exception {
        doReturn(NOT_LOGGED_IN_USER).when(service).getLoggedUserFromSession();

        service.getTripsByUser(ANY_USER);
    }

    @Test
    public void return_zero_trips_when_user_is_not_friend_of_logged_in_user() throws Exception {
        doReturn(LOGGED_IN_USER).when(service).getLoggedUserFromSession();

        assertThat(service.getTripsByUser(USER_WITHOUT_FRIENDS), hasSize(0));
    }

}
