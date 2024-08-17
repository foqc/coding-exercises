package basics;

public class CanConstructTab {

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
   * Time complexity: O((n*m) * m) -> O(n * m^2) additional m due to substring operation
   * Space complexity: O(m)
   */

  public static boolean canConstruct(String target, String[] wordBank) {
    // word bank array will not change (no need to shrink or add elements) so only target length, will contribute to the tabulation process, so we create a 1D array.
    boolean[] table = new boolean[target.length() + 1];
    for (int i = 0; i <= target.length(); i++) {
      table[i] = false;
    }

    table[0] = true;// represents if(Objects.equals(target, "")) return true in recursive implementation
    for (int i = 0; i <= target.length(); i++) {
      if (!table[i]) {// we only tabulate when is true
        continue;
      }
      for (String word : wordBank) {
        //if the word matches the characters starting at position i
        if (target.startsWith(word, i)) {// in js target.slice(i, i + target.length()) === word
          table[i + word.length()] = true;
        }
      }
    }

    return table[target.length()];
  }

  public static void main(String... args) {

    System.out.println("Result = " + canConstruct("abcdef",
        new String[]{"ab", "abc", "cd", "def", "abcd"}));//true
    System.out.println("Result = " + canConstruct("skateboard",
        new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"}));//false
    System.out.println("Result = " + canConstruct("enterapotentpot",
        new String[]{"a", "p", "ent", "enter", "ot", "o", "t"}));//true
    System.out.println("Result = " + canConstruct("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef",
        new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeeee"}));//false
  }
}
