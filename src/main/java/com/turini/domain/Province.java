package com.turini.domain;

import br.com.caelum.vraptor.serialization.SkipSerialization;
import com.turini.domain.geographic.*;

import java.util.Optional;

public class Province {

	private String name;

	@SkipSerialization
	private Boundaries boundaries;

	private final transient Geographic geographic;

	public Province(Geographic geographic) {
		this.geographic = geographic;
	}

	public Province() {
		this(null);
	}

	public Boundaries getBoundaries() {
		return boundaries;
	}

	public Geographic getGeographic() {
		return Optional
			.ofNullable(geographic).orElseGet(Geographic::new);
	}

	public void setBoundaries(Boundaries boundaries) {
		this.boundaries = boundaries;
	}

	public boolean contains(Coordinate coordinate) {
		return getGeographic().contains(coordinate, boundaries);
	}
}