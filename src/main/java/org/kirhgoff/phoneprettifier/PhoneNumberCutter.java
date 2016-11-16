package org.kirhgoff.phoneprettifier;

import org.kirhgoff.phoneprettifier.chunk.Chunk;
import org.kirhgoff.phoneprettifier.model.NumberArray;

import java.util.Set;

public class PhoneNumberCutter {
  private Dictionary dictionary;

  public PhoneNumberCutter(Dictionary dictionary) {
    this.dictionary = dictionary;
  }

  public Set<Chunk> cut(NumberArray phoneNumber) {
    return null;
  }
}
