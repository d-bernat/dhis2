package org.dhis2.data_element_rest.configuration;

import lombok.extern.slf4j.Slf4j;
import org.dhis2.data_element_rest.domain.User;
import org.dhis2.data_element_rest.repositories.UserRepository;
import org.dhis2.data_element_rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

    private final AuthenticationEntryPoint authEntryPoint;
    private final UserService userService;
    private final UserRepository userRepository;

    public WebSecurityConfig(AuthenticationEntryPoint authEntryPoint, UserService userService, UserRepository userRepository)
    {
        this.authEntryPoint = authEntryPoint;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable();
        http.authorizeRequests().anyRequest().authenticated();
        http.httpBasic().authenticationEntryPoint(authEntryPoint);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, BCryptPasswordEncoder encoder) throws Exception
    {
        addDefaultUsers();
        for (org.dhis2.data_element_rest.domain.User user : userService.getAllUsers())
        {
            String password = user.getPassword();
            String encryptedPassword = encoder.encode(password);
            InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> mngConfig = auth.inMemoryAuthentication();
            UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                                                                                        .password(encryptedPassword)
                                                                                        .roles("USER")
                                                                                        .build();
            mngConfig.withUser(userDetails);
            log.info(user.toString() + " granted");
        }
    }

    private void addDefaultUsers()
    {
        User bugs = new User();
        bugs.setUsername("bugs");
        bugs.setPassword("bunny");
        userRepository.save(bugs);
        User speedy = new User();
        speedy.setUsername("speedy");
        speedy.setPassword("gonzales");
        userRepository.save(speedy);
    }
}