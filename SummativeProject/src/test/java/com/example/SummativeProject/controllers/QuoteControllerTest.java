package com.example.SummativeProject.controllers;

import com.example.SummativeProject.models.Quote;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuoteController.class)
class QuoteControllerTest {

    // Wiring in the MockMvc object
    @Autowired
    private MockMvc mockMvc;

    // ObjectMapper used to convert Java objects to JSON and vice versa
    private ObjectMapper mapper = new ObjectMapper();

    // A list of records for testing purposes
    private List<Quote> quoteList;

    @Test
    public void shouldReturnRandomQuoteAndDefinition() throws Exception {

        // Arrange
        // Convert Java object to JSON
//        String outputJson = mapper.writeValueAsString(quoteList);

        // Act
        mockMvc.perform(get("/quote"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}