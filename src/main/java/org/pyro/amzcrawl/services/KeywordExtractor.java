package org.pyro.amzcrawl.services;

import java.util.*;

/**
 * Created by alex on 19.12.2017.
 */
public class KeywordExtractor {
    private final String text;
    private HashMap<String, Integer> uniqueWords = new HashMap<>();
    private HashMap<String, Integer> uniqueTwoWords = new HashMap<>();
    private HashMap<String, Integer> uniqueThreeWords = new HashMap<>();
    private HashMap<String, Integer> uniqueFourWords = new HashMap<>();

    public KeywordExtractor(String text) {
        this.text = text;
    }

    public void getKeywords() {
        String[] words = this.text.split(" ");
        List<String> validWords = new ArrayList<String>();
        for(int i = 0; i<words.length; i++) {
            if(words[i].length() < 3) {
                validWords.add(words[i]);
            }
        }

        for(int i = 0; i<validWords.size(); i++) {
            this.uniqueWords.putIfAbsent(validWords.get(i), 0);
            this.uniqueWords.put(validWords.get(i), this.uniqueWords.get(validWords.get(i))+1);

            String twoWords = "";
            String threeWords = "";
            String fourWords = "";

            if(i+1 < validWords.size()) {
                twoWords = validWords.get(i) + " " + validWords.get(i + 1);
            }

            if(i+2 < validWords.size()) {
                threeWords = twoWords + validWords.get(i+2);
            }

            if(i+3 < validWords.size()) {
                fourWords = threeWords + " " + validWords.get(i + 3);
            }

            System.out.println(fourWords);

        }

    }

}
