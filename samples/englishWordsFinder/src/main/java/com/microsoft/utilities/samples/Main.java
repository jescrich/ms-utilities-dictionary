package com.microsoft.utilities.samples;

import com.microsoft.utilities.Dictionary;
import com.microsoft.utilities.IDictionaryProvider;
import com.microsoft.utilities.MatchResult;
import com.microsoft.utilities.StringMatcher;
import com.microsoft.utilities.impl.FlatfileDictionaryProvider;

public class Main {

    public static void main(String[] args) throws Exception {

        // Use a built int flat file dictionary provider or implement your own.
        IDictionaryProvider provider = new FlatfileDictionaryProvider(Main.class.getClassLoader().getResource("allEnglishDictionary.txt").getPath());

        Dictionary dictionary = new Dictionary(provider);

        MatchResult results = StringMatcher.matchAllWords(dictionary, args[0]);

        System.out.println("Word: " + args[0]);

        System.out.println("Matched English Words:");

        results.getEnglishWords().forEach(System.out::println);

    }
}
