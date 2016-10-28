package com.xafero.nice.rights.api;

import java.io.Closeable;

public interface IExaminer extends Closeable {

	boolean isAllowed(IId<?> subject, IId<?> action, IId<?> object);

}
