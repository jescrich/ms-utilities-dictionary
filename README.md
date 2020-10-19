# Microsoft Dictionary String Based Utility

![Java CI](https://github.com/jescrich/ms-utilities-dictionary/workflows/Java%20CI/badge.svg)

## Author
@jescrich

## Maven Dependency
```java
    <groupId>com.microsoft.utilities</groupId>
    <artifactId>dictionary</artifactId>
    <version>1.0-SNAPSHOT</version>
```

## How it works

This component is a string based dictionary utility, which in his first version has the ability to find all of the English words in a given String.  
Based on a permutation like algorithm it finds all possible permutations of a given word in all its different lengths.
For example, if you are given the word WORKING, you can easily find WORK and KING, but ROW, RING and KNOW are also in there. 
It returns all possible combinations, as well all the english valid words.

### Assumptions

- Utility doesn't have access to the dictionary whole content, just a method isEnglishWord that return if it's contained into the dictionary.
- Matcher uses a *permutation like* algorithm which its complexity time is exponential.
- Matcher doesn't have a max complexity limitation, so as more length more time consuming.
- Matcher consider all substrings lengths as a possible english words, starting from a minimun length of 2.
- Matcher has a strategy pattern, that has only one implemented which is PermutationMatcherStrategy
- Matcher eventually can use different strategies that can be more performant but is out of the scope of the exercise.

### Design

- A Dictionary utility class with a Provider pattern to inject different dictionary providers.
- A Matcher utility class with a Strategy pattern and with a pre configured matcher strategy.
- A StringUtils with a permutation like alghoritm to get all possible words for a given word.
- A default Matcher Strategy that utilize StringUtils whic get the all the possibles words then ask the dictionary for which words are english ones.

## Build
```java
    mvn build
```

## Run Unit Tests
```java
    mvn test
```

## Install in your local maven repository
```java
    mvn install
```
> Since this component is not intented to be published, you have to install locally prior to try to use it.

# Usage Example

1. Add the dependency to your own pom.xml

2. This component includes a Flat File dictionary provider, you can use it or implement a custom one, just implementing the IDictionaryProvider.

3. Example

    ```java

       Dictionary dictionary = new Dictionary(new IDictionaryProvider() {
                         
             private String[] content = new String[]{
                                   "FOO", "BAR", "WORK", "KING", "ROW", "RING", 
                                   "KNOW", "KINGKONG", "MICROSOFT", "OK"
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
   
        Dictionary dictionary = new Dictionary(provider);
        MatchResult result = StringMatcher.matchAllWords(dictionary, "WORKING");
   
        List<String> englishWordsFound = result.getEnglishWords();
   
        englishWordsFound.forEach(System.out::println);
    ```
   
   ##### OUTPUT
   
   ```
   WORK
   KING
   ROW
   RING
   KNOW
   OK
   ```
