package org.kirhgoff.prettyphone;

public class LetterConverter {
  private final String [] rows = new String [] {
      "abcABC",
      "defDEF",
      "ghiGHI",
      "jklJKL",
      "mnoMNO",
      "pqrsPQRS",
      "tuvTUV",
      "wxyzWXYZ"
  };

  private final int [] numbers = new int [128];

  public LetterConverter() {
    for (int i = 0; i < rows.length; i ++) {
      char chars[] = rows[i].toCharArray();
      for (char chr : chars) {
        numbers[(int)chr] = i + 2; //Dangerous conversion
      }
    }
  }

  public int convert(char chr) {
    int index = (int)chr;
    if (index < 0 || index > numbers.length) {
      throw new IllegalArgumentException("Cannot convert " + chr);
    }
    int result = numbers[index];
    if (result == 0) {
      throw new IllegalArgumentException("Cannot convert " + chr);
    }
    return result;
  }
}
