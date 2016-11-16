package org.kirhgoff.phoneprettifier.chunk;

public class WordChunk extends ChunkBase {
  private final String word;

  public WordChunk(String word) {
    if (word == null) {
      throw new IllegalArgumentException("Do not accept null words");
    }
    this.word = word.toUpperCase();
  }

  @Override
  public String toString() {
    return word;
  }
}
