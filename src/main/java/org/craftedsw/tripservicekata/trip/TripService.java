package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    private static final ArrayList<Trip> ZERO_TRIPS = new ArrayList<>();

    private final TripDAO dao;

    public TripService(final TripDAO dao) {
        this.dao = dao;
    }

    public List<Trip> getFriendTripsFrom(final User user, final User userFromSession) {
        validateUserIsLoggedIn(userFromSession);
        if (user.isNotFriendOf(userFromSession)) {
            return ZERO_TRIPS;
        }
        return findTripsBy(user);
	}

    private void validateUserIsLoggedIn(final User loggedUser) {
        if (isNotLoggedIn(loggedUser)) {
            throw new UserNotLoggedInException();
        }
    }

    private boolean isNotLoggedIn(final User loggedUser) {
        return loggedUser == null;
    }

    protected List<Trip> findTripsBy(final User user) {
		return dao.findTripsBy(user);
	}

}
