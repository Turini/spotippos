package com.turini.domain;

import br.com.caelum.vraptor.serialization.SkipSerialization;
import com.google.gson.annotations.SerializedName;
import com.turini.domain.geographic.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.*;

public class Property {

	private Integer id;

	@NotEmpty
	private String title;

	@Min(0)
	@NotNull
	private BigDecimal price;

	@NotEmpty
	private String description;

	@SerializedName(value = "x", alternate = {"lat"})
	@Min(0)
	@Max(1400)
	private Integer latitude;

	@SerializedName(value = "y", alternate = {"long"})
	@Min(0)
	@Max(1000)
	private Integer longitude;

	@Max(5)
	@Min(1)
	private Integer beds;

	@Max(4)
	@Min(1)
	private Integer baths;

	private Set<String> provinces = new HashSet<>();

	@Max(240)
	@Min(20)
	private Integer squareMeters;

	private final transient Geographic geographic;

	public Property(Geographic geographic) {
		this.geographic = geographic;
	}

	public Property() {
		this(null);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public Integer getLatitude() {
		return latitude;
	}

	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
	}

	public Integer getLongitude() {
		return longitude;
	}

	public void setLongitude(Integer longitude) {
		this.longitude = longitude;
	}

	public Integer getBeds() {
		return beds;
	}

	public Integer getBaths() {
		return baths;
	}

	public Integer getSquareMeters() {
		return squareMeters;
	}

	public Set<String> getProvinces() {
		return provinces;
	}

	public Geographic getGeographic() {
		return Optional
				.ofNullable(geographic).orElseGet(Geographic::new);
	}

	public void addProvince(String province) {
		provinces.add(province);
	}

	public boolean isBetween(Coordinate coordinateA, Coordinate coordinateB) {
		return getGeographic().contains(
			getCoordinate(),
			new Boundaries(coordinateA, coordinateB));
	}

	public Coordinate getCoordinate() {
		return new Coordinate(latitude, longitude);
	}

	@Override
	public String toString() {
		return "Property{" +
			"id='" + id + '\'' +
			", title='" + title + '\'' +
			", price='" + price + '\'' +
			", description='" + description + '\'' +
			", latitude='" + latitude + '\'' +
			", longitude='" + longitude + '\'' +
			", beds='" + beds + '\'' +
			", baths='" + baths + '\'' +
			", provinces='" + provinces + '\'' +
			", squareMeters='" + squareMeters + '\'' +
		'}';
	}
}