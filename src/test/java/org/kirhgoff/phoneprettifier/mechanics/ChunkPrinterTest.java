package org.kirhgoff.phoneprettifier.mechanics;

import org.kirhgoff.phoneprettifier.ArrayUtilsTrait;
import org.kirhgoff.phoneprettifier.chunk.Chunk;
import org.kirhgoff.phoneprettifier.chunk.ExactWordChunk;
import org.kirhgoff.phoneprettifier.chunk.OneDigitChunk;
import org.kirhgoff.phoneprettifier.chunk.PartiallyMatchedChunk;
import org.kirhgoff.phoneprettifier.model.Diff;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChunkPrinterTest implements ArrayUtilsTrait {
  private ChunkPrinter printer = new ChunkPrinter();

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
}
