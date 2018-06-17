package com.turini.domain.geographic;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Coordinate {

	@SerializedName("x")
	private final Integer latitude;

	@SerializedName("y")
	private final Integer longitude;

	public Coordinate(int latitude, int longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Integer getLatitude() {
		return latitude;
	}

	public Integer getLongitude() {
		return longitude;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Coordinate that = (Coordinate) o;
		return Objects.equals(latitude, that.latitude) &&
				Objects.equals(longitude, that.longitude);
	}

	@Override
	public int hashCode() {
		return Objects.hash(latitude, longitude);
	}

	@Override
	public String toString() {
		return "Coordinate{" +
			"latitude=" + latitude +
			", longitude=" + longitude +
			'}';
	}
}