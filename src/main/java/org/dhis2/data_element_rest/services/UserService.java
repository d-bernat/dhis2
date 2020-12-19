package org.dhis2.data_element_rest.services;


import org.dhis2.data_element_rest.domain.User;

import java.util.List;

public interface UserService
{
    List<User> getAllUsers();
    User getUserByName(String username);
}
