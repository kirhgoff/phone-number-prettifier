package org.kirhgoff.phoneprettifier.mechanics;

import org.kirhgoff.phoneprettifier.chunk.Chunk;
import org.kirhgoff.phoneprettifier.chunk.DeadEndChunk;
import org.kirhgoff.phoneprettifier.chunk.ExactWordChunk;
import org.kirhgoff.phoneprettifier.model.DigitsArray;
import org.kirhgoff.phoneprettifier.model.MatchResult;

import java.util.Set;

public class SingleThreadMincer implements Mincer {
  private Dictionary dictionary;
  private final WordMatcher matcher = new WordMatcher();

  public SingleThreadMincer(Dictionary dictionary) {
    this.dictionary = dictionary;
  }

  @Override
  public Chunk process(DigitsArray phoneNumber) throws InterruptedException {
    Chunk root = new ExactWordChunk("");
    processInternal(root, phoneNumber);
    return root;
  }

  private void processInternal(Chunk parent, DigitsArray phoneNumber) {
    Set<MatchResult> results = matcher.match(phoneNumber, dictionary);
    for (MatchResult match : results) {
      Chunk child = match.getHead();
      DigitsArray remainder = match.getRemainder();

      parent.addChild(child);
      if (!remainder.isEmpty() && !(child instanceof DeadEndChunk)) {
        processInternal(child, remainder);
      }
    }
  }
}
