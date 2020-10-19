package com.microsoft.utilities.tests;

import com.microsoft.utilities.Dictionary;
import com.microsoft.utilities.IDictionaryProvider;
import com.microsoft.utilities.impl.FlatfileDictionaryProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FlatfileDictionaryProviderTests {

    @Test
    public void provider_withoutResourceLocation_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new FlatfileDictionaryProvider(null);
        });

        String expectedMessage = "Argument resourceLocation cannot be null.";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void provider_shouldProvideFlatFileContent() throws IOException {
        IDictionaryProvider provider = new FlatfileDictionaryProvider(this.getClass().getClassLoader().getResource("englishDictionary.txt").getPath());
        Assertions.assertEquals(4, provider.getWords().length);
        Assertions.assertEquals(4, provider.getSize());
    }
}
