package org.kirhgoff.phoneprettifier.model;

public enum Diff {
  EQUAL(0),
  SINGLE(1),
  MANY(2);

  private final int value;

  Diff(int value) {
    this.value = value;
  }
}
