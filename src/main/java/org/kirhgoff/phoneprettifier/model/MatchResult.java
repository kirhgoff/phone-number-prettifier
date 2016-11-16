package org.kirhgoff.phoneprettifier.model;

import org.kirhgoff.phoneprettifier.chunk.Chunk;

/**
 * Tuple of finished chunk and remainder
 */
public class MatchResult {
  private final Chunk head;
  private final DigitsArray tail;

  public MatchResult(Chunk head, DigitsArray tail) {
    this.head = head;
    this.tail = tail;
  }

  public Chunk getHead() {
    return head;
  }

  public DigitsArray getTail() {
    return tail;
  }
}
