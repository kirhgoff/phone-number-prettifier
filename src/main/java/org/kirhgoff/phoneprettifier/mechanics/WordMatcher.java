package org.kirhgoff.phoneprettifier.mechanics;

import org.kirhgoff.phoneprettifier.model.MatchResult;
import org.kirhgoff.phoneprettifier.model.DigitsArray;

import java.util.List;
import java.util.stream.Collectors;

import static org.kirhgoff.phoneprettifier.model.Diff.EQUAL;

public class WordMatcher {
  public List<MatchResult> match(DigitsArray array, Dictionary dictionary) {
    return dictionary.getWords().stream()
      .filter(w -> (w.length() <= array.length()))
      .filter(w -> array.compareWith(w.getNumberArray()) == EQUAL)
      .map(w -> new MatchResult(w, array.bite(w.getNumberArray())))
      .collect(Collectors.toList());
  }
}
