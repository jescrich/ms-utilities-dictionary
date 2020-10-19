package com.microsoft.utilities;

import com.microsoft.utilities.matchers.PermutationMatcherStrategy;
import com.microsoft.utilities.matchers.MatcherStrategy;

public class StringMatcher {

    private MatcherStrategy strategy;
    private Dictionary dictionary;

    /**
     * @param dictionary
     */
    public StringMatcher(Dictionary dictionary) {
        Assert.notNull(dictionary, "dictionary");
        this.dictionary = dictionary;
    }

    /**
     *
     * @param dictionary Dictionary
     * @param strategy Matcher Strategy
     */
    public StringMatcher(Dictionary dictionary, MatcherStrategy strategy) {
        Assert.notNull(dictionary, "dictionary");
        Assert.notNull(strategy, "strategy");
        this.dictionary = dictionary;
        this.setStrategy(strategy);
    }

    /**
     * Match all words for a given word and his combinations using a given dictionary and the strategy based on complexity.
     * @param word to traverse.
     * @return all matched results.
     */
    public MatchResult matchAllWords(String word) {

        if (getStrategy() == null) {
            setStrategy(new PermutationMatcherStrategy());
        }

        return getStrategy().match(dictionary, word);
    }

    /**
     * Match all words for a given word and his combinations using a given dictionary and the strategy based on complexity.
     * @param dictionary dictionary
     * @param word word to traverse
     * @return all matched results.
     */
    public static MatchResult matchAllWords(Dictionary dictionary, String word) {
        return new StringMatcher(dictionary).matchAllWords(word);
    }

    public MatcherStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(MatcherStrategy strategy) {
        this.strategy = strategy;
    }
}
