package com.example.words.controller;

import com.example.words.dto.WordsDto;
import com.example.words.service.WordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/words")
public class WordsController {

    @Autowired
    private WordsService wordsService;

    @PostMapping("/check")
    public WordsDto checkWords(@RequestBody WordsDto wordsDto) {
        return wordsService.getCorrectWordsSequence(wordsDto);
    }

}
