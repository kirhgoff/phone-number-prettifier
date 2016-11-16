package org.kirhgoff.phoneprettifier.chunk;

import java.util.LinkedList;
import java.util.List;

public class ChunkBase implements Chunk {
  private List<Chunk> children = new LinkedList<>();

  @Override
  public void addChild(Chunk child) {
    children.add(child);
  }

  @Override
  public List<Chunk> getChildren() {
    return children;
  }

}
