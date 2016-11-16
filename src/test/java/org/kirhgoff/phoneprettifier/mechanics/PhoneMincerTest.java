package org.kirhgoff.phoneprettifier.mechanics;

import org.kirhgoff.phoneprettifier.ArrayUtilsTrait;
import org.kirhgoff.phoneprettifier.chunk.Chunk;
import org.kirhgoff.phoneprettifier.chunk.ChunkPrinter;
import org.kirhgoff.phoneprettifier.model.DigitsArray;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PhoneMincerTest implements ArrayUtilsTrait {

  @Test
  public void testBasicCase() throws Exception {
    Dictionary dictionary = new Dictionary()
      .addWords(Arrays.asList("cat", "luv", "ab", "ultu"));

    PhoneMincer mincer = new PhoneMincer(dictionary);
    DigitsArray phoneNumber = new DigitsArray(ints(2, 2, 8, 5, 8, 8));

    Chunk tree = mincer.process(phoneNumber);
    ChunkPrinter printer = new ChunkPrinter();
    List<String> result = printer.print(tree);
    assertThat(result).containsOnly(
      "CAT-LUV", "AB-ULTU"
    );
  }
}
