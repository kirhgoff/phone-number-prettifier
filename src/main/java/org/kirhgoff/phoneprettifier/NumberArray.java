package org.kirhgoff.phoneprettifier;

import java.util.Arrays;

public class NumberArray {
  private final int [] data;
  private final int hashCode;

  public NumberArray(int[] data) {
    if (data == null) {
      throw new IllegalArgumentException("nulls are not allowed");
    }
    this.data = Arrays.copyOf(data, data.length);
    this.hashCode = Arrays.hashCode(data);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    NumberArray that = (NumberArray) o;

    return this.hashCode == that.hashCode;
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(data);
  }
}
