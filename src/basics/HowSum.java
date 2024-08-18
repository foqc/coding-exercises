package basics;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
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

  public static class Data {

    Integer[] elements;
    Integer total;

    Data(Integer[] elements, Integer total) {
      this.elements = elements;
      this.total = total;
    }
  }

  /*
   * Using BSF technique (this will search the shortest path [best path] first)
   * m=targetSum, n=array length
   * Time complexity: O(n*m*m) => O(n*m^2) additional m because of copy array operation will take target sum in the worst case
   * Space complexity: O(m*n) -> slightly slow than memoized solution, because it will add m plus n length of remainder values to the queue
   */
  public static Integer[] howSum2(int targetSum, int[] numbers) {
    Queue<Data> queue = new LinkedList<>();
    queue.add(new Data(new Integer[]{}, 0));

    while (!queue.isEmpty()) {
      Data lastData = queue.poll();
      Integer lastTotal = lastData.total;
      if (lastTotal == targetSum) {
        return lastData.elements;
      }
      if (lastTotal > targetSum) {
        return null;
      }
      for (int number : numbers) {
        int total = lastTotal + number;
        if (queue.stream().noneMatch(item -> item.total
            == total)) {// if already exist total in queue skip (similar to memoization)
          queue.add(
              new Data(addX(lastData.elements.length, lastData.elements, number), total));
        }
      }
    }

    return null;
  }

  public static void main(String... args) {

    System.out.println("Result = " + arrayToString(howSum2(7, new int[]{2, 3})));//[3,2,2]
    System.out.println("Result = " + arrayToString(howSum2(7, new int[]{5, 3, 4, 7})));//[4,3]
    System.out.println("Result = " + arrayToString(howSum2(7, new int[]{2, 4})));//null
    System.out.println("Result = " + arrayToString(howSum2(8, new int[]{2, 3, 5})));//[2, 2, 2, 2]
    System.out.println(
        "Result = " + arrayToString(howSum2(300, new int[]{7, 14})));//null

  }
}
