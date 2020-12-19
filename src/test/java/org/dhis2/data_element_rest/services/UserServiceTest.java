package org.dhis2.data_element_rest.services;

import org.dhis2.data_element_rest.domain.User;
import org.dhis2.data_element_rest.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserServiceTest
{

    public static final long ID = 1L;
    public static final String USERNAME = "bugs";
    private UserService userService;

    @Mock
    UserRepository userRepository;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void should_Return_All_Users_When_Called()
    {
        //arrange
        List<User> users = Arrays.asList( new User(), new User());
        when(userRepository.findAll()).thenReturn(users);

        //act
        List<User> allUsers = userService.getAllUsers();

        //assert
        assertEquals(2, allUsers.size());

    }

    @Test
    public void should_Return_User_By_Name_When_Required()
    {
        //arrange
        User user = new User();
        user.setId(ID);
        user.setUsername(USERNAME);
        when(userRepository.findByUsername(anyString())).thenReturn(user);

        //act
        User fetchedUser = userService.getUserByName(USERNAME);

        //assert
        assertEquals(ID, fetchedUser.getId());
        assertEquals(USERNAME, fetchedUser.getUsername());
    }
}