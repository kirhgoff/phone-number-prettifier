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
}
