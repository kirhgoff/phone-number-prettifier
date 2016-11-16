package org.kirhgoff.phoneprettifier.model;

/**
 * Tuple of finished chunk and remainder
 */
public class MatchResult {
  private final MultiWord head;
  private final DigitsArray tail;

  public MatchResult(MultiWord head, DigitsArray tail) {
    this.head = head;
    this.tail = tail;
  }

  public MultiWord getHead() {
    return head;
  }

  public DigitsArray getTail() {
    return tail;
  }
}
