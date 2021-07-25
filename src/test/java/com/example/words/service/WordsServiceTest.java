package com.example.words.service;

import com.example.words.dto.WordsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class WordsServiceTest {

    private WordsService wordsService;

    @BeforeEach
    void setUp() {
        wordsService = new WordsService();
    }

    @Test
    void whenCallWithCorrectWordSequence_thenReturnFullList() {
        var words = Arrays.asList("fish", "horse", "egg", "goose", "eagle");
        var result = wordsService.getCorrectWordsSequence(new WordsDto(words));

        assertEquals(words, result.getWords());
    }

    @Test
    void whenCallWithOneIncorrectWord_thenReturnFilteredList() {
        var words = Arrays.asList("fish", "horse", "snake", "goose", "eagle");
        var expectedResult = words.subList(0, 2);

        var result = wordsService.getCorrectWordsSequence(new WordsDto(words));

        assertEquals(expectedResult, result.getWords());
    }

    @Test
    void whenCallWithEmptyString_thenReturnWordsBeforeEmptyString() {
        var words = Arrays.asList("fish", "horse", "", "goose", "eagle");
        var expectedResult = words.subList(0, 2);

        var result = wordsService.getCorrectWordsSequence(new WordsDto(words));

        assertEquals(expectedResult, result.getWords());
    }

    @Test
    void whenStartsWithEmptyString_thenReturnEmptyWordList() {
        var words = Arrays.asList("", "horse", "", "goose", "eagle");
        var result = wordsService.getCorrectWordsSequence(new WordsDto(words));

        assertEquals(Collections.emptyList(), result.getWords());
    }
}