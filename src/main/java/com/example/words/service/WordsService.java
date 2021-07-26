package com.example.words.service;

import com.example.words.dto.WordsDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WordsService {

    public WordsDto getCorrectWordsSequence(WordsDto wordsDto) {
        var wordsList = new ArrayList<String>(wordsDto.getWords().size());
        for (var word : wordsDto.getWords()) {
            if (canInsertWordIntoList(wordsList, word)) {
                wordsList.add(word);
            } else {
                break;
            }
        }
        return new WordsDto(wordsList);
    }

    private boolean canInsertWordIntoList(List<String> wordsList, String word) {
        return !isStringNullOrEmpty(word) && (wordsList.isEmpty() ||
                isFirstWordLastCharEqualsSecondWordFirstChar(wordsList.get(wordsList.size() - 1), word));
    }

    private boolean isStringNullOrEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }

    private boolean isFirstWordLastCharEqualsSecondWordFirstChar(String first, String second) {
        return first.charAt(first.length() - 1) == second.charAt(0);
    }

}
