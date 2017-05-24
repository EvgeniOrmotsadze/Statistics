package com.britebill.interview.statistics.calculator;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class StatisticsCalculator {

    public Long getTotalNumberOfWords(List<String> data) {
        return (long)data.size();
    }

    public Long getTotalNumberOfUniqueWords(List<String> data) {
        Set<String> uniqueWords = new LinkedHashSet<>();
        data.stream().forEach(s-> uniqueWords.add(s));
        return (long) uniqueWords.size();
    }

    public Long getAverageCharactersPerWord(List<String> data) {

        data.stream().forEach(s->s.length());
        return null;
    }

    public String getMostRepeatedWord(List<String> data) {
        // TODO Add the implementation to get the most repeated word here
        return null;
    }

}
