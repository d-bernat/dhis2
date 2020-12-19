package org.dhis2.data_element_rest.bootstrap;
import lombok.extern.slf4j.Slf4j;
import org.dhis2.data_element_rest.domain.User;
import org.dhis2.data_element_rest.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner
{
    private final UserRepository userRepository;

    public Bootstrap(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception
    {
        User bugs = new User();
        bugs.setUsername("bugs");
        bugs.setPassword("bunny");
        userRepository.save(bugs);
        log.info("User saved: " + bugs.toString());
        User speedy = new User();
        speedy.setUsername("speedy");
        speedy.setPassword("gonzales");
        userRepository.save(speedy);
        log.info("User saved: " + speedy.toString());
    }
}
