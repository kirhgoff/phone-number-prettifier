package org.kirhgoff.phoneprettifier.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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

  public int findDiffIndex(DigitsArray array) {
    assertNotNull(array);
    assertSameLength(array);

    for (int i = 0; i < length(); i ++) {
      if (array.digitAt(i) != this.number.digitAt(i)) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public String toString() {
    return number.toString() + "=>" + words;
  }

  private void assertNotNull(DigitsArray array) {
    if (array == null) {
      throw new IllegalArgumentException("Cannot accept null");
    }
  }

  private void assertSameLength(DigitsArray array) {
    if (array.length() != this.number.length()) {
      throw new IllegalArgumentException("Should be of the same length " + this.number.length() + ", while argument is " + array.length() + " digits");
    }
  }
}
