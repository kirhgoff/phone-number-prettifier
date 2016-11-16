package org.kirhgoff.phoneprettifier.chunk;

public class WordChunk extends ChunkBase {
  private final String word;

  public WordChunk(String word) {
    assertNotNull(word);
    this.word = word.toUpperCase();
  }

  @Override
  public String toString() {
    return word;
  }
}
