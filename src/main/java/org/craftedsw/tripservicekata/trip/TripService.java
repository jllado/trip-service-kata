package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

	public List<Trip> getTripsByUser(final User user) throws UserNotLoggedInException {
		List<Trip> tripList = new ArrayList<Trip>();
		final User loggedUser = getLoggedUserFromSession();
		boolean isFriend = false;
		if (loggedUser == null) {
			throw new UserNotLoggedInException();
		}
        if (isFriend(user, loggedUser, isFriend)) {
			tripList = findTripsByUser(user);
		}
		return tripList;
	}

    private boolean isFriend(User user, User loggedUser, boolean isFriend) {
        for (User friend : user.getFriends()) {
            if (friend.equals(loggedUser)) {
                isFriend = true;
                break;
            }
        }
        return isFriend;
    }

    protected List<Trip> findTripsByUser(final User user) {
		return TripDAO.findTripsByUser(user);
	}

	protected User getLoggedUserFromSession() {
		return UserSession.getInstance().getLoggedUser();
	}

}
