package com.xafero.nice.rights;

import java.util.List;

public interface IGroup extends INameable{
    
    List<IUser> getUsers();
}
