package com.microsoft.utilities.tests;

import com.microsoft.utilities.Dictionary;
import com.microsoft.utilities.IDictionaryProvider;
import org.apache.commons.math.util.MathUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class DictionaryTests {

    @Test
    public void dictionaryWithoutProvider_shouldRaiseException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Dictionary(null);
        });

        String expectedMessage = "Argument dictionaryProvider cannot be null.";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void isEnglishWord_shouldInteractWithDictionaryProvider() {
        IDictionaryProvider provider = mock(IDictionaryProvider.class);
        Dictionary.isEnglishWord(provider, "FOO");
        verify(provider, times(1)).isEnglishWord("FOO");
    }

    @Test
    public void isEnglishWord_whenIsInDictionary_shouldReturnTrue() {
        IDictionaryProvider provider = mock(IDictionaryProvider.class);
        when(provider.isEnglishWord(eq("FOO"))).thenReturn(true);
        Assertions.assertTrue(Dictionary.isEnglishWord(provider, "FOO"));

    }

    @Test
    public void isEnglishWord_whenIsNotInDictionary_shouldReturnFalse() {
        IDictionaryProvider provider = mock(IDictionaryProvider.class);
        when(provider.isEnglishWord(eq("FOO"))).thenReturn(false);
        Assertions.assertFalse(Dictionary.isEnglishWord(provider, "FOO"));
    }
}
