package org.kirhgoff.phoneprettifier;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kirilllastovirya on 15/11/2016.
 */
public class DictionaryTest {

  @Test
  public void testDictionary() throws Exception {
    List<String> words = Arrays.asList("def", "fed", "defa");
    Dictionary dictionary = new Dictionary(words);
    //MultiWord word = dictionary.getWord(new int [] {3, 3, 3});

  }
}
