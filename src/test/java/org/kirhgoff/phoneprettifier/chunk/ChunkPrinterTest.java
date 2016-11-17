package org.kirhgoff.phoneprettifier.chunk;

import org.kirhgoff.phoneprettifier.ArrayUtilsTrait;
import org.kirhgoff.phoneprettifier.mechanics.ChunkPrinter;
import org.kirhgoff.phoneprettifier.model.Diff;
import org.testng.annotations.Test;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.fail;

public class ChunkPrinterTest implements ArrayUtilsTrait {
  private ChunkPrinter printer = new ChunkPrinter();

  @Test
  public void testInvalidChunks() throws Exception {
    checkInvalid(() -> new OneDigitChunk(16));
    checkInvalid(() -> new OneDigitChunk(-1));
    checkInvalid(() -> new ExactWordChunk(null));
    checkInvalid(() -> new PartiallyMatchedChunk(null,
      new Diff(true, ints())));
    checkInvalid(() -> new PartiallyMatchedChunk("aaa",
      new Diff(true, ints(1, 2, 3, 4))));
    checkInvalid(() -> new PartiallyMatchedChunk("aaa",
      new Diff(true, ints(12))));
  }

  @Test
  public void testOneDigitChunk() throws Exception {
    checkPrinter(new OneDigitChunk (2), "2");
  }

  @Test
  public void testWordChunk() throws Exception {
    checkPrinter(new ExactWordChunk("something"), "SOMETHING");
  }

  @Test
  public void testWordWithDigitChunk() throws Exception {
    checkPrinter(new PartiallyMatchedChunk("some",
      new Diff(true, ints(-1, 0, -1, 4))), "S0M4");
  }

  @Test
  public void testWithChildren() throws Exception {
    Chunk root = new OneDigitChunk(1);
    root.addChild(new OneDigitChunk(2));
    root.addChild(new ExactWordChunk("ceNTer"));
    root.addChild(new PartiallyMatchedChunk("right",
      new Diff(true, ints(4, -1, -1, -1, -1))));

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
