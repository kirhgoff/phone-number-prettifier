package org.kirhgoff.phoneprettifier;

import org.kirhgoff.phoneprettifier.model.MultiWord;
import org.kirhgoff.phoneprettifier.model.NumberArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dictionary {
  private final LetterConverter converter;
  private final Map<NumberArray, MultiWord> words = new HashMap<>();

  public Dictionary(LetterConverter converter) {
    this.converter = converter;
  }

  public void addWords (List<String> wordsList) {
    for (String word : wordsList) {
      int [] numeric = converter.convertWord(word);
      NumberArray wrapper = new NumberArray(numeric);
      words.computeIfAbsent(wrapper, MultiWord::new).addVariant(word);
    }
  }

  public MultiWord wordFor(int[] numbers) {
    return words.get(new NumberArray(numbers));
  }
}
