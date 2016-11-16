package org.kirhgoff.phoneprettifier.chunk;

public class WordChunk implements Chunk{
  private final String word;

  public WordChunk(String word) {
    this.word = word;
  }

  @Override
  public String toString() {
    return word;
  }
}
