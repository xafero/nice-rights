package com.xafero.nice.rights.example;

import com.xafero.nice.rights.api.IId;
import com.xafero.nice.rights.api.IPrefix;

public enum Permission implements IId<String>, IPrefix {

	Views, Plays;

	@Override
	public String getId() {
		return name().toLowerCase();
	}

	@Override
	public String getPrefix() {
		return "Perm";
	}
}
