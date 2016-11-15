package org.kirhgoff.phoneprettifier;

import java.util.Arrays;
import java.util.StringJoiner;

public class NumberArray {
  private final int [] array;
  private final int hashCode;
  private final String string;

  public NumberArray(int[] array) {
    if (array == null) {
      throw new IllegalArgumentException("nulls are not allowed");
    }
    this.array = Arrays.copyOf(array, array.length);
    this.hashCode = Arrays.hashCode(array);
    this.string = Arrays.toString(array);
  }

  private String makeString(int[] numbers) {
    StringJoiner joiner = new StringJoiner("[", ", ", "]");
    for (int number : numbers) {
      joiner.add(String.valueOf(number));
    }
    return joiner.toString();
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
