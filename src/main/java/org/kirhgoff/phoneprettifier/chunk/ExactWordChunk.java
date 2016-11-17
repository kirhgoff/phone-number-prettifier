package org.kirhgoff.phoneprettifier.chunk;

public class ExactWordChunk extends ChunkBase {
  private final String word;

  public ExactWordChunk(String word) {
    assertNotNull(word);
    this.word = word.toUpperCase();
  }

  @Override
  public String toString() {
    return word;
  }

  @Override
  public boolean lastCharIsDigit() {
    return false;
  }
}
