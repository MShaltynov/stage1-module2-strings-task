package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> words = new ArrayList<>();
        for (String delimiter : delimiters) {
           source= source.replaceAll(delimiter, " ");
        }
        StringTokenizer stringTokenizer = new StringTokenizer(source, " ");
        while (stringTokenizer.hasMoreTokens()){
            String token= stringTokenizer.nextToken();
            words.add(token);
        }
        return words;
    }
}
