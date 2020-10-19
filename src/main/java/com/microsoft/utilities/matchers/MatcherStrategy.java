package com.microsoft.utilities.matchers;

import com.microsoft.utilities.Dictionary;
import com.microsoft.utilities.MatchResult;

public abstract class MatcherStrategy {
    public abstract MatchResult match(Dictionary dictionary, String word);
}
