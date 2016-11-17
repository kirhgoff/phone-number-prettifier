package org.kirhgoff.phoneprettifier.mechanics;

import org.kirhgoff.phoneprettifier.chunk.Chunk;
import org.kirhgoff.phoneprettifier.chunk.DeadEndChunk;

import java.util.LinkedList;
import java.util.List;

/**
 * Takes a chunks tree and collects all the paths
 * from root to every leaf, except DeadEnd leafs
 */
public class ChunkPrinter {
  private static final String DELIMITER = "-";

  public List<String> print(Chunk chunk) {
    List<String> results = new LinkedList<>();
    printRecursive("", chunk, results);
    return results;
  }

  private void printRecursive(String previousPath, Chunk chunk, List<String> result) {
    String string = chunk.toString();
    if (!(chunk instanceof DeadEndChunk) && chunk.hasNoChildren()) {
      result.add(previousPath + string);
    } else {
      for (Chunk child : chunk.getChildren()) {
        String newPath;
        if (string.length() == 0) {
          newPath = previousPath; //First call case
        } else {
          newPath = previousPath + string + DELIMITER;
        }
        printRecursive(newPath, child, result);
      }
    }
  }
}
