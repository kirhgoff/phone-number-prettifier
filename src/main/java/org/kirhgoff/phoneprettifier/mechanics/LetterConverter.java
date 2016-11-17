package org.kirhgoff.phoneprettifier.mechanics;

/**
 * Converts from letters to digits
 * correspondingly to specification
 */
class LetterConverter {
  private static final String [] ROWS = new String [] {
      "abcABC",
      "defDEF",
      "ghiGHI",
      "jklJKL",
      "mnoMNO",
      "pqrsPQRS",
      "tuvTUV",
      "wxyzWXYZ"
  };

  private final int [] asciiCharsToDigits = new int [128];

  LetterConverter() {
    for (int i = 0; i < ROWS.length; i ++) {
      char chars[] = ROWS[i].toCharArray();
      for (char chr : chars) {
        asciiCharsToDigits[(int)chr] = i + 2; //Dangerous conversion
      }
    }
  }

  int convertChar(char chr) {
    int index = (int)chr;
    if (index < 0 || index > asciiCharsToDigits.length) {
      throw new IllegalArgumentException("Cannot convert " + chr);
    }
    int result = asciiCharsToDigits[index];
    if (result == 0) {
      throw new IllegalArgumentException("Cannot convert " + chr);
    }
    return result;
  }

  int [] convertWord(String word) {
    if (word == null) {
      throw new IllegalArgumentException("Cannot convert null word");
    }

    char [] chars = word.toCharArray();
    int [] result = new int [chars.length];

    for (int i = 0; i < result.length; i ++) {
      result [i] = convertChar(chars[i]);
    }
    return result;
  }
}
