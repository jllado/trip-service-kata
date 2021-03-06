package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.trip.Trip;

import java.util.ArrayList;
import java.util.List;

public class User {

	private final List<Trip> trips = new ArrayList<>();
	private final List<User> friends = new ArrayList<>();
	
	public List<User> getFriends() {
		return friends;
	}
	
	public void addFriend(final User user) {
		friends.add(user);
	}

	public void addTrip(final Trip trip) {
		trips.add(trip);
	}
	
	public List<Trip> trips() {
		return trips;
	}

	public boolean isFriendOf(final User user) {
		return getFriends().contains(user);
	}

	public boolean isNotFriendOf(final User user) {
		return !isFriendOf(user);
	}

}
