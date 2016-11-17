package org.kirhgoff.phoneprettifier.chunk;

import org.kirhgoff.phoneprettifier.model.Diff;

public class PartiallyMatchedChunk extends ChunkBase {
  private final String wordWithDigitsReplaced;

  public PartiallyMatchedChunk(String word, Diff diff) {
    assertNotNull(word);
    assertNotEmpty(word);
    assertBounds(word, diff.length() - 1);

    char[] initialChars = word.toCharArray();
    for (int i = 0; i < initialChars.length; i++) {
      int digit = diff.digitAt(i);
      if (digit != -1) {
        initialChars[i] = String.valueOf(digit).charAt(0);
      }
    }
    wordWithDigitsReplaced = String.valueOf(initialChars).toUpperCase();
  }

  @Override
  public String toString() {
    return wordWithDigitsReplaced;
  }
}
