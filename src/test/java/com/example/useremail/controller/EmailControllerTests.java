package com.example.useremail.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.useremail.model.Email;
import com.example.useremail.repository.EmailRepository;

/**
 *
 * @author mehul jain
 */
@WebMvcTest(EmailController.class)
public class EmailControllerTests {

    @MockBean
    private EmailRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateEmail() throws Exception {
        Email email = new Email(1, "from", "to", "subject", "body");

        mockMvc.perform(post("/api/user").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(email)))
                .andExpect(status().isCreated())
                .andDo(print());
    }
}
