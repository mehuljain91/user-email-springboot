package com.example.useremail.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.useremail.model.User;
import com.example.useremail.repository.UserRepository;

/**
 *
 * @author mehul jain
 */
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateUser() throws Exception {
        User user = new User(1, "ramesh", "gupta", "rameshgupta@email.com", "9324778150");

        mockMvc.perform(post("/api/user").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void shouldReturnUser() throws Exception {
        long id = 1L;
        User user = new User(1, "ramesh", "gupta", "rameshgupta@email.com", "9324778150");

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        mockMvc.perform(get("/api/user/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.firstname").value(user.getFirstname()))
                .andExpect(jsonPath("$.lastname").value(user.getLastname()))
                .andExpect(jsonPath("$.emailId").value(user.getEmailId()))
                .andExpect(jsonPath("$.phoneNumber").value(user.getPhoneNumber()))
                .andDo(print());
    }

    @Test
    void shouldReturnNotFoundUser() throws Exception {
        long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/user/{id}", id))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void shouldReturnListOfUsers() throws Exception {
        List<User> users = new ArrayList<>(
                Arrays.asList(new User(1, "ramesh", "gupta", "rameshgupta@email.com", "9823688150"),
                        new User(2, "suraj", "kumar", "surajkumar@email.com", "9324778320"),
                        new User(3, "samuel", "mac", "samuelmac@email.com", "8307122485")));

        when(userRepository.findAll()).thenReturn(users);
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(users.size()))
                .andDo(print());
    }
}
