package org.kirhgoff.phoneprettifier;

import org.kirhgoff.phoneprettifier.chunk.Chunk;
import org.kirhgoff.phoneprettifier.mechanics.*;
import org.kirhgoff.phoneprettifier.model.DigitsArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Prettifier {
  private static ChunkPrinter printer = new ChunkPrinter();

  private static class Options {
    String dictionaryPath;
    List<String> phoneNumbersSources = new LinkedList<>();
  }

  public static void main(String[] args) throws IOException {
    println("Phone numbers prettifier v1.0");
    println("=============================");

    Options options = parseOptions(args);

    Dictionary dictionary = Dictionary.fromFile(options.dictionaryPath);
    final Mincer mincer = new SingleThreadMincer(dictionary);

    if (!options.phoneNumbersSources.isEmpty()) {
      readLinesFrom(options.phoneNumbersSources).stream()
        .map(Prettifier::parseNumber)
        .forEach(array -> {
          println("------------------------------------------");
          processPhoneNumber(array, mincer);
        });
    } else {
      runInteractiveLoop(mincer);
    }
  }

  private static void runInteractiveLoop(Mincer mincer) {
    Scanner scanner = new Scanner(System.in);
    println("Switched to interactive mode, press Ctrl+C to quit");
    while (true) {
      try {
        println("------------------------------------------");
        println("Enter a phone number:");
        String number = flattenValidate(scanner.nextLine());
        if (number.isEmpty()) {
          println("Number could contain at least some digits, heh?");
        } else {
          processPhoneNumber(parseNumber(number), mincer);
        }
      } catch (Exception e) {
        println("Caught exception: " + e.getMessage());
        break;
      }
    }
  }

  private static void processPhoneNumber(DigitsArray array, Mincer mincer) {
    long started = System.currentTimeMillis();
    try {
      println("Processing phone number: " + array);
      Chunk chunk = mincer.process(array);
      List<String> results = printer.print(chunk);

      results.forEach(Prettifier::println);

      println("Printed " + results.size() + " variants.");
    } finally {
      println ("Finished in " + (System.currentTimeMillis() - started) + " millis");
    }
  }

  //A little bit clumsy
  static DigitsArray parseNumber(String number) {
    int [] ints = new int [number.length()];
    for (int i = 0; i < number.length(); i ++) {
      ints [i] = Character.getNumericValue(number.charAt(i));
    }
    return new DigitsArray(ints);
  }

  private static List<String> readLinesFrom(List<String> files) {
    return files.stream()
      .flatMap(name -> {
        try {
          return Files.lines(Paths.get(name));
        } catch (Exception e) {
          println("Cannot read file " + name);
          return Stream.empty();
        }
      })
      .map(Prettifier::flattenValidate)
      .filter(str -> str != null && str.length() > 0)
      .collect(Collectors.toList());
  }

  static String flattenValidate(String line) {
    if (line == null) {
      throw new IllegalArgumentException("Should not be null");
    }
    return line.replaceAll("[^0-9]", "");
  }

  static Options parseOptions(String[] args) {
    Options options = new Options ();
    options.dictionaryPath = "dictionary.txt";

    for (int i = 0; i < args.length; i ++) {
      if (args[i].equals("-d")) {
        if (args.length == i + 1) {
          printUsageAndExit();
        } else {
          options.dictionaryPath = args[++i];
        }
      } else {
        options.phoneNumbersSources.add(args[i]);
      }
    }
    return options;
  }

  private static void printUsageAndExit() {
    println("Required parameters are: [-d dictionaryPath] [phoneNumbersFilePath]+");
    System.exit(0);
  }

  private static void println(String string) {
    System.out.println(string);
  }

}
