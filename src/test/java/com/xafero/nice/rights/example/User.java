package com.xafero.nice.rights.example;

import com.xafero.nice.rights.api.IId;

public class User implements IId<String> {

	private final String id;

	public User(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}
}
