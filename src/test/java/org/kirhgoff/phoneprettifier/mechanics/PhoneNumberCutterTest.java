package org.kirhgoff.phoneprettifier.mechanics;

import org.kirhgoff.phoneprettifier.ArrayUtilsTrait;
import org.kirhgoff.phoneprettifier.chunk.Chunk;
import org.kirhgoff.phoneprettifier.model.DigitsArray;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PhoneNumberCutterTest implements ArrayUtilsTrait {
  @Test(enabled = false)
  public void testBasicCut() throws Exception {
    LetterConverter converter = new LetterConverter();
    Dictionary dictionary = new Dictionary(converter);
    dictionary.addWords(Arrays.asList("a", "b", "h", "d"));
    PhoneNumberCutter cutter = new PhoneNumberCutter(dictionary);

    DigitsArray phoneNumber = new DigitsArray(ints(2));
    Set<Chunk> chunks = cutter.cut(phoneNumber);
    assertThat(chunks).containsExactly(
      //TODO
    );
  }
}
