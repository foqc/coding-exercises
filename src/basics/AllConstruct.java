package basics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

public class AllConstruct {
  /*
   * Write a function `allConstruct(target, wordBank)` that takes a target string
   * and array of string as arguments.
   * The function should return a 2D array containing all of the ways that target can be
   * constructed by concatenating elements of the wordBank array.
   *
   * Ex. allConstruct(abcdef, [ab, abc, cd, def, abcd]) -> [[abc, def]] we can construct abcdef from [abc, def]
   * Ex. allConstruct(skateboard, [bo, rd, ate, t, ska, sk, boar]) -> [], there is no way construct skateboard
   * Ex. allConstruct('', [cat, dog, mouse]) -> [[]], because empty string can be constructed with any bank words.
   */

  public static String[] addInFront(String[] arr, String x) {
    String[] newArr = new String[arr.length + 1];
    System.arraycopy(arr, 0, newArr, 1, arr.length);
    newArr[0] = x;

    return newArr;
  }

  public static String show2DArray(List<String[]> list) {
    if (list.isEmpty()) {
      return "[]";
    }
    return list.stream().map(item -> String.join(", ", item))
        .collect(Collectors.joining(" | "));
  }

  /*
   * m=target.length, n=word bank length
   * Time complexity: O((n^m) * m) additional m due to substring operation
   * Space complexity: O(m*m) -> O(m^2) additional m due to substring operation
   */

  public static List<String[]> allConstruct(String target, String[] wordBank) {
    if (Objects.equals(target, "")) {
      String[] emptyArray = new String[]{};
      return new ArrayList<>() {{
        add(emptyArray);
      }};
    }

    List<String[]> all = new ArrayList<>();
    for (String word : wordBank) {
      if (target.startsWith(word)) {
        String currentTarget = target.substring(word.length());
        List<String[]> currentWays = allConstruct(currentTarget, wordBank);
        List<String[]> currentUpdatedWays = currentWays.stream()
            .map(item -> addInFront(item, word)).collect(Collectors.toList());
        all.addAll(currentUpdatedWays);
      }
    }
    return all;
  }

  /*
   * m=target.length, n=word bank length
   * Time complexity: O((n^m) * m) additional m due to substring operation
   * Space complexity: O(m*m) -> O(m^2) additional m due to substring operation
   */

  public static List<String[]> allConstruct(String target, String[] wordBank,
      Map<String, List<String[]>> memo) {
    if (memo.containsKey(target)) {
      return memo.get(target);
    }

    if (Objects.equals(target, "")) {
      String[] emptyArray = new String[]{};
      return new ArrayList<>() {{
        add(emptyArray);
      }};
    }

    List<String[]> all = new ArrayList<>();
    for (String word : wordBank) {
      if (target.startsWith(word)) {
        String currentTarget = target.substring(word.length());
        List<String[]> currentWays = allConstruct(currentTarget, wordBank, memo);
        List<String[]> currentUpdatedWays = currentWays.stream()
            .map(item -> addInFront(item, word)).collect(Collectors.toList());
        all.addAll(currentUpdatedWays);
      }
    }
    memo.put(target, all);
    return all;
  }

  public static class Data {

    public List<String[]> list;
    public String remainder;

    public Data(String remainder) {
      String[] emptyArray = new String[]{};
      this.remainder = remainder;
      list = new ArrayList<>(Collections.singleton(emptyArray));
    }

    public Data(String remainder, List<String[]> list) {
      this.remainder = remainder;
      this.list = list;
    }
  }

  /*
   * m=target.length, n=word bank length
   * Time complexity: O((n^m) * m) additional m due to substring operation
   * Space complexity: O(n^m) because of 3d table
   */
  public static List<String[]> allConstruct2(String target, String[] wordBank) {
    Queue<Data> queue = new LinkedList<>();
    queue.add(new Data(target));
    List<String[]> all = new ArrayList<>();

    while (!queue.isEmpty()) {
      Data lastData = queue.poll();
      String lastRemainder = lastData.remainder;
      if (lastRemainder.isEmpty()) {
        all.addAll(lastData.list);
      }
      for (String word : wordBank) {
        if (lastRemainder.startsWith(word)) {
          String currentTarget = lastRemainder.substring(word.length());
          // [if (!queue.stream().noneMatch(item -> item.remainder.equals(currentTarget))))] queue skip (similar to memoization), I can not use this technique because I need to check all the combinations
          //in exercises such as howSum, bestSum, canSum, canConstruct we can skip some branches, because noo need to count or get all of the possibles combinations.
          //with target=eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef this will have out of memory error
          queue.add(
              new Data(currentTarget, lastData.list.stream().map(item -> addInFront(item, word))
                  .collect(Collectors.toList())));
        }
      }
    }

    return all;
  }

  public static void main(String... args) {

    System.out.println("Result = " + show2DArray(allConstruct("purple",
        new String[]{"purp", "p", "ur", "le", "purpl"})));//[purp, le] | [p, ur, p, le]
    System.out.println("Result = " + show2DArray(allConstruct("abcdef",
        new String[]{"ab", "abc", "cd", "def", "abcd"})));//[abc, def]
    System.out.println("Result = " + show2DArray(allConstruct("skateboard",
        new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"})));//[]
    System.out.println("Result = " + show2DArray(allConstruct("enterapotentpot",
        new String[]{"a", "p", "ent", "enter", "ot", "o", "t"})));//...
    System.out.println(
        "Result = " + show2DArray(allConstruct("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
            new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeeee"}, new HashMap<>())));//[]
  }
}
