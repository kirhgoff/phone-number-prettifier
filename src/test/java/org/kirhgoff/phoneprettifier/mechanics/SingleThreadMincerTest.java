package org.kirhgoff.phoneprettifier.mechanics;

import org.kirhgoff.phoneprettifier.ArrayUtilsTrait;
import org.kirhgoff.phoneprettifier.chunk.Chunk;
import org.kirhgoff.phoneprettifier.model.DigitsArray;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SingleThreadMincerTest implements ArrayUtilsTrait {
  private ChunkPrinter printer = new ChunkPrinter();

  @Test
  public void testBasicCase() throws Exception {
    Dictionary dictionary = new Dictionary()
      .addWords(Arrays.asList("cat", "luv", "ab", "ultu"));
    Mincer mincer = new SingleThreadMincer(dictionary);

    checkMincer(mincer, digits(2, 2, 2), "CA2", "2-AB");
    checkMincer(mincer, digits(2, 2, 8), "CAT", "2-A8");
    checkMincer(mincer, digits(5, 8, 8), "LUV");

    checkMincer(mincer, digits(2, 2, 2, 2), "AB-AB", "2-CA2");
  }

  private void checkMincer(
    Mincer mincer, DigitsArray phoneNumber, String ... strings)
    throws InterruptedException {
    Chunk tree = mincer.process(phoneNumber);
    List<String> result = printer.print(tree);
    assertThat(result).containsOnly(strings);
  }
}
