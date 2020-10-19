package com.microsoft.utilities;

public class Assert {
    /**
     * Assert utility
     * @param argument to be checked
     * @param argumentName argument name.
     * @throws IllegalArgumentException
     */
    public static void notNull(Object argument, String argumentName) throws IllegalArgumentException {
        if (argument == null)
        {
            throw new IllegalArgumentException(String.format("Argument %s cannot be null.", argumentName));
        }
    }
}
