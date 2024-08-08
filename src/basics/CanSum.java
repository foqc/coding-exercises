package basics;

import java.util.HashMap;
import java.util.Map;

public class CanSum {

  /*
   * Write a function `canSum(targetSum, numbers)` that takes in a targetSum
   * and array of numbers as arguments.
   * The function should return a boolean indicating whether or not it is possible
   * to generate the targetSum using numbers from the array.
   * You may use an element of the array as many times as needed.
   * You may assume tha all input numbers are no negative.
   *
   * Ex. canSum(7, [5, 3, 4, 7]) -> true because 7 and 3+4 are  = 7
   * Ex. canSum(7, [2, 4]) -> false because there is no way to sum 7
   */

  /*
   * m=targetSum, n=array length
   * Time complexity: O(n^m)
   * Space complexity: O(m)
   */
  public static boolean canSum(int targetSum, int[] numbers) {
    if (targetSum == 0) {
      return true;
    }
    if (targetSum < 0) {
      return false;
    }

    for (int number : numbers) {
      int remainder = targetSum - number;
      if (canSum(remainder, numbers)) {
        return true;
      }
    }

    return false;
  }


  /*
   * m=targetSum, n=array length
   * Time complexity: O(m*n)
   * Space complexity: O(m)
   */
  public static boolean canSum(int targetSum, int[] numbers, Map<Integer, Boolean> memo) {
    if (memo.containsKey(targetSum)) {
      return memo.get(targetSum);
    }

    if (targetSum == 0) {
      return true;
    }
    if (targetSum < 0) {
      return false;
    }

    for (int number : numbers) {
      int remainder = targetSum - number;
      if (canSum(remainder, numbers, memo)) {
        memo.put(targetSum, true);
        return true;
      }
    }
    memo.put(targetSum, false);
    return false;
  }

  public static void main(String... args) {

    System.out.println("Result = " + canSum(7, new int[]{2, 3}));//true
    System.out.println("Result = " + canSum(7, new int[]{5, 3, 4, 7}));//true
    System.out.println("Result = " + canSum(7, new int[]{2, 4}));//false
    System.out.println("Result = " + canSum(8, new int[]{2, 3, 5}));//true
    System.out.println("Result = " + canSum(300, new int[]{7, 14}, new HashMap<>()));//false
  }

}
