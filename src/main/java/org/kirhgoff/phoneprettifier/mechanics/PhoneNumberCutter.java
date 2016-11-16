package org.kirhgoff.phoneprettifier.mechanics;

import org.kirhgoff.phoneprettifier.chunk.Chunk;
import org.kirhgoff.phoneprettifier.model.DigitsArray;

import java.util.Set;

public class PhoneNumberCutter {
  private Dictionary dictionary;

  public PhoneNumberCutter(Dictionary dictionary) {
    this.dictionary = dictionary;
  }

  public Set<Chunk> cut(DigitsArray phoneNumber) {
    return null;
  }
}
