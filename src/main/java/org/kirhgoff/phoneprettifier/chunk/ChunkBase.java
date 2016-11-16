package org.kirhgoff.phoneprettifier.chunk;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ChunkBase implements Chunk {
  private List<Chunk> children = new LinkedList<>();
  private List<Chunk> view = Collections.unmodifiableList(children);

  @Override
  public void addChild(Chunk child) {
    children.add(child);
  }

  @Override
  public List<Chunk> getChildren() {
    return view;
  }

}
