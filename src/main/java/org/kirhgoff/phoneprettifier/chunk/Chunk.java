package org.kirhgoff.phoneprettifier.chunk;


import java.util.List;

public interface Chunk {

  void addChild(Chunk child);
  List<Chunk> getChildren();

  default void assertIsDigit(int digit) {
    if (digit < 0 || digit > 9) {
      throw new IllegalArgumentException("This is no digit: " + digit);
    }
  }

  default void assertNotNull(String word) {
    if (word == null) {
      throw new IllegalArgumentException("Do not accept null word");
    }
  }

  default void assertNotEmpty(String word) {
    if (word.length() == 0) {
      throw new IllegalArgumentException("Cannot accept empty word");
    }
  }

  default void assertBounds(String word, int index) {
    if (index < 0 || index >= word.length()) {
      throw new IndexOutOfBoundsException("word is: " + word + ", index=" + index);
    }
  }



}
