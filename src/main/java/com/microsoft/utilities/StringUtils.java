package com.microsoft.utilities;

import org.apache.commons.math.util.MathUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class StringUtils {

    /**
     * Find all permutations of a given word, non repeated and for all different lengths, starting from 1 to max word length.
     * @param word to traverse
     * @return List of all possible uppercased permutations.
     */
    public static List<String> findPermutations(String word) {

        char[] arr = word.toUpperCase().toCharArray();

        List<String> result = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            result.addAll(findPermutations(arr, String.valueOf(arr[i]), Collections.singletonList(i)));
        }

        return result;
    }

    /**
     * Find all permutations possible permutations, given a char array as a source and a partial string.
     * @param arr array of char to traverse
     * @param current current prefix string
     * @param currentIndexes current chars index already included in the current prefix.
     * @return List of all possible permutations
     */
    private static List<String> findPermutations(char[] arr, String current, List<Integer> currentIndexes) {

        List<String> result = new LinkedList<>();

        // Given a current string, find which other positions remains to be added.
        // ie: given arr: {'A','B','C'} and current = "AB", remaining position is 2, "C"

        for (int i = 0; i < arr.length; i++) {

            if (currentIndexes.contains(i))
                continue;

            List<Integer> nextIndexes = new LinkedList<>(currentIndexes);

            String m = current + arr[i];

            result.add(m);

            nextIndexes.add(i);

            result.addAll(findPermutations(arr, m, nextIndexes));

        }

        return result;
    }

    /**
     * Return the permutation complexity based on a given word, consider all possible lengths.
     * @param word
     * @return
     */
    public static long getPermutationComplexity(String word) {
        int wordSize = word.length();
        long complexity = 0;
        for (int i = wordSize; i > 1; i--) {
            complexity += MathUtils.factorial(wordSize) / MathUtils.factorial(wordSize - i);
        }
        return complexity;
    }
}
