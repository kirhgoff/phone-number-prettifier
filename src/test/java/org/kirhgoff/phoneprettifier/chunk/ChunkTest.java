package org.kirhgoff.phoneprettifier.chunk;

import org.kirhgoff.phoneprettifier.ArrayUtilsTrait;
import org.kirhgoff.phoneprettifier.model.Diff;
import org.testng.annotations.Test;

import java.util.function.Supplier;

import static org.testng.Assert.fail;

public class ChunkTest implements ArrayUtilsTrait {
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

  private void checkInvalid(Supplier<Chunk> supplier) {
    try{
      supplier.get();
      fail("Should fail");
    }
    catch (IllegalArgumentException | IndexOutOfBoundsException e) {}
  }

}
