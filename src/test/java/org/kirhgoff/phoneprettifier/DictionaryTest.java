package org.kirhgoff.phoneprettifier;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.fail;

public class DictionaryTest {

  @Test(enabled = false)
  public void testDictionaryCreation() throws Exception {
    List<String> words = Arrays.asList("def", "fed", "defa");
    LetterConverter converter = new LetterConverter();
    Dictionary dictionary = new Dictionary(converter);
    dictionary.addWords(words);

    //Existent values
    check(dictionary, new int[]{3, 3, 3}, new String[]{"def", "fed"});
    check(dictionary, new int[]{3, 3, 3, 4}, new String[]{"defa"});

    //No values
    check(dictionary, new int[]{3, 3}, new String[]{});
    check(dictionary, new int[]{}, new String[]{});

    //Bad input
    try {
      check(dictionary, null, null);
      fail("Should not accept incorrect input");
    } catch (IllegalArgumentException e) {}
  }

  private void check(Dictionary dictionary, int[] number, String[] strings) {
    MultiWord multiWord = dictionary.wordFor(number);
    List<String> variants = multiWord.getVariants();
    assertThat(variants).containsExactly(strings);
  }
}
