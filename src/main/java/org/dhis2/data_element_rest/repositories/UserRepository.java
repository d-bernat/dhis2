package org.dhis2.data_element_rest.repositories;

import org.dhis2.data_element_rest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findByUsername(String username);
}
