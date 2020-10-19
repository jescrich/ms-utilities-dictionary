package com.microsoft.utilities.tests;

import com.microsoft.utilities.Dictionary;
import com.microsoft.utilities.IDictionaryProvider;
import com.microsoft.utilities.MatchResult;
import com.microsoft.utilities.StringMatcher;
import com.microsoft.utilities.matchers.PermutationMatcherStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MatcherTests {

    @Test
    public void matchWords_givenSameWords_shouldReturnTrue() throws Exception {
        IDictionaryProvider provider = mock(IDictionaryProvider.class);

        when(provider.isEnglishWord(eq("WORKING"))).thenReturn(true);

        Dictionary dictionary = new Dictionary(provider);

        MatchResult result = new StringMatcher(dictionary, new PermutationMatcherStrategy()).matchAllWords("WORKING");

        Assertions.assertEquals(1, result.getEnglishWords().size());
    }

    @Test
    public void matchWords_givenWordWithConsecutiveCharsAtBeginning_shouldReturnTrue() throws Exception {
        IDictionaryProvider provider = mock(IDictionaryProvider.class);

        when(provider.isEnglishWord(eq("WORK"))).thenReturn(true);

        Dictionary dictionary = new Dictionary(provider);

        MatchResult result = new StringMatcher(dictionary, new PermutationMatcherStrategy()).matchAllWords("WORKING");

        Assertions.assertEquals(1, result.getEnglishWords().size());
    }

    @Test
    public void matchWords_givenWordWithConsecutiveCharsAtEnd_shouldReturnTrue() throws Exception {
        IDictionaryProvider provider = mock(IDictionaryProvider.class);

        when(provider.isEnglishWord(eq("ING"))).thenReturn(true);

        Dictionary dictionary = new Dictionary(provider);

        MatchResult result = new StringMatcher(dictionary, new PermutationMatcherStrategy()).matchAllWords("WORKING");

        Assertions.assertTrue(result.getEnglishWords().size() == 1);
    }

    @Test
    public void matchWords_givenWordWithNonConsecutiveChars_shouldReturnTrue() throws Exception {
        IDictionaryProvider provider = mock(IDictionaryProvider.class);

        when(provider.isEnglishWord(eq("KNOW"))).thenReturn(true);

        Dictionary dictionary = new Dictionary(provider);

        MatchResult result = new StringMatcher(dictionary, new PermutationMatcherStrategy()).matchAllWords("WORKING");

        Assertions.assertEquals(1, result.getEnglishWords().size());
    }

    @Test
    public void matchWords_givenWordNoLettersMatching_shouldReturnFalse() throws Exception {

        Dictionary dictionary = new Dictionary(new IDictionaryProvider() {

            private String[] content = new String[]{
                    "FOO", "BAR"
            };

            @Override
            public String[] getWords() {
                return content;
            }

            @Override
            public int getSize() {
                return content.length;
            }

            @Override
            public boolean isEnglishWord(String word) {
                return
                        Arrays.asList(content).contains(word.toUpperCase());
            }
        });

        MatchResult result = new StringMatcher(dictionary, new PermutationMatcherStrategy()).matchAllWords("WORKING");

        Assertions.assertEquals(0, result.getEnglishWords().size());
    }

    @Test
    public void matchWords_givenDictionary_shouldMatchWords() throws Exception {

        Dictionary dictionary = new Dictionary(new IDictionaryProvider() {

            private String[] content = new String[]{
                    "FOO", "BAR", "WORK", "KING", "ROW", "RING", "KNOW", "KINGKONG", "OK", "NO", "WORKI"
            };

            @Override
            public String[] getWords() {
                return content;
            }

            @Override
            public int getSize() {
                return content.length;
            }

            @Override
            public boolean isEnglishWord(String word) {
                return Arrays.asList(content).contains(word.toUpperCase());
            }
        });

        MatchResult result = new StringMatcher(dictionary, new PermutationMatcherStrategy()).matchAllWords("WORKING");

        Assertions.assertEquals(8, result.getEnglishWords().size());

        Assertions.assertTrue(result.getEnglishWords().contains("WORK"));
        Assertions.assertTrue(result.getEnglishWords().contains("KING"));
        Assertions.assertTrue(result.getEnglishWords().contains("ROW"));
        Assertions.assertTrue(result.getEnglishWords().contains("RING"));
        Assertions.assertTrue(result.getEnglishWords().contains("KNOW"));
        Assertions.assertTrue(result.getEnglishWords().contains("OK"));
        Assertions.assertTrue(result.getEnglishWords().contains("NO"));
        Assertions.assertTrue(result.getEnglishWords().contains("WORKI"));

        Assertions.assertFalse(result.getEnglishWords().contains("FOO"));
        Assertions.assertFalse(result.getEnglishWords().contains("KINGKONG"));

        System.out.println("Possibles: ");
        System.out.println(String.join(",",result.getCombinations()));
        System.out.println("Results: ");
        result.getEnglishWords().forEach(System.out::println);
        System.out.println("Complexity: " + result.getComplexity());
    }
}
