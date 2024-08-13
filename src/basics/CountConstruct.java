package basics;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
