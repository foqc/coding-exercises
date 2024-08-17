package basics;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class BestSum {
  /*
   * Write a function `bestSum(targetSum, numbers)` that takes in a targetSum
   * and array of numbers as arguments.
   * The function should return an array containing the shortest combination of elements that add up to
   * exactly targetSum. If there is no combination that ends up to the targetSum, the return null.
   * If there are multiple combination possible, you may return any single one.
   *
   * Ex. bestSum(7, [5, 3, 4, 7]) -> [7] is shortest 7
   * Ex. bestSum(7, [2, 4]) -> null, there is no way to sum 7
   */

  public static Integer[] addX(int n, Integer[] arr, int x) {
    Integer[] newArr = new Integer[n + 1];
    if (n >= 0) {
      System.arraycopy(arr, 0, newArr, 0, n);
    }

    newArr[n] = x;

    return newArr;
  }

  public static String arrayToString(Integer[] list) {
    if (list == null) {
      return "null";
    }
    return Arrays.stream(list).mapToInt(Integer::valueOf)
        .mapToObj(String::valueOf)
        .collect(Collectors.joining(" - "));
  }

  /*
   * m=targetSum, n=array length
   * Time complexity: O(n^m*m) additional m because of copy array operation will take target sum in the worst case
   * Space complexity: O(m*m) -> O(m^2) because it stores shortestCombination in each recursion
   */
  public static Integer[] bestSum(int targetSum, int[] numbers) {
    if (targetSum == 0) {
      return new Integer[]{};
    }
    if (targetSum < 0) {
      return null;
    }

    Integer[] shortestCombination = null;

    for (int number : numbers) {
      int remainder = targetSum - number;
      Integer[] remainResult = bestSum(remainder, numbers);
      if (remainResult != null) {
        Integer[] currentCombination = addX(remainResult.length, remainResult, number);
        if (shortestCombination == null || currentCombination.length < shortestCombination.length) {
          shortestCombination = currentCombination;
        }
      }
    }

    return shortestCombination;
  }

  /*
   * m=targetSum, n=array length
   * Time complexity: O(n*m^2) additional m because of copy array operation will take target sum in the worst case
   * Space complexity: O(m*m) -> O(m^2) because it stores shortestCombination in each recursion
   */
  public static Integer[] bestSum(int targetSum, int[] numbers, Map<Integer, Integer[]> memo) {
    if (memo.containsKey(targetSum)) {
      return memo.get(targetSum);
    }
    if (targetSum == 0) {
      return new Integer[]{};
    }
    if (targetSum < 0) {
      return null;
    }

    Integer[] shortestCombination = null;

    for (int number : numbers) {
      int remainder = targetSum - number;
      Integer[] remainResult = bestSum(remainder, numbers, memo);
      if (remainResult != null) {
        Integer[] currentCombination = addX(remainResult.length, remainResult, number);
        if (shortestCombination == null || currentCombination.length < shortestCombination.length) {
          shortestCombination = currentCombination;
        }
      }
    }

    memo.put(targetSum, shortestCombination);
    return shortestCombination;
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
  public static Integer[] bestSum2(int targetSum, int[] numbers) {
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
        queue.add(
            new Data(addX(lastData.elements.length, lastData.elements, number), total));
      }
    }

    return null;
  }

  public static void main(String... args) {

    System.out.println("Result = " + arrayToString(bestSum(7, new int[]{5, 3, 4, 7})));//[7]
    System.out.println("Result = " + arrayToString(bestSum(8, new int[]{2, 3, 5})));//[3,5]
    System.out.println("Result = " + arrayToString(bestSum(8, new int[]{1, 4, 5})));//[4,4]
    System.out.println(
        "Result = " + arrayToString(
            bestSum(100, new int[]{1, 2, 5, 25}, new HashMap<>())));//[25, 25, 25, 25]

  }
}
