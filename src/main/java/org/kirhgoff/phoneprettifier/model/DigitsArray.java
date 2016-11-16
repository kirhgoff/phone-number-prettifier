package org.kirhgoff.phoneprettifier.model;

import java.util.Arrays;

import static java.util.Arrays.copyOfRange;
import static org.kirhgoff.phoneprettifier.model.Diff.EQUAL;
import static org.kirhgoff.phoneprettifier.model.Diff.MANY;
import static org.kirhgoff.phoneprettifier.model.Diff.SINGLE;

public class DigitsArray {
  private final int [] array;
  private final int hashCode;
  private final String string;

  public DigitsArray(int[] array) {
    assertNotNull(array);

    this.array = Arrays.copyOf(array, array.length);
    this.hashCode = Arrays.hashCode(this.array);
    this.string = Arrays.toString(this.array);
  }

  public int length() {
    return array.length;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    DigitsArray that = (DigitsArray) o;
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

  public Diff compareWith(DigitsArray other) {
    assertNotNull(other);
    assertIsLongerThan(other);

    int result = 0;
    for (int i = 0; i < other.length(); i ++) {
      if (this.array[i] != other.array[i]) {
        result ++;
        if (result > 1) {
          return MANY;
        }
      }
    }
    return result == 0 ? EQUAL : SINGLE;
  }

  public DigitsArray bite(DigitsArray other) {
    assertIsLongerThan(other);
    return new DigitsArray(copyOfRange(array, other.length(), array.length));
  }

  private void assertNotNull(Object some) {
    if (some == null) {
      throw new IllegalArgumentException("Do not accept nulls");
    }
  }

  private void assertIsLongerThan(DigitsArray other) {
    if (other.length() > this.length()) {
      throw new IndexOutOfBoundsException("Argument should be shorter than " + length() + " but it is " + other.length() + " digits long");
    }
  }

  private void assertNotEmpty(int[] array) {
    if (array.length == 0) {
      throw new IllegalArgumentException("Empty array is not accepted");
    }
  }

}
