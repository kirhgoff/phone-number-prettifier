package org.kirhgoff.phoneprettifier.mechanics;

import org.kirhgoff.phoneprettifier.ArrayUtilsTrait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class LetterConverterTest implements ArrayUtilsTrait {
  private final LetterConverter converter = new LetterConverter();

  @DataProvider(name = "singleChar")
  public Object[][] getData() {
    return new Object[][]{
        {2, "abc"},
        {3, "def"},
        {4, "ghi"},
        {5, "jkl"},
        {6, "mno"},
        {7, "pqrs"},
        {8, "tuv"},
        {9, "wxyz"}
    };
  }

  @Test(dataProvider="singleChar")
  public void testConverterChar(int number, String string) {
    String allCases = string.toLowerCase() + string.toUpperCase();
    allCases.chars().mapToObj(i -> (char)i)
        .forEach(chr -> assertThat(converter.convertChar(chr)).isEqualTo(number));
  }

  @Test
  public void testIncorrectInput() throws Exception {
    try {
      converter.convertChar('#');
      fail("Should fail on incorrect input");
    } catch (IllegalArgumentException e) {}
  }

  @DataProvider(name = "words")
  public Object[][] getWordData() {
    return new Object[][]{
      {ints(3, 3, 3), "def"},
      {ints(2, 3, 4), "adh"},
    };
  }

  @Test(dataProvider="words")
  public void testConverterWord(int[] numbers, String word) {
     assertThat(converter.convertWord(word)).isEqualTo(numbers);
  }
}
