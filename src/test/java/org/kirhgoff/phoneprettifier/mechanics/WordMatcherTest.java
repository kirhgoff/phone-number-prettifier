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
    Dictionary dictionary = new Dictionary();
    dictionary.addWords(Arrays.asList("ad", "be", "c", "aj", "ddd"));
    WordMatcher matcher = new WordMatcher();

    DigitsArray phone = new DigitsArray(ints(2, 3, 4));
    Set<MatchResult> results = matcher.match(phone, dictionary);

    List<String> printed = results.stream()
        .map(i -> i.getHead().toString() + "-" + i.getRemainder().toString())
        .collect(Collectors.toList());

    assertThat(printed).containsOnly(
      "AD-[4]", "BE-[4]", //Unwraps multiword
      "C-[3, 4]",
      "A3-[4]" //one digit
    );

  }
}
