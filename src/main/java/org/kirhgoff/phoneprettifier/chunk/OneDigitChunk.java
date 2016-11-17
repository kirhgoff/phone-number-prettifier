package org.kirhgoff.phoneprettifier.chunk;

/**
 * Single digit chunk. Used to skip one digit
 * after the matched words to get all the variants
 */
public class OneDigitChunk extends ChunkBase {
  private final int digit;

  public OneDigitChunk(int digit) {
    assertIsDigit(digit);
    this.digit = digit;
  }

  @Override
  public String toString() {
    return String.valueOf(digit);
  }

  @Override
  public boolean lastCharIsDigit() {
    return true; //It is true
  }
}
