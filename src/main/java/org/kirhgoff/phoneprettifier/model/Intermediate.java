package org.kirhgoff.phoneprettifier.model;

import org.kirhgoff.phoneprettifier.chunk.Chunk;

/**
 * Tuple of finished chunk and remainder
 */
public class Intermediate {
  private final Chunk head;
  private final NumberArray tail;

  public Intermediate(Chunk head, NumberArray tail) {
    this.head = head;
    this.tail = tail;
  }

  public Chunk getHead() {
    return head;
  }

  public NumberArray getTail() {
    return tail;
  }
}
