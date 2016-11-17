package org.kirhgoff.phoneprettifier.mechanics;

import org.kirhgoff.phoneprettifier.ArrayUtilsTrait;
import org.kirhgoff.phoneprettifier.model.MatchResult;
import org.kirhgoff.phoneprettifier.model.DigitsArray;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class WordMatcherTest implements ArrayUtilsTrait {

  @Test
  public void testSimpleMatching() throws Exception {
    //Full match
    checkMatches(
      digits(3, 6, 6, 5),
      strings("fool"),
      strings("FOOL-[]")
    );

    //One diff
    checkMatches(
      digits(3, 8, 6, 5),
      strings("fool"),
      strings("F8OL-[]")
    );

    //Two non-consecutive diffs
    checkMatches(
      digits(3, 8, 6, 4),
      strings("fool"),
      strings("F8O4-[]")
    );

    //No match
    checkMatches(
      digits(2, 8, 6, 4),
      strings("fool"),
      strings("#-[2, 8, 6, 4]")
    );
  }

  private void checkMatches(DigitsArray phone, String[] dictionaryContent, String[] expectedResult) {
    Dictionary dictionary = new Dictionary();
    dictionary.addWords(Arrays.asList(dictionaryContent));
    WordMatcher matcher = new WordMatcher();

    Set<MatchResult> results = matcher.match(phone, dictionary, false);

    List<String> printed = results.stream()
        .map(i -> i.getHead().toString() + "-" + i.getRemainder().toString())
        .collect(Collectors.toList());

    assertThat(printed).containsOnly(expectedResult);
  }
}
