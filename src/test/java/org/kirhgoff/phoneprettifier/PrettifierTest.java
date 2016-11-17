package org.kirhgoff.phoneprettifier;

import org.kirhgoff.phoneprettifier.model.DigitsArray;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.fail;

public class PrettifierTest implements ArrayUtilsTrait {
  @Test
  public void testFlatten() throws Exception {
    //Should remove any garbage
    checkFlatten("123", "123");
    checkFlatten("1sdf2bbb3", "123");
    checkFlatten("1s.,df2b@\tbb3", "123");
    checkFlatten("aaaa", "");
    checkFlatten("", "");

    try {
      checkFlatten(null, null);
      fail("Should fail");
    } catch (IllegalArgumentException e) {}
  }


  @Test
  public void testParseNumber() throws Exception {
    checkParse("12345", digits(1, 2, 3, 4, 5));
    checkParse("1", digits(1));
    checkParse("", digits());
  }

  private Object checkParse(String number, DigitsArray digits) {
    return assertThat(Prettifier.parseNumber(number)).isEqualTo(digits);
  }

  private Object checkFlatten(String source, String result) {
    return assertThat(Prettifier.flattenValidate(source)).isEqualTo(result);
  }
}
