package org.kirhgoff.phoneprettifier.chunk;

import org.testng.annotations.Test;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.fail;

public class ChunkPrinterTest {
  private ChunkPrinter printer = new ChunkPrinter();

  @Test
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

  @Test
  public void testOneDigitChunk() throws Exception {
    checkPrinter(new OneDigitChunk (2), "2");
  }

  @Test
  public void testWordChunk() throws Exception {
    checkPrinter(new WordChunk ("something"), "SOMETHING");
  }

  @Test
  public void testWordWithDigitChunk() throws Exception {
    checkPrinter(new WordWithDigitChunk ("something", 1, 0), "S0METHING");
  }

  @Test
  public void testWithChildren() throws Exception {
    Chunk root = new OneDigitChunk(1);
    root.addChild(new OneDigitChunk(2));
    root.addChild(new WordChunk("ceNTer"));
    root.addChild(new WordWithDigitChunk("right", 0, 4));

    checkPrinter(root, "1-2", "1-CENTER", "1-4IGHT");
  }

  private void checkPrinter(Chunk chunk, String ... something) {
    assertThat(printer.print(chunk)).containsOnly(something);
  }

  private void checkInvalid(Supplier<Chunk> supplier) {
    try{
      supplier.get();
      fail("Should fail");
    }
    catch (IllegalArgumentException | IndexOutOfBoundsException e) {}
  }
}
