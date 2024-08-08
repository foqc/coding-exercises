package basics;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HowSum {
  /*
   * Write a function `howSum(targetSum, numbers)` that takes in a targetSum
   * and array of numbers as arguments.
   * The function should return an array containing any combination of elements that add up to
   * exactly targetSum. If there is no combination that ends up to the targetSum, the return null.
   * If there are multiple combination possible, you may return any single one.
   *
   * Ex. howSum(7, [5, 3, 4, 7]) -> [7] or [3,4] are  = 7
   * Ex. howSum(7, [2, 4]) -> null, there is no way to sum 7
   */

  public static Integer[] addX(int n, Integer[] arr, int x) {
    Integer[] newArr = new Integer[n + 1];
    if (n >= 0) {
      System.arraycopy(arr, 0, newArr, 0, n);
    }

    newArr[n] = x;

    return newArr;
  }

  /*
   * m=targetSum, n=array length
   * Time complexity: O(n^m*m) additional m because of copy array operation will take target sum in the worst case
   * Space complexity: O(m)
   */
  public static Integer[] howSum(int targetSum, int[] numbers) {
    if (targetSum == 0) {
      return new Integer[]{};
    }
    if (targetSum < 0) {
      return null;
    }

    for (int number : numbers) {
      int remainder = targetSum - number;
      Integer[] remainResult = howSum(remainder, numbers);
      if (remainResult != null) {
        return addX(remainResult.length, remainResult, number);
      }
    }

    return null;
  }

  /*
   * m=targetSum, n=array length
   * Time complexity: O(n*m*m) => O(n*m^2) additional m because of copy array operation will take target sum in the worst case
   * Space complexity: O(m*m) =< O(m^2) additional m because it stores m keys with m arrays
   */
  public static Integer[] howSum(int targetSum, int[] numbers,
      Map<Integer, Integer[]> memo) {
    if (memo.containsKey(targetSum)) {
      return memo.get(targetSum);
    }

    if (targetSum == 0) {
      return new Integer[]{};
    }
    if (targetSum < 0) {
      return null;
    }

    for (int number : numbers) {
      int remainder = targetSum - number;
      Integer[] remainResult = howSum(remainder, numbers, memo);
      if (remainResult != null) {
        memo.put(targetSum, addX(remainResult.length, remainResult, number));
        return memo.get(targetSum);
      }
    }
    memo.put(targetSum, null);
    return null;
  }

  public static String arrayToString(Integer[] list) {
    if (list == null) {
      return "null";
    }
    return Arrays.stream(list).mapToInt(Integer::valueOf)
        .mapToObj(String::valueOf)
        .collect(Collectors.joining(" - "));
  }

  public static void main(String... args) {

    System.out.println("Result = " + arrayToString(howSum(7, new int[]{2, 3})));//[3,2,2]
    System.out.println("Result = " + arrayToString(howSum(7, new int[]{5, 3, 4, 7})));//[4,3]
    System.out.println("Result = " + arrayToString(howSum(7, new int[]{2, 4})));//null
    System.out.println("Result = " + arrayToString(howSum(8, new int[]{2, 3, 5})));//[2, 2, 2, 2]
    System.out.println(
        "Result = " + arrayToString(howSum(300, new int[]{7, 14}, new HashMap<>())));//null

  }
}
