package com.xafero.nice.rights;

import java.util.List;

public interface IRole extends INameable {

    List<IPermission> getPermissions();
}
