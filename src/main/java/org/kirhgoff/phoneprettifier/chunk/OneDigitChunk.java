package org.kirhgoff.phoneprettifier.chunk;

public class OneDigitChunk implements Chunk {
  private final int digit;

  public OneDigitChunk(int digit) {
    this.digit = digit;
  }

  @Override
  public String toString() {
    return String.valueOf(digit);
  }
}
