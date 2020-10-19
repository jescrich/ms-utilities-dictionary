package com.microsoft.utilities.impl;

import com.microsoft.utilities.Assert;
import com.microsoft.utilities.IDictionaryProvider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class FlatfileDictionaryProvider implements IDictionaryProvider {

    private String resourceLocation;
    private HashMap<String, String> content;

    public FlatfileDictionaryProvider(String resourceLocation) throws IllegalArgumentException, IOException {
        Assert.notNull(resourceLocation, "resourceLocation");
        this.resourceLocation = resourceLocation;
        this.content = new HashMap<>();
        readResource();
    }

    private void readResource() throws IOException {

        //TODO: A simple flat file provider without any kind of format validation.
        //      Expects one word per line.

        List<String> r = new ArrayList<>(Files.readAllLines(Paths.get(resourceLocation)));

        r.forEach((w) -> content.put(w.toLowerCase(), w));
    }

    @Override
    public String[] getWords() {
        return content.values().toArray(new String[0]);
    }

    @Override
    public int getSize() {
        return content.size();
    }

    @Override
    public boolean isEnglishWord(String word) {
        return content.containsKey(word.toLowerCase());
    }
}
