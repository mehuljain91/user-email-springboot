package com.example.useremail.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.useremail.model.Email;

/**
 *
 * @author mehul jain
 */
@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

    Optional<Email> emailByUser(Integer userid);
}
