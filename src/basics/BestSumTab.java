package basics;

import basics.HowSumTab.ArrayData;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BestSumTab {

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

  public static String arrayToString(List<Integer> list) {
    if (list == null) {
      return "null";
    }
    return list.stream().map(String::valueOf)
        .collect(Collectors.joining(" - ", "[ ", " ]"));
  }

  /*
   * m=targetSum, n=array length
   * Time complexity: O(m*m*n) -> O(m^2*n) additional m because of copy array operation will take target sum in the worst case
   * Space complexity: O(m*m) -> O(m^2) additional m because of additional sub array
   */
  public static List<Integer> bestSum(int targetSum, int[] numbers) {
    ArrayData[] table = new ArrayData[targetSum + 1];
    for (int i = 0; i <= targetSum; i++) {
      table[i] = null;
    }

    table[0] = new ArrayData();//if(targetSum == 0) return new Integer[]{} equivalent in recursive implementation

    for (int i = 0; i <= targetSum; i++) {
      if (table[i] == null) {// we only tabulate when is not null
        continue;
      }
      for (int number : numbers) {
        if (i + number <= targetSum) {
          List<Integer> newCombination = new ArrayList<>(table[i].list);
          newCombination.add(number);

          if (table[i + number] == null || table[i + number].list.size() > newCombination.size()) {
            table[i + number] = new ArrayData(
                newCombination);//copy previous position list in the [i + number] position
          }
        }
      }
    }

    return table[targetSum] == null ? null : table[targetSum].list;
  }

  public static void main(String... args) {

    System.out.println("Result = " + arrayToString(bestSum(7, new int[]{5, 3, 4, 7})));//[7]
    System.out.println("Result = " + arrayToString(bestSum(8, new int[]{2, 3, 5})));//[3,5]
    System.out.println("Result = " + arrayToString(bestSum(8, new int[]{1, 4, 5})));//[4,4]
    System.out.println(
        "Result = " + arrayToString(bestSum(100, new int[]{1, 2, 5, 25})));//[25, 25, 25, 25]

  }
}
