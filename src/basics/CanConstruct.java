package basics;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

public class CanConstruct {

  /*
   * Write a function `canConstruct(target, wordBank)` that takes a target string
   * and array of string as arguments.
   * The function should return a boolean indicating whether or not the target can be
   * constructed by concatenating elements of word bank array.
   * You may reuse element of wordBank as many times as needed.
   *
   * Ex. canConstruct(abcdef, [ab, abc, cd, def, abcd]) -> true because we can construct abcdef from [abc, def]
   * Ex. canConstruct(skateboard, [bo, rd, ate, t, ska, sk, boar]) -> false, there is no way construct skateboard
   * Ex. canConstruct('', [cat, dog, mouse]) -> true, because empty string can be constructed with any bank words.
   */

  /*
   * m=target.length, n=word bank length
   * Time complexity: O((n^m) * m) additional m due to substring operation
   * Space complexity: O(m*m) -> O(m^2) additional m due to substring operation
   */

  public static boolean canConstruct(String target, String[] wordBank) {
    if (Objects.equals(target, "")) {
      return true;
    }

    for (String word : wordBank) {
      if (target.startsWith(word)) {
        String currentTarget = target.substring(word.length());
        if (canConstruct(currentTarget, wordBank)) {
          return true;
        }
      }
    }
    return false;
  }
  /*
   * m=target.length, n=word bank length
   * Time complexity: O((n*m) * m) -> O(n * m^2) additional m due to substring operation
   * Space complexity: O(m*m) -> O(m^2) additional m due to substring operation
   */

  public static boolean canConstruct(String target, String[] wordBank, Map<String, Boolean> memo) {
    if (memo.containsKey(target)) {
      return memo.get(target);
    }
    if (Objects.equals(target, "")) {
      return true;
    }

    for (String word : wordBank) {
      if (target.startsWith(word)) {
        String currentTarget = target.substring(word.length());
        if (canConstruct(currentTarget, wordBank, memo)) {
          memo.put(target, true);
          return true;
        }
      }
    }
    memo.put(target, false);
    return false;
  }

  /*
   * m=target.length, n=word bank length
   * Time complexity: O((n*m) * m) -> O(n * m^2) additional m due to substring operation
   * Space complexity: O(m*m) -> O(m^2) additional m due to substring operation
   */
  public static boolean canConstruct2(String target, String[] wordBank) {
    Queue<String> queue = new LinkedList<>();
    queue.add(target);

    while (!queue.isEmpty()) {
      String lastRemainder = queue.poll();
      if (lastRemainder.isEmpty()) {
        return true;
      }
      for (String word : wordBank) {
        if (lastRemainder.startsWith(word)) {
          String currentTarget = lastRemainder.substring(word.length());
          if (!queue.contains(currentTarget)) {// if already exist remainder Target in queue skip (similar to memoization)
            queue.add(currentTarget);
          }
        }
      }
    }

    return false;
  }

  public static void main(String... args) {

    System.out.println("Result = " + canConstruct("abcdef",
        new String[]{"ab", "abc", "cd", "def", "abcd"}));//true
    System.out.println("Result = " + canConstruct("skateboard",
        new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"}));//false
    System.out.println("Result = " + canConstruct("enterapotentpot",
        new String[]{"a", "p", "ent", "enter", "ot", "o", "t"}));//true
    System.out.println("Result = " + canConstruct("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
        new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeeee"}, new HashMap<>()));//false
  }
}
