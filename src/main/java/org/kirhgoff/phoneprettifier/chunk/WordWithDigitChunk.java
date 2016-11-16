package org.kirhgoff.phoneprettifier.chunk;

public class WordWithDigitChunk implements Chunk {
  private final String wordWithDigit;

  public WordWithDigitChunk(String word, int index, int digit) {
    if (digit < 0 || digit > 9) {
      throw new IllegalArgumentException("This is no digit: " + digit);
    }
    char[] myNameChars = word.toCharArray();
    myNameChars[index] = String.valueOf(digit).charAt(0);
    wordWithDigit = String.valueOf(myNameChars);
  }

  @Override
  public String toString() {
    return wordWithDigit;
  }
}
