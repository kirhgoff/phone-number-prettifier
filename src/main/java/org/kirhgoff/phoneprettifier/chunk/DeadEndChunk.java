package org.kirhgoff.phoneprettifier.chunk;

/**
 * Leaf for chunk tree that indicates
 * there are no available combinations are here
 */
public class DeadEndChunk extends ChunkBase {
  @Override
  public String toString() {
    return "#"; //Just in case
  }

  @Override
  public boolean lastCharIsDigit() {
    return false; //Does not matter
  }
}
