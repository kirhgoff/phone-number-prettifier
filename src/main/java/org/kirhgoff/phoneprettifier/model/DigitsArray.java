package org.kirhgoff.phoneprettifier.model;

import java.util.Arrays;

import static java.util.Arrays.copyOfRange;

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

  public int digitAt(int index) {
    assertBounds(index);
    return array[index];
  }

  public int length() {
    return array.length;
  }

  public boolean isEmpty() {
    return length() == 0;
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

    boolean matched = true;
    int lastDiff = Integer.MIN_VALUE;
    int [] diffs = new int[other.length()];
    Arrays.fill(diffs, -1);

    for (int i = 0; i < other.length(); i ++) {
      if (array[i] != other.array[i]) {
        if (lastDiff + 1 == i) {
          matched = false;
          break;
        }

        lastDiff = i;
        diffs[i] = array[i];
      }
    }
    return new Diff (matched, diffs);
  }

  //TODO int parameter is enough
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
      throw new IndexOutOfBoundsException("Argument should be shorter than " + length() + ", but it is " + other.length() + " digits long");
    }
  }

  private void assertBounds(int index) {
    if (index < 0 || index >= length()) {
      throw new IndexOutOfBoundsException("Cannot get char at " + index + " while array length is " + length());
    }
  }

}
