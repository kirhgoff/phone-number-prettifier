package org.kirhgoff.phoneprettifier.chunk;

import java.util.Collections;
import java.util.List;

public class ChunkPrinter {
  public List<String> print(Chunk chunk) {
    return Collections.singletonList(chunk.toString());
  }
}
