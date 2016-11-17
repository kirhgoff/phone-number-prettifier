package org.kirhgoff.phoneprettifier.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Collection of words represented by same number
 */
public class MultiWord {
  private final DigitsArray number;
  private final Set<String> words = new HashSet<>();

  public MultiWord(DigitsArray number) {
    this.number = number;
  }

  public Set<String> getVariants() {
    return Collections.unmodifiableSet(words);
  }

  public void addVariant(String word) {
    words.add(word);
  }

  public int length() {
    return number.length();
  }

  public DigitsArray getNumberArray() {
    return number;
  }

  @Override
  public String toString() {
    return number.toString() + "=>" + words;
  }
}
