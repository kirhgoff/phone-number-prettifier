package org.kirhgoff.phoneprettifier.chunk;

import java.util.LinkedList;
import java.util.List;

public class ChunkPrinter {

  public static final String DELIMITER = "-";

  public List<String> print(Chunk chunk) {
    List<String> results = new LinkedList<>();
    printRecursive("", chunk, results);
    return results;
  }

  private void printRecursive(String previousPath, Chunk chunk, List<String> result) {
    if (chunk.hasNoChildren()) {
      result.add(previousPath + chunk.toString());
    } else {
      for (Chunk child : chunk.getChildren()) {
        String newPath = previousPath + chunk.toString() + DELIMITER;
        printRecursive(newPath, child, result);
      }
    }
  }
}
