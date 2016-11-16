package org.kirhgoff.phoneprettifier.mechanics;

import org.kirhgoff.phoneprettifier.ArrayUtilsTrait;
import org.kirhgoff.phoneprettifier.model.MatchResult;
import org.kirhgoff.phoneprettifier.model.DigitsArray;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class WordMatcherTest implements ArrayUtilsTrait {

  @Test(enabled = false)
  public void testSimpleMatching() throws Exception {
    LetterConverter converter = new LetterConverter();
    Dictionary dictionary = new Dictionary(converter);
    dictionary.addWords(Arrays.asList("ad", "be", "c", "other"));
    WordMatcher matcher = new WordMatcher();

    DigitsArray phone = new DigitsArray(ints(2, 3, 4));
    List<MatchResult> results = matcher.match(phone, dictionary);

    List<String> printed = results.stream()
        .map(i -> i.getHead().toString() + "-" + i.getTail().toString())
        .collect(Collectors.toList());

    assertThat(printed).containsOnly(
      "ad-[4]", "be-[4]", //Unwraps multiword
      "c-[3, 4]"
    );

  }
}
