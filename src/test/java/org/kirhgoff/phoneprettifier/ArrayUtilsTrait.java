package org.kirhgoff.phoneprettifier;

import org.kirhgoff.phoneprettifier.model.DigitsArray;

/**
 * Created by kirilllastovirya on 15/11/2016.
 */
public interface ArrayUtilsTrait {

  default int [] ints (int ... values) {
    return values;
  }

  default String [] strings (String ... values) {
    return values;
  }

  default DigitsArray digits (int ... values) {
    return new DigitsArray(ints(values));
  }

}
