package org.kirhgoff.phoneprettifier;

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

}
