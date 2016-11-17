package org.kirhgoff.phoneprettifier.mechanics;

import org.kirhgoff.phoneprettifier.chunk.Chunk;
import org.kirhgoff.phoneprettifier.chunk.DeadEndChunk;

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
    String string = chunk.toString();
    if (!(chunk instanceof DeadEndChunk) && chunk.hasNoChildren()) {
      result.add(previousPath + string);
    } else {
      for (Chunk child : chunk.getChildren()) {
        String newPath; //TODO refactor this
        if (string.length() == 0) {
          newPath = previousPath;
        } else {
          newPath = previousPath + string + DELIMITER;
        }
        printRecursive(newPath, child, result);
      }
    }
  }
}
