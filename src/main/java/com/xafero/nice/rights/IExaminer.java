package com.xafero.nice.rights;

public interface IExaminer {

    Answer check(IUser user, IPermission permission);
}
