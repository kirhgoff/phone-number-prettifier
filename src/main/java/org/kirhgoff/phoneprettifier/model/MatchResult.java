package org.kirhgoff.phoneprettifier.model;

import org.kirhgoff.phoneprettifier.chunk.Chunk;

/**
 * Tuple of finished chunk and remainder
 */
public class MatchResult {
  private final Chunk head;
  private final DigitsArray remainder;

  public MatchResult(Chunk head, DigitsArray remainder) {
    this.head = head;
    this.remainder = remainder;
  }

  public Chunk getHead() {
    return head;
  }

  public DigitsArray getRemainder() {
    return remainder;
  }

  @Override
  public String toString() {
    return head.toString() + "..." + remainder.toString();
  }
}
