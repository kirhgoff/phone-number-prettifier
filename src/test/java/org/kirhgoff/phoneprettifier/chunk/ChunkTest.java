package org.kirhgoff.phoneprettifier.chunk;

import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChunkTest {
  private ChunkPrinter printer = new ChunkPrinter();

  @Test(enabled = false)
  public void testOneDigitChunk() throws Exception {
    checkPrinter(new OneDigitChunk (2), "something");
  }

  @Test(enabled = false)
  public void testWordChunk() throws Exception {
    checkPrinter(new WordChunk ("something"), "something");
  }

  @Test(enabled = false)
  public void testWordWithDigitChunk() throws Exception {
    checkPrinter(new WordWithDigitChunk ("something", 1, 0), "s0mething");
  }

  private void checkPrinter(Chunk chunk, String ... something) {
    assertThat(printer.print(chunk)).containsExactly(something);
  }

}
