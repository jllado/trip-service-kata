package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    private static final ArrayList<Trip> ZERO_TRIPS = new ArrayList<Trip>();

    private final TripDAO dao;

    public TripService(TripDAO dao) {
        this.dao = dao;
    }

    public List<Trip> getFriendTripsFrom(final User user) throws UserNotLoggedInException {
        final User loggedUser = getLoggedUserFromSession();
        validateUserIsLoggedIn(loggedUser);
        if (user.isFriend(loggedUser)) {
			return findTripsByUser(user);
		}
		return ZERO_TRIPS;
	}

    private void validateUserIsLoggedIn(User loggedUser) {
        if (isNotLoggedIn(loggedUser)) {
            throw new UserNotLoggedInException();
        }
    }

    private boolean isNotLoggedIn(User loggedUser) {
        return loggedUser == null;
    }

    protected List<Trip> findTripsByUser(final User user) {
		return dao.findTripsBy(user);
	}

	protected User getLoggedUserFromSession() {
		return UserSession.getInstance().getLoggedUser();
	}

}
