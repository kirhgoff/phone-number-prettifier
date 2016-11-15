package org.kirhgoff.phoneprettifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dictionary {
  private final LetterConverter converter;
  private final Map<int [], MultiWord> words = new HashMap<>();

  public Dictionary(LetterConverter converter) {
    this.converter = converter;
  }

  public void addWords (List<String> words) {
//    for (String word : words) {
//      words
//    }
  }

  public MultiWord wordFor(int[] number) {
    return null;
  }
}
