package org.dhis2.data_element_rest.controllers.v1;

import org.dhis2.data_element_rest.domain.User;
import org.dhis2.data_element_rest.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController
{
    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("users")
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("users/{username}")
    public User getUserByName(@PathVariable String username)
    {
        return userService.getUserByName(username);
    }
}