package org.kirhgoff.phoneprettifier;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

public class LetterConverterTest {
  private final LetterConverter converter = new LetterConverter();

  @Test(dataProvider="fixtures")
  public void testConverter(int number, String string) {
    String allCases = string.toLowerCase() + string.toUpperCase();
    allCases.chars().mapToObj(i -> (char)i)
      .forEach(chr -> assertThat(converter.convert(chr)).isEqualTo(number));
  }

  @DataProvider(name = "fixtures")
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

  @Test
  public void testIncorrectInput() throws Exception {
    try {
      converter.convert('#');
      fail("Should fail on incorrect input");
    } catch (IllegalArgumentException e) {}
  }

}
