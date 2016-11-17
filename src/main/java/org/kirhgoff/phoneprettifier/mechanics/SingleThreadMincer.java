package org.kirhgoff.phoneprettifier.mechanics;

import org.kirhgoff.phoneprettifier.chunk.Chunk;
import org.kirhgoff.phoneprettifier.chunk.DeadEndChunk;
import org.kirhgoff.phoneprettifier.chunk.ExactWordChunk;
import org.kirhgoff.phoneprettifier.chunk.OneDigitChunk;
import org.kirhgoff.phoneprettifier.model.DigitsArray;
import org.kirhgoff.phoneprettifier.model.MatchResult;

import java.util.Set;

/**
 * Starts cutting number word by word and build chunks tree
 */
public class SingleThreadMincer implements Mincer {
  private Dictionary dictionary;
  private final WordMatcher matcher = new WordMatcher();

  public SingleThreadMincer(Dictionary dictionary) {
    this.dictionary = dictionary;
  }

  @Override
  public Chunk process(DigitsArray phoneNumber) {
    Chunk root = new ExactWordChunk("");
    processInternal(root, phoneNumber);
    return root;
  }

  private void processInternal(Chunk parent, DigitsArray phoneNumber) {
    Set<MatchResult> results = matcher
      .match(phoneNumber, dictionary, parent.lastCharIsDigit());

    for (MatchResult match : results) {
      Chunk child = match.getHead();
      DigitsArray remainder = match.getRemainder();

      parent.addChild(child);
      if (!remainder.isEmpty() && !(child instanceof DeadEndChunk)) {
        processInternal(child, remainder);
      }
    }

    // Shift one digit and try again
    // Not allowed if we already shifted or
    // Last chunks last symbol is digit
    if (phoneNumber.length() > 1 &&
      !(parent instanceof OneDigitChunk) &&
      !parent.lastCharIsDigit())
    {
      Chunk digit = new OneDigitChunk(phoneNumber.digitAt(0));
      parent.addChild(digit);
      processInternal(digit, phoneNumber.bite(1));
    }
  }
}
