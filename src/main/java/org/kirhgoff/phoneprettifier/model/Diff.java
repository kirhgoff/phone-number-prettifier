package org.kirhgoff.phoneprettifier.model;

import java.util.Arrays;

//TODO extend DigitsArray
/**
 * Class stores differences between two digit arrays
 * matched values are marked by -1, diffs are real digits
 */
public class Diff {
  private final boolean matched;
  private final boolean exactMatch;
  private final int[] diffs;

  public Diff(boolean matched, int[] diffs) {
    this.matched = matched;
    this.diffs = diffs;
    this.exactMatch = checkIsExactMatch();
  }

  private boolean checkIsExactMatch() {
    for (int i = 0; i < diffs.length; i++) {
      if (diffs[i] != -1) return false;
    }
    return true;
  }

  public boolean areMatched() {
    return matched;
  }

  public boolean isExactMatch() {
    return exactMatch;
  }

  public int digitAt(int index) {
    return diffs[index];
  }

  public int length() {
    return diffs.length;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Diff diff = (Diff) o;

    if (matched != diff.matched) return false;
    return Arrays.equals(diffs, diff.diffs);
  }

  @Override
  public int hashCode() {
    int result = (matched ? 1 : 0);
    result = 31 * result + Arrays.hashCode(diffs);
    return result;
  }
}
