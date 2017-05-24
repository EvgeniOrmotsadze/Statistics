package com.britebill.interview.statistics.calculator;

import java.util.*;
import java.util.stream.Collectors;


public class StatisticsCalculator {
    private int count = 0;

    public Long getTotalNumberOfWords(List<String> data) {
        return (long)data.size();
    }

    public Long getTotalNumberOfUniqueWords(List<String> data) {
        Set<String> uniqueWords = new LinkedHashSet<>();
        data.stream().forEach(s -> uniqueWords.add(s));
        return (long) uniqueWords.size();
    }


    public Long getAverageCharactersPerWord(List<String> data) {
        data.forEach(s-> count+= s.length());
        return (long)count/data.size();
    }

    public String getMostRepeatedWord(List<String> data) {
        Map<String, Long> occurrences = data.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        long max = -1;
        String mostRepeated = "";
        for(Map.Entry<String,Long> entry : occurrences.entrySet()){
            if(entry.getValue() > max ){
                mostRepeated = entry.getKey();
                max = entry.getValue();
            }
        }
        return mostRepeated;
    }

}
