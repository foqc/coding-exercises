package basics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AllConstructTab {

  public static class ArrayData {

    public List<String[]> list;

    public ArrayData() {
      String[] emptyArray = new String[]{};
      list = new ArrayList<>(Collections.singleton(emptyArray));
    }

    public ArrayData(List<String[]> list) {
      this.list = list;
    }
  }

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


  public static String[] addElement(String[] arr, String x) {
    String[] newArr = new String[arr.length + 1];
    System.arraycopy(arr, 0, newArr, 0, arr.length);
    newArr[arr.length] = x;

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
   * Space complexity: O(n^m) because of 3d table
   */

  public static List<String[]> allConstruct(String target, String[] wordBank) {
    // word bank array will not change (no need to shrink or add elements) so only target length, will contribute to the tabulation process, so we create a 1D array.
    ArrayData[] table = new ArrayData[target.length() + 1];
    for (int i = 0; i < table.length; i++) {
      table[i] = new ArrayData(new ArrayList<>()); // init 1d array []
    }

    //init 2d empty array [[]]
    table[0] = new ArrayData();// represents if(Objects.equals(target, "")) return empty array
    for (int i = 0; i <= target.length(); i++) {

      for (String word : wordBank) {
        //if the word matches the characters starting at position i
        if (target.startsWith(word, i)) {// in js target.slice(i, i + target.length()) === word

          List<String[]> newCombinations = table[i].list.stream()
              .map(item -> addElement(item, word))
              .collect(Collectors.toList());

          table[i + word.length()].list.addAll(newCombinations);
        }
      }
    }

    return table[target.length()].list;
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
        "Result = " + show2DArray(allConstruct("eeeeeeeeeeeeeeeeeeeeeef",
            new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeeee"})));//[]
  }
}
