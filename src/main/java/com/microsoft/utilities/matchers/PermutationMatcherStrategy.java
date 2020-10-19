package com.microsoft.utilities.matchers;

import com.microsoft.utilities.Dictionary;
import com.microsoft.utilities.MatchResult;
import com.microsoft.utilities.StringUtils;

import java.util.LinkedList;
import java.util.List;

public class PermutationMatcherStrategy extends MatcherStrategy {

    @Override
    public MatchResult match(Dictionary dictionary, String word) {

        List<String> subset = StringUtils.findPermutations(word);

        List<String> result = new LinkedList<>();

        subset.forEach((w) -> {
            if (dictionary.isEnglishWord(w))
                result.add(w);
        });

        long complexity = StringUtils.getPermutationComplexity(word);

        return new MatchResult(word, subset, result, complexity);
    }
}
