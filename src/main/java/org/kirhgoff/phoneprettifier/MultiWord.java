package org.kirhgoff.phoneprettifier;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MultiWord {
  private final NumberArray number;
  private final Set<String> words = new HashSet<>();

  public MultiWord(NumberArray number) {
    this.number = number;
  }

  public Set<String> getVariants() {
    return Collections.unmodifiableSet(words);
  }

  public void addVariant(String word) {
    words.add(word);
  }
}
