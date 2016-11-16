package org.kirhgoff.phoneprettifier.mechanics;

import org.kirhgoff.phoneprettifier.ArrayUtilsTrait;
import org.kirhgoff.phoneprettifier.model.MultiWord;
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
    Dictionary dictionary = new Dictionary();
    dictionary.addWords(words);

    //Existent values
    checkWordsFound(dictionary, ints(3, 3, 3), strings("def", "fed"));
    checkWordsFound(dictionary, ints(3, 4, 3, 2), strings("dhfa"));

    //No values
    checkWordNotFound(dictionary, ints(3, 3));
    checkWordNotFound(dictionary, ints());

    //Bad input
    try {
      checkWordsFound(dictionary, null, null);
      fail("Should not accept incorrect input");
    } catch (IllegalArgumentException e) {}
  }

  private void checkWordsFound(Dictionary dictionary, int[] number, String[] strings) {
    MultiWord multiWord = dictionary.wordFor(number);
    Set<String> variants = multiWord.getVariants();
    assertThat(variants).containsExactly(strings);
  }
  private void checkWordNotFound(Dictionary dictionary, int[] number) {
    assertThat(dictionary.wordFor(number)).isNull();
  }

}
