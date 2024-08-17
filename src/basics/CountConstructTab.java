package basics;

public class CountConstructTab {
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
   * Time complexity: O((n*m) * m) -> O(n * m^2) additional m due to substring operation
   * Space complexity: O(m*m) -> O(m^2) additional m due to substring operation
   */
  public static int countConstruct(String target, String[] wordBank) {
    // word bank array will not change (no need to shrink or add elements) so only target length, will contribute to the tabulation process, so we create a 1D array.
    int[] table = new int[target.length() + 1];

    table[0] = 1;// represents if(Objects.equals(target, "")) return 1 in recursive implementation
    for (int i = 0; i <= target.length(); i++) {
      if (table[i] == 0) {// we only tabulate when not 0
        continue;
      }
      for (String word : wordBank) {
        //if the word matches the characters starting at position i
        if (target.startsWith(word, i)) {// in js target.slice(i, i + target.length()) === word
          table[i + word.length()] += table[i];
        }
      }
    }

    return table[target.length()];
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
        new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeeee"}));//0
  }
}
