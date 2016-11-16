package org.kirhgoff.phoneprettifier.model;

import org.kirhgoff.phoneprettifier.ArrayUtilsTrait;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.kirhgoff.phoneprettifier.model.Diff.EQUAL;
import static org.kirhgoff.phoneprettifier.model.Diff.MANY;
import static org.kirhgoff.phoneprettifier.model.Diff.SINGLE;
import static org.testng.Assert.fail;

public class DigitsArrayTest implements ArrayUtilsTrait {

  @Test
  public void testHashCodeEquals() throws Exception {
    checkEqualsHashcode(ints(0, 1, 2), ints(0, 1), ints(0));
    checkEqualsHashcode(ints(), ints(0, 1), ints(0));
    checkEqualsHashcode(ints(1), ints(0, 1), ints(0));
  }

  @Test
  public void testInvalidParams() throws Exception {
    try {
      new DigitsArray(null);
      fail("Should fail");
    } catch (IllegalArgumentException e) {}
  }

  @Test
  public void testToString() throws Exception {
    assertThat(digits().toString()).isEqualTo("[]");
    assertThat(digits(1).toString()).isEqualTo("[1]");
    assertThat(digits(1, 2).toString()).isEqualTo("[1, 2]");
  }

  @Test
  public void testDiffsCount() throws Exception {
    checkDiff(ints(1), ints(1), EQUAL);
    checkDiff(ints(1), ints(2), SINGLE);
    checkDiff(ints(1, 2, 3, 4, 5), ints(1, 2, 3, 4, 5), EQUAL);

    checkDiff(ints(1, 2, 3), ints(1, 2), EQUAL);
    checkDiff(ints(1, 2, 3), ints(1, 3), SINGLE);
    checkDiff(ints(1, 2, 3), ints(2, 3), MANY);

    checkDiff(ints(1, 2, 3, 5, 6), ints(1, 2, 3, 5), EQUAL);
    checkDiff(ints(1, 2, 3, 5, 6), ints(4, 2, 3, 5), SINGLE);
  }

  @Test
  public void testInvalidDiff() throws Exception {
    //Longer
    try {
      checkDiff(ints(1), ints(1, 2), EQUAL);
      fail("Should fail");
    } catch (IndexOutOfBoundsException e) {}

    try {
      DigitsArray first = digits(1, 2);
      first.compareWith(null);
      fail("Should fail");
    } catch(IllegalArgumentException e) {}
  }

  @Test
  public void testBite() {
    checkBiteResult(digits(1, 2, 3, 4), digits(6, 6), digits(3, 4));
    checkBiteResult(digits(1, 2, 3), digits(6, 6), digits(3));
    checkBiteResult(digits(1, 2, 3), digits(6, 6, 6), digits());
  }

  @Test
  public void testDigitAt() throws Exception {
    DigitsArray digits = digits(0, 1, 2, 3, 4, 5);
    for (int i = 0; i < digits.length(); i ++) {
      assertThat(digits.digitAt(i)).isEqualTo(i);
    }

    try {
      digits.digitAt(-1);
      fail("Should fail");
    } catch (IndexOutOfBoundsException e) {}

    try {
      digits.digitAt(666);
      fail("Should fail");
    } catch (IndexOutOfBoundsException e) {}

    try {
      digits().digitAt(0);
      fail("Should fail");
    } catch (IndexOutOfBoundsException e) {}

  }

  private Object checkBiteResult(DigitsArray number, DigitsArray number2, DigitsArray result) {
    return assertThat(number.bite(number2)).isEqualTo(result);
  }

  private void checkDiff(int[] source, int[] target, Diff diff) {
    DigitsArray first = new DigitsArray(source);
    DigitsArray second = new DigitsArray(target);
    assertThat(first.compareWith(second)).isEqualTo(diff);
  }

  private void checkEqualsHashcode(int[] equalData, int[] nonEqual1, int[] nonEqual2) {
    DigitsArray one = new DigitsArray(equalData);
    DigitsArray two = new DigitsArray(equalData);
    DigitsArray three = new DigitsArray(equalData);

    DigitsArray four = new DigitsArray(nonEqual1);
    DigitsArray five = new DigitsArray(nonEqual2);

    checkEqual(one, one);
    checkEqual(one, two);
    checkEqual(two, three);
    checkEqual(three, one);

    checkNotEqual(one, four);
    checkNotEqual(two, four);
    checkNotEqual(three, four);
    checkNotEqual(four, five);
  }

  private void checkNotEqual(DigitsArray one, DigitsArray two) {
    assertThat(one).isNotEqualTo(two);
    assertThat(one.hashCode()).isNotEqualTo(two.hashCode());
  }

  private void checkEqual(DigitsArray one, DigitsArray two) {
    assertThat(one).isEqualTo(two);
    assertThat(one.hashCode()).isEqualTo(two.hashCode());
  }


}
