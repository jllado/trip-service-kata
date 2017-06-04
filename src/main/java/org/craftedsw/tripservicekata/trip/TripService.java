package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    private static final ArrayList<Trip> ZERO_TRIPS = new ArrayList<Trip>();

    public List<Trip> getTripsByUser(final User user) throws UserNotLoggedInException {
        final User loggedUser = getLoggedUserFromSession();
		if (isNotLoggedIn(loggedUser)) {
			throw new UserNotLoggedInException();
		}
        if (user.isFriend(loggedUser)) {
			return findTripsByUser(user);
		}
		return ZERO_TRIPS;
	}

    private boolean isNotLoggedIn(User loggedUser) {
        return loggedUser == null;
    }

    protected List<Trip> findTripsByUser(final User user) {
		return TripDAO.findTripsByUser(user);
	}

	protected User getLoggedUserFromSession() {
		return UserSession.getInstance().getLoggedUser();
	}

}
