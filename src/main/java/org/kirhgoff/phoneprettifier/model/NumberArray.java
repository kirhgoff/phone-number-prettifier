package org.kirhgoff.phoneprettifier.model;

import java.util.Arrays;

public class NumberArray {
  private final int [] array;
  private final int hashCode;
  private final String string;

  public NumberArray(int[] array) {
    if (array == null) {
      throw new IllegalArgumentException("nulls are not allowed");
    }
    this.array = Arrays.copyOf(array, array.length);
    this.hashCode = Arrays.hashCode(this.array);
    this.string = Arrays.toString(this.array);
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
    return Arrays.hashCode(array);
  }

  @Override
  public String toString() {
    return string;
  }
}
