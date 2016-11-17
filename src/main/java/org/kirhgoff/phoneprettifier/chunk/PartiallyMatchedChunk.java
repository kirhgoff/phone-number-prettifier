package org.kirhgoff.phoneprettifier.chunk;

import org.kirhgoff.phoneprettifier.model.Diff;

/**
 * Dictionary word that was partially matched
 * (without two consecutive changes)
 */
public class PartiallyMatchedChunk extends ChunkBase {
  private final String wordWithDigitsReplaced;
  private final boolean lastCharIsDigit;

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
    lastCharIsDigit = diff.digitAt(diff.length() - 1) != NO_CHANGE;
  }

  @Override
  public String toString() {
    return wordWithDigitsReplaced;
  }

  @Override
  public boolean lastCharIsDigit() {
    return lastCharIsDigit;
  }
}
