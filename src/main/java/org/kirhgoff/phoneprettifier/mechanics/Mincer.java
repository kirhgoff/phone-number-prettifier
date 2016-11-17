package org.kirhgoff.phoneprettifier.mechanics;

import org.kirhgoff.phoneprettifier.chunk.Chunk;
import org.kirhgoff.phoneprettifier.model.DigitsArray;

public interface Mincer {
  Chunk process(DigitsArray phoneNumber);
}
