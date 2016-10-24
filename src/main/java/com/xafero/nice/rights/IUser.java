package com.xafero.nice.rights;

import java.util.List;

public interface IUser extends INameable {
    
    List<IGroup> getGroups();
    
}
