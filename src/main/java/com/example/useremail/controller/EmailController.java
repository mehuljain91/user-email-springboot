package com.example.useremail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import com.example.useremail.model.Email;
import com.example.useremail.repository.EmailRepository;

/**
 *
 * @author mehul jain
 */
@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private EmailRepository emailRepository;

    @PostMapping("/email")
    public ResponseEntity<Email> saveEmail(@RequestBody Email email) {

        try {
            return new ResponseEntity<>(emailRepository.save(email), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/emails/count?user={userID}")
    public ResponseEntity<Email> getNumberOfEmail(@RequestParam Integer id) {

        Optional<Email> email = emailRepository.emailByUser(id);

        if (email.isPresent()) {
            return new ResponseEntity<>(email.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
