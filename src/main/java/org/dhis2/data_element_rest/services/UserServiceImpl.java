package org.dhis2.data_element_rest.services;

import org.dhis2.data_element_rest.domain.User;
import org.dhis2.data_element_rest.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public User getUserByName(String username)
    {
        return userRepository.findByUsername(username);
    }
}
