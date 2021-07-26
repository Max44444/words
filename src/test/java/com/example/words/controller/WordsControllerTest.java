package com.example.words.controller;

import com.example.words.dto.WordsDto;
import com.example.words.service.WordsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.StringJoiner;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@EnableWebMvc
@WebMvcTest(WordsController.class)
class WordsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WordsService wordsService;

    @Test
    void whenCheckWords_thenReturnValidResponse() throws Exception {
        var words = Arrays.asList("fish", "horse", "egg", "goose", "eagle");
        when(wordsService.getCorrectWordsSequence(any(WordsDto.class))).thenReturn(new WordsDto(words));

        var stringJoiner = new StringJoiner("\",\"", "{ \"words\": [ \"", "\" ]}");
        words.forEach(stringJoiner::add);
        var requestBody = stringJoiner.toString();

        this.mockMvc
                .perform(post("/check")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.words").isArray())
                .andExpect(jsonPath("$.words", hasSize(words.size())));
    }
}