package com.microsoft.utilities;

import java.util.List;

public class MatchResult {

    private String word;
    private long complexity;
    private List<String> combinations;
    private List<String> englishWords;

    public MatchResult(String word, List<String> combinations, List<String> englishWords, long complexity)
    {
        this.setWord(word);
        this.combinations = combinations;
        this.englishWords = englishWords;
        this.complexity = complexity;
    }

    public long getComplexity() {
        return complexity;
    }

    public void setComplexity(long complexity) {
        this.complexity = complexity;
    }

    public List<String> getCombinations() {
        return combinations;
    }

    public void setCombinations(List<String> combinations) {
        this.combinations = combinations;
    }

    public List<String> getEnglishWords() {
        return englishWords;
    }

    public void setEnglishWords(List<String> englishWords) {
        this.englishWords = englishWords;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
