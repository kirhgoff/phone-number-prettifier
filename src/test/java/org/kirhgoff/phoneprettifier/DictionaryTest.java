package org.kirhgoff.phoneprettifier;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.fail;

public class DictionaryTest implements ArrayUtilsTrait {

  @Test
  public void testDictionaryCreation() throws Exception {
    List<String> words = Arrays.asList("def", "fed", "dhfa");
    LetterConverter converter = new LetterConverter();
    Dictionary dictionary = new Dictionary(converter);
    dictionary.addWords(words);

    //Existent values
    check(dictionary, ints(3, 3, 3), strings("def", "fed"));
    check(dictionary, ints(3, 4, 3, 2), strings("dhfa"));

    //No values
    checkNull(dictionary, ints(3, 3));
    checkNull(dictionary, ints());

    //Bad input
    try {
      check(dictionary, null, null);
      fail("Should not accept incorrect input");
    } catch (IllegalArgumentException e) {}
  }

  private void check(Dictionary dictionary, int[] number, String[] strings) {
    MultiWord multiWord = dictionary.wordFor(number);
    Set<String> variants = multiWord.getVariants();
    assertThat(variants).containsExactly(strings);
  }
  private void checkNull(Dictionary dictionary, int[] number) {
    assertThat(dictionary.wordFor(number)).isNull();
  }

}
