package com.example.useremail.repository;

import com.example.useremail.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mehul jain
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
