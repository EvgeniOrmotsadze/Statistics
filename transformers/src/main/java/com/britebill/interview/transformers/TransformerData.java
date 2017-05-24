package com.britebill.interview.transformers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransformerData {

    public List<String> transformData (List<String> src) {
        List<String> list = new ArrayList<>();  // first copy list, don't change method variable
        list.addAll(src);
        list.replaceAll(String::toUpperCase); // to uppercase
        return list.stream().distinct().sorted((a, b) -> a.length() - b.length()).collect(Collectors.toList()); 
    }
}
