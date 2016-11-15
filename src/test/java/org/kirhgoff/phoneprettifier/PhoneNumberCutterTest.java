package org.kirhgoff.phoneprettifier;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PhoneNumberCutterTest implements ArrayUtilsTrait {
  @Test
  public void testBasicCut() throws Exception {
    LetterConverter converter = new LetterConverter();
    Dictionary dictionary = new Dictionary(converter);
    dictionary.addWords(Arrays.asList("a", "ad", "dg", "d"));
    PhoneNumberCutter cutter = new PhoneNumberCutter(dictionary);

    NumberArray phoneNumber = new NumberArray(ints(2, 3, 4));
    Set<Chunk> chunks = cutter.cut(phoneNumber);
//    assertThat(chunks).containsExactly(
//
//    );
  }
}
