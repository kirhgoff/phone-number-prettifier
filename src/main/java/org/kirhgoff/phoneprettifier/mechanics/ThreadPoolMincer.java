package org.kirhgoff.phoneprettifier.mechanics;

import org.kirhgoff.phoneprettifier.chunk.Chunk;
import org.kirhgoff.phoneprettifier.chunk.DeadEndChunk;
import org.kirhgoff.phoneprettifier.chunk.WordChunk;
import org.kirhgoff.phoneprettifier.model.DigitsArray;
import org.kirhgoff.phoneprettifier.model.MatchResult;

import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

//TODO need to make Chunks thread safe, later if needed
public class ThreadPoolMincer implements Mincer {
  //TODO use ForkJoinPool
  private final ThreadPoolExecutor executor
    = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);

  private Dictionary dictionary;
  private final WordMatcher matcher = new WordMatcher();

  public ThreadPoolMincer(Dictionary dictionary) {
    this.dictionary = dictionary;
  }

  public void shutdown () {
    executor.shutdownNow();
  }

  public Chunk process(DigitsArray phoneNumber) throws InterruptedException {
    Chunk root = new WordChunk("");
    executor.execute(new MincerTask(root, phoneNumber));
    while (executor.getActiveCount() != 0) {
      Thread.sleep(100);
    }

    return root;
  }

  private class MincerTask implements Runnable {
    private final Chunk parent;
    private final DigitsArray phoneNumber;

    public MincerTask(Chunk parent, DigitsArray phoneNumber) {
      this.parent = parent;
      this.phoneNumber = phoneNumber;
    }

    @Override
    public void run() {
      Set<MatchResult> results = matcher.match(phoneNumber, dictionary);
      for (MatchResult match : results) {
        Chunk child = match.getHead();
        DigitsArray remainder = match.getRemainder();

        parent.addChild(child);
        if (!remainder.isEmpty() && !(child instanceof DeadEndChunk)) {
          executor.execute(new MincerTask(child, remainder));
        }
      }
    }
  }

}
