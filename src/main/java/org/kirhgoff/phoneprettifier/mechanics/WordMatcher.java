package org.kirhgoff.phoneprettifier.mechanics;

import org.kirhgoff.phoneprettifier.chunk.Chunk;
import org.kirhgoff.phoneprettifier.chunk.WordChunk;
import org.kirhgoff.phoneprettifier.chunk.WordWithDigitChunk;
import org.kirhgoff.phoneprettifier.model.*;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

import static org.kirhgoff.phoneprettifier.model.Diff.EQUAL;
import static org.kirhgoff.phoneprettifier.model.Diff.MANY;
import static org.kirhgoff.phoneprettifier.model.Diff.SINGLE;

public class WordMatcher {

  public static final BiFunction<String, DigitsArray, Chunk> WORD_CHUNK_FACTORY
    = (str, arr) -> new WordChunk(str);

  public Set<MatchResult> match(DigitsArray array, Dictionary dictionary) {

    Set<MatchResult> results = new HashSet<>();
    //TODO shift for one symbol and check that the diff is not the first one
    for (MultiWord word : dictionary.getWords()) {
      Diff diff = array.compareWith(word.getNumberArray());
      if (diff == MANY) {
        continue;
      }

      BiFunction<String, DigitsArray, Chunk> factory = null;
      if (diff == EQUAL) {
        factory = WORD_CHUNK_FACTORY;
      } else if (diff == SINGLE) {
        //TODO should be very slow - check and rewrite
        factory = (str, arr) -> {
          int index = word.findDiffIndex(array);
          return new WordWithDigitChunk(str, index, arr.digitAt(index));
        };
      }

      for (String string : word.getVariants()) {
        //TODO suppress warning
        Chunk chunk = factory.apply(string, array);
        DigitsArray tail = array.bite(word.getNumberArray());
        results.add(new MatchResult(chunk, tail));
      }
    }

    return results;
  }
}
