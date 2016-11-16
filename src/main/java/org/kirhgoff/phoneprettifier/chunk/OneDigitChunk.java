package org.kirhgoff.phoneprettifier.chunk;

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
}
