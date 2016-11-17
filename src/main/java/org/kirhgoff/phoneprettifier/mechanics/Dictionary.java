package org.kirhgoff.phoneprettifier.mechanics;

import org.kirhgoff.phoneprettifier.model.MultiWord;
import org.kirhgoff.phoneprettifier.model.DigitsArray;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Dictionary {
  private final LetterConverter converter = new LetterConverter();
  private final Map<DigitsArray, MultiWord> words = new HashMap<>();

  Dictionary addWords (List<String> wordsList) {
    wordsList.forEach(this::addWord);
    return this;
  }

  private void addWord(String word) {
    int [] numeric = converter.convertWord(word);
    DigitsArray wrapper = new DigitsArray(numeric);
    words.computeIfAbsent(wrapper, MultiWord::new).addVariant(word);
  }

  MultiWord wordFor(int[] numbers) {
    return words.get(new DigitsArray(numbers));
  }

  //TODO could be optimized by restricting word length
  Collection<MultiWord> getWords () {
    return words.values();
  }

  public static Dictionary fromFile(String dictionaryPath) throws IOException {
    long started = System.currentTimeMillis();
    Dictionary dictionary = new Dictionary();
    List<String> skippedWords = new LinkedList<>();
    AtomicInteger counter = new AtomicInteger(0);

    Files.lines(Paths.get(dictionaryPath))
      .filter(s -> s != null && !s.isEmpty())
      .map(String::trim)
      .forEach(s -> {
        try {
          dictionary.addWord(s);
          counter.incrementAndGet();
        } catch (Exception e) {
          skippedWords.add(s);
        }
      });

    System.out.println("Loaded " + counter.get() + " dictionary words in " + (System.currentTimeMillis() - started) + " millis");

    if (!skippedWords.isEmpty()) {
      Path skipped = Files.createTempFile("skipped", ".txt");
      Files.write(skipped, skippedWords, Charset.defaultCharset());

      System.out.println("Skipped " + skippedWords.size() + " incorrect dictionary words, written to " + skipped.toAbsolutePath());
    }
    return dictionary;
  }
}
