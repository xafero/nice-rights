package com.xafero.nice.rights.util;

import com.xafero.nice.rights.api.IId;
import com.xafero.nice.rights.api.IPrefix;

public class AnyId<T> implements IId<T>, IPrefix {

	public static enum IdMode {
		Raw, ToString, Lower, Upper
	};

	public static <I> AnyId<I> iid(I object) {
		return iid(object, null);
	}

	public static <I> AnyId<I> iid(I object, String prefix) {
		return iid(object, prefix, null);
	}

	public static <I> AnyId<I> iid(I object, String prefix, IdMode mode) {
		return new AnyId<I>(object, prefix, mode);
	}

	private final T object;
	private final String prefix;
	private final IdMode mode;

	private AnyId(T object, String prefix, IdMode mode) {
		this.object = object;
		this.prefix = prefix == null ? object.getClass().getSimpleName() : prefix;
		this.mode = mode == null ? IdMode.Raw : mode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getId() {
		switch (mode) {
		case ToString:
			return (T) object.toString();
		case Lower:
			return (T) object.toString().toLowerCase();
		case Upper:
			return (T) object.toString().toUpperCase();
		default:
			return object;
		}
	}

	@Override
	public String getPrefix() {
		return prefix;
	}
}
