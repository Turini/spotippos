package com.turini.domain.geographic;

public class Geographic {

	public boolean contains(Coordinate coordinate, Boundaries boundaries) {

		var latitude = coordinate.getLatitude();
		var longitude = coordinate.getLongitude();

		return boundaries.getUpperLeftLatitude() <= latitude &&
			latitude <= boundaries.getBottomRightLatitude() &&
			boundaries.getUpperLeftLongitude() >= longitude &&
			longitude >= boundaries.getBottomRightLongitude();
	}
}