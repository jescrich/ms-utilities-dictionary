package com.microsoft.utilities.tests.integration;

import com.microsoft.utilities.Dictionary;
import com.microsoft.utilities.IDictionaryProvider;
import com.microsoft.utilities.StringMatcher;
import com.microsoft.utilities.impl.FlatfileDictionaryProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Matcher;

import static org.mockito.Mockito.mock;

public class FlatFileDictionaryTests {

    @Test
    public void matchWords_givenDictionary_shouldMatchWords() throws Exception {
        IDictionaryProvider provider = new FlatfileDictionaryProvider(this.getClass().getClassLoader().getResource("allEnglishDictionary.txt").getPath());
        Dictionary dictionary = new Dictionary(provider);
        int results = StringMatcher.matchAllWords(dictionary, "WORKING").getEnglishWords().size();
        Assertions.assertNotEquals(0, results);
    }

}
