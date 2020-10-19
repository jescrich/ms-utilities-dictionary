package com.microsoft.utilities;

import org.apache.commons.math.util.MathUtils;

import java.util.*;

public class Dictionary {

    private IDictionaryProvider dictionaryProvider;

    /**
     * Dictionary utility
     *
     * @param dictionaryProvider an implmentation of IDictionaryProvider
     * @throws IllegalArgumentException
     */
    public Dictionary(IDictionaryProvider dictionaryProvider) throws IllegalArgumentException {
        Assert.notNull(dictionaryProvider, "dictionaryProvider");
        this.dictionaryProvider = dictionaryProvider;
    }

    /**
     * Returns if the given word is an English word based on a given dictionary.
     *
     * @param word word
     * @return true/false
     */
    public boolean isEnglishWord(String word) {
        return dictionaryProvider.isEnglishWord(word);
    }


    /**
     * Returns the size of the dictionary.
     * @return size
     */
    public int getSize() {
        return dictionaryProvider.getSize();
    }

    /**
     * Returns if the given word is an English word based on a given dictionary and all possible matches.
     *
     * @param word word
     * @return true/false
     */
    public static boolean isEnglishWord(IDictionaryProvider dictionaryProvider, String word) {
        return new Dictionary(dictionaryProvider).isEnglishWord(word);
    }
}
