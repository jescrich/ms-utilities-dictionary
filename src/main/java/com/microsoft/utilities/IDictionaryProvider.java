package com.microsoft.utilities;

import java.io.IOException;

/**
 * A Dictionary Provider interface
 */
public interface IDictionaryProvider {
    String[] getWords();
    int getSize();
    boolean isEnglishWord(String word);
}
