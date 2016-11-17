package org.kirhgoff.phoneprettifier.mechanics;

import org.kirhgoff.phoneprettifier.chunk.Chunk;
import org.kirhgoff.phoneprettifier.chunk.DeadEndChunk;
import org.kirhgoff.phoneprettifier.chunk.ExactWordChunk;
import org.kirhgoff.phoneprettifier.chunk.PartiallyMatchedChunk;
import org.kirhgoff.phoneprettifier.model.Diff;
import org.kirhgoff.phoneprettifier.model.DigitsArray;
import org.kirhgoff.phoneprettifier.model.MatchResult;
import org.kirhgoff.phoneprettifier.model.MultiWord;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

public class WordMatcher {

  public static final BiFunction<String, DigitsArray, Chunk> WORD_CHUNK_FACTORY
    = (str, arr) -> new ExactWordChunk(str);

  private static final DeadEndChunk DEAD_END_CHUNK = new DeadEndChunk();

  //TODO wrong, several numbers are allowed to be replaced
  public Set<MatchResult> match(DigitsArray array, Dictionary dictionary) {
    Set<MatchResult> results = new HashSet<>();

    for (MultiWord word : dictionary.getWords()) {
      //TODO again - reduce dictionary for phone size
      if (word.length() > array.length()) {
        continue;
      }

      Diff diff = array.compareWith(word.getNumberArray());
      if (!diff.areMatched()) {
        continue;
      }

      BiFunction<String, DigitsArray, Chunk> factory = null;
      if (diff.isExactMatch()) {
        factory = WORD_CHUNK_FACTORY;
      } else {
        //TODO should be very slow - check and rewrite
        factory = (str, arr) -> {
          return new PartiallyMatchedChunk(str, diff);
        };
      }

      for (String string : word.getVariants()) {
        //TODO suppress warning
        Chunk chunk = factory.apply(string, array);
        DigitsArray tail = array.bite(word.getNumberArray());
        results.add(new MatchResult(chunk, tail));
      }
    }

    if (results.isEmpty()) {
      results.add(new MatchResult(DEAD_END_CHUNK, array));
    }
    return results;
  }
}
