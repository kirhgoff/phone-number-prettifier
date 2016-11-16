package org.kirhgoff.phoneprettifier.chunk;

public class WordWithDigitChunk implements Chunk {
  private final String wordWithDigit;

  public WordWithDigitChunk(String word, int index, int digit) {
    assertNotNull(word);
    assertNotEmpty(word);
    assertBounds(word, index);
    assertIsDigit(digit);


    char[] myNameChars = word.toCharArray();
    myNameChars[index] = String.valueOf(digit).charAt(0);
    wordWithDigit = String.valueOf(myNameChars);
  }

  @Override
  public String toString() {
    return wordWithDigit;
  }
}
