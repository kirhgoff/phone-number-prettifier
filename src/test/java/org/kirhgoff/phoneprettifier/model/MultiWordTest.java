package org.kirhgoff.phoneprettifier.model;

import org.kirhgoff.phoneprettifier.ArrayUtilsTrait;
import org.testng.annotations.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.fail;

public class MultiWordTest implements ArrayUtilsTrait {
  @Test
  public void testMultiWordBasic() throws Exception {
    MultiWord word = new MultiWord(new DigitsArray(ints(1)));
    word.addVariant("a");
    word.addVariant("b");

    Set<String> variants = word.getVariants();
    assertThat(variants).containsExactly("a", "b");

    try {
      variants.add("c");
      fail("No modifications are allowed");
    } catch (UnsupportedOperationException e) {}
  }

  @Test
  public void testFindDiffIndex() throws Exception {
    MultiWord word = new MultiWord(new DigitsArray(ints(2, 2, 2)));
    word.addVariant("aaa");

    assertThat (word.findDiffIndex(digits(2, 2, 2))).isEqualTo(-1);
    assertThat (word.findDiffIndex(digits(1, 2, 2))).isEqualTo(0);
    assertThat (word.findDiffIndex(digits(2, 1, 2))).isEqualTo(1);
    assertThat (word.findDiffIndex(digits(2, 2, 1))).isEqualTo(2);
  }

  @Test
  public void testFindDiffIndexIllegal() throws Exception {
    MultiWord word = new MultiWord(new DigitsArray(ints(1)));
    word.addVariant("a");

    try {
      word.findDiffIndex(digits(2, 2));
      fail("Should fail");
    } catch (IllegalArgumentException e) {}

    try {
      word.findDiffIndex(digits(2, 2, 2, 2));
      fail("Should fail");
    } catch (IllegalArgumentException e) {}
  }

  @Test
  public void testToString() throws Exception {
    MultiWord word = new MultiWord(new DigitsArray(ints(2)));
    word.addVariant("a");
    word.addVariant("b");

    assertThat(word.toString()).isEqualTo("[2]=>[a, b]");

  }
}
