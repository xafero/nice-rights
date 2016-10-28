package com.xafero.nice.rights.example;

import com.xafero.nice.rights.api.IId;

public class Game implements IId<String> {

	private final String id;

	public Game(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}
}
