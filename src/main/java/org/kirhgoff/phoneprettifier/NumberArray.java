package org.kirhgoff.phoneprettifier;

import java.util.Arrays;

/**
 * Created by kirilllastovirya on 15/11/2016.
 */
public class NumberArray {
  private final int [] data;

  public NumberArray(int[] data) {
    this.data = Arrays.copyOf(data, data.length);
  }


}
