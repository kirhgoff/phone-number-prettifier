package org.kirhgoff.phoneprettifier.chunk;

import org.testng.annotations.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.fail;

public class ChunkTest {
  private ChunkPrinter printer = new ChunkPrinter();

  @Test(enabled = false)
  public void testInvalidChunks() throws Exception {
    checkInvalid(() -> new OneDigitChunk(16));
    checkInvalid(() -> new OneDigitChunk(-1));
    checkInvalid(() -> new WordChunk(null));
    checkInvalid(() -> new WordWithDigitChunk(null, 1, 1));
    checkInvalid(() -> new WordWithDigitChunk("aaa", 666, 1));
    checkInvalid(() -> new WordWithDigitChunk("aaa", -1, 1));
    checkInvalid(() -> new WordWithDigitChunk("aaa", 1, -1));
    checkInvalid(() -> new WordWithDigitChunk("aaa", 1, 12));
  }

  private void checkInvalid(Supplier<Chunk> supplier) {
    try{
      supplier.get();
      fail("Should fail");
    } catch (IllegalArgumentException e) {}
  }

  @Test
  public void testOneDigitChunk() throws Exception {
    checkPrinter(new OneDigitChunk (2), "2");
  }

  @Test
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
