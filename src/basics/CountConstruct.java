package basics;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

public class CountConstruct {

  /*
   * Write a function `countConstruct(target, wordBank)` that takes a target string
   * and array of string as arguments.
   * The function should return the number of ways that the target can be
   * constructed by concatenating elements of word bank array.
   * You may reuse element of wordBank as many times as needed.
   *
   * Ex. countConstruct(abcdef, [ab, abc, cd, def, abcd]) -> 1 because we can construct abcdef from [abc, def]
   * Ex. countConstruct(skateboard, [bo, rd, ate, t, ska, sk, boar]) -> 0, there is no way construct skateboard
   * Ex. countConstruct('', [cat, dog, mouse]) -> 1, because empty string can be constructed with any bank words.
   */

  /*
   * m=target.length, n=word bank length
   * Time complexity: O((n^m) * m) additional m due to substring operation
   * Space complexity: O(m*m) -> O(m^2) additional m due to substring operation
   */

  public static int countConstruct(String target, String[] wordBank) {
    if (Objects.equals(target, "")) {
      return 1;
    }
    int total = 0;
    for (String word : wordBank) {
      if (target.startsWith(word)) {
        String currentTarget = target.substring(word.length());
        int currentNumberWays = countConstruct(currentTarget, wordBank);
        total += currentNumberWays;
      }
    }
    return total;
  }

  /*
   * m=target.length, n=word bank length
   * Time complexity: O((n*m) * m) -> O(n * m^2) additional m due to substring operation
   * Space complexity: O(m*m) -> O(m^2) additional m due to substring operation
   */
  public static int countConstruct(String target, String[] wordBank, Map<String, Integer> memo) {
    if (memo.containsKey(target)) {
      return memo.get(target);
    }
    if (Objects.equals(target, "")) {
      return 1;
    }
    int total = 0;
    for (String word : wordBank) {
      if (target.startsWith(word)) {
        String currentTarget = target.substring(word.length());
        int currentNumberWays = countConstruct(currentTarget, wordBank, memo);
        total += currentNumberWays;
      }
    }
    memo.put(target, total);
    return total;
  }

  /*
   * m=target.length, n=word bank length
   * Time complexity: O((n*m) * m) -> O(n * m^2) additional m due to substring operation
   * Space complexity: O(m*m) -> O(m^2) additional m due to substring operation
   */
  public static int countConstruct2(String target, String[] wordBank) {
    Queue<String> queue = new LinkedList<>();
    queue.add(target);
    int total = 0;

    while (!queue.isEmpty()) {
      String lastRemainder = queue.poll();
      if (lastRemainder.isEmpty()) {
        total += 1;
      }
      for (String word : wordBank) {
        if (lastRemainder.startsWith(word)) {
          String currentTarget = lastRemainder.substring(word.length());
          // [if (!queue.contains(currentTarget))] queue skip (similar to memoization), I can not use this technique because I need to check all the combinations
          //in exercises such as howSum, bestSum, canSum, canConstruct we can skip some branches, because noo need to count or get all of the possibles combinations.
          //with target=eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef this will have out of memory error
          queue.add(currentTarget);
        }
      }
    }

    return total;
  }

  public static void main(String... args) {

    System.out.println("Result = " + countConstruct("purple",
        new String[]{"purp", "p", "ur", "le", "purpl"}));//2
    System.out.println("Result = " + countConstruct("abcdef",
        new String[]{"ab", "abc", "cd", "def", "abcd"}));//1
    System.out.println("Result = " + countConstruct("skateboard",
        new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"}));//0
    System.out.println("Result = " + countConstruct("enterapotentpot",
        new String[]{"a", "p", "ent", "enter", "ot", "o", "t"}));//4
    System.out.println("Result = " + countConstruct("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
        new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeeee"}, new HashMap<>()));//0
  }
}
