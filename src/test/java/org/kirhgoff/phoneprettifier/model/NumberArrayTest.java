package org.kirhgoff.phoneprettifier.model;

import org.kirhgoff.phoneprettifier.ArrayUtilsTrait;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.kirhgoff.phoneprettifier.model.Diff.EQUAL;
import static org.kirhgoff.phoneprettifier.model.Diff.MANY;
import static org.kirhgoff.phoneprettifier.model.Diff.SINGLE;
import static org.testng.Assert.fail;

public class NumberArrayTest implements ArrayUtilsTrait {

  @Test
  public void testHashCodeEquals() throws Exception {
    checkEqualsHashcode(ints(0, 1, 2), ints(0, 1), ints(0));
    checkEqualsHashcode(ints(), ints(0, 1), ints(0));
    checkEqualsHashcode(ints(1), ints(0, 1), ints(0));

    try {
      new NumberArray(null);
      fail("Should fail");
    } catch (IllegalArgumentException e) {}
  }

  @Test
  public void testToString() throws Exception {
    assertThat(new NumberArray(ints()).toString()).isEqualTo("[]");
    assertThat(new NumberArray(ints(1)).toString()).isEqualTo("[1]");
    assertThat(new NumberArray(ints(1, 2)).toString()).isEqualTo("[1, 2]");
  }

  @Test
  public void testDiffsCount() throws Exception {
    checkDiff(ints(1, 2, 3), ints(1, 2), EQUAL);
    checkDiff(ints(1, 2, 3), ints(1, 3), SINGLE);
    checkDiff(ints(1, 2, 3), ints(2, 3), MANY);
    checkDiff(ints(1, 2, 3, 5, 6), ints(1, 2, 3, 5), EQUAL);
    checkDiff(ints(1, 2, 3, 5, 6), ints(4, 2, 3, 5), SINGLE);
  }

  private void checkDiff(int[] source, int[] target, Diff diff) {
    NumberArray first = new NumberArray(source);
    NumberArray second = new NumberArray(target);
    assertThat(first.compareWith(second)).isEqualTo(diff);
  }

  private void checkEqualsHashcode(int[] equalData, int[] nonEqual1, int[] nonEqual2) {
    NumberArray one = new NumberArray(equalData);
    NumberArray two = new NumberArray(equalData);
    NumberArray three = new NumberArray(equalData);

    NumberArray four = new NumberArray(nonEqual1);
    NumberArray five = new NumberArray(nonEqual2);

    checkEqual(one, one);
    checkEqual(one, two);
    checkEqual(two, three);
    checkEqual(three, one);

    checkNotEqual(one, four);
    checkNotEqual(two, four);
    checkNotEqual(three, four);
    checkNotEqual(four, five);
  }

  private void checkNotEqual(NumberArray one, NumberArray two) {
    assertThat(one).isNotEqualTo(two);
    assertThat(one.hashCode()).isNotEqualTo(two.hashCode());
  }

  private void checkEqual(NumberArray one, NumberArray two) {
    assertThat(one).isEqualTo(two);
    assertThat(one.hashCode()).isEqualTo(two.hashCode());
  }

}
