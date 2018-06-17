package com.turini.domain.geographic;

import java.util.Objects;

public class Boundaries {

	private final Coordinate upperLeft;
	private final Coordinate bottomRight;

	public Boundaries(Coordinate upperLeft, Coordinate bottomRight) {
		this.upperLeft = upperLeft;
		this.bottomRight = bottomRight;
	}

	public Coordinate getUpperLeft() {
		return upperLeft;
	}

	public Coordinate getBottomRight() {
		return bottomRight;
	}

	public Integer getUpperLeftLatitude() {
		return getUpperLeft().getLatitude();
	}

	public Integer getUpperLeftLongitude() {
		return getUpperLeft().getLongitude();
	}

	public Integer getBottomRightLatitude() {
		return getBottomRight().getLatitude();
	}

	public Integer getBottomRightLongitude() {
		return getBottomRight().getLongitude();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Boundaries that = (Boundaries) o;
		return Objects.equals(upperLeft, that.upperLeft) &&
				Objects.equals(bottomRight, that.bottomRight);
	}

	@Override
	public int hashCode() {
		return Objects.hash(upperLeft, bottomRight);
	}

	@Override
	public String toString() {
		return "Boundaries{" +
			"upperLeft=" + upperLeft +
			", bottomRight=" + bottomRight +
			'}';
	}
}