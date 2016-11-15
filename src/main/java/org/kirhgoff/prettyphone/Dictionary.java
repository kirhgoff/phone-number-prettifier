package org.kirhgoff.prettyphone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kirilllastovirya on 15/11/2016.
 */
public class Dictionary {
  private final List<String> words;

  public Dictionary(List<String> words) {
    this.words = new ArrayList<>(words);
  }

  public boolean containsExactly(String number) {
    return false;
  }
}
