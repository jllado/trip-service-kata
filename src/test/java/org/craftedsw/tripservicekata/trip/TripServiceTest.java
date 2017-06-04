package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class TripServiceTest {

    private static final User NOT_LOGGED_IN_USER = null;
    private static final User ANY_USER = new User();

    @Spy
    private TripService service = new TripService();

    @Test(expected = UserNotLoggedInException.class)
    public void throw_an_exception_when_not_logged_in_user() throws Exception {
        doReturn(NOT_LOGGED_IN_USER).when(service).getLoggedUserFromSession();

        service.getTripsByUser(ANY_USER);
    }

}
