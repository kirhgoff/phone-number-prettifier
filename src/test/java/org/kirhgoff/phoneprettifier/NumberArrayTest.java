package org.kirhgoff.phoneprettifier;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.fail;

public class NumberArrayTest {

  @Test
  public void testHashCodeEquals() throws Exception {
    checkEqualsHashcode(new int[]{0, 1, 2}, new int[]{0, 1}, new int[]{0});
    checkEqualsHashcode(new int[]{}, new int[]{0, 1}, new int[]{0});
    checkEqualsHashcode(new int[]{1}, new int[]{0, 1}, new int[]{0});

    try {
      new NumberArray(null);
      fail("Should fail");
    } catch (IllegalArgumentException e) {}
  }

  @Test(enabled = false)
  public void testToString() throws Exception {
    assertThat(new NumberArray(new int [] {})).isEqualTo("[]");
    assertThat(new NumberArray(new int [] {1})).isEqualTo("[1]");
    assertThat(new NumberArray(new int [] {1, 2})).isEqualTo("[1, 2]");
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
