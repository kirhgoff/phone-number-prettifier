package org.kirhgoff.prettyphone;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

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
