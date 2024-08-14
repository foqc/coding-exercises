package basics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HowSumTab {

  public static class ArrayData {

    public List<Integer> list;

    public ArrayData() {
      list = new ArrayList<>();
    }
  }
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

  public static String arrayToString(List<Integer> list) {
    if (list == null) {
      return "null";
    }
    return list.stream().map(String::valueOf)
        .collect(Collectors.joining(" - ", "[ ", " ]"));
  }

  /*
   * m=targetSum, n=array length
   * Time complexity: O(m*n*m) -> O(m^2*n) additional m because of copy array operation [new ArrayList<>(table[i].list)] will take target sum in the worst case
   * Space complexity: O(m*m) -> O(m^2) additional m because of additional sub array
   */
  public static List<Integer> howSum(int targetSum, int[] numbers) {
    ArrayData[] table = new ArrayData[targetSum + 1];
    for (int i = 0; i <= targetSum; i++) {
      table[i] = null;
    }

    table[0] = new ArrayData();//if(targetSum == 0) return [] equivalent in recursive implementation

    for (int i = 0; i <= targetSum; i++) {
      if (table[i] == null) {// we only tabulate when is not null
        continue;
      }
      for (int number : numbers) {
        if (i + number <= targetSum) {
          if (table[i + number] == null) {
            table[i + number] = new ArrayData();
          }
          table[i + number].list = new ArrayList<>(table[i].list);//copy previous position list in the [i + number] position
          table[i + number].list.add(number);//then adds the current element
        }
      }
    }

    return table[targetSum] == null ? null : table[targetSum].list;
  }

  public static void main(String... args) {

    System.out.println("Result = " + arrayToString(howSum(7, new int[]{2, 3})));//[3,2,2]
    System.out.println("Result = " + arrayToString(howSum(7, new int[]{5, 3, 4, 7})));//[4,3]
    System.out.println("Result = " + arrayToString(howSum(7, new int[]{2, 4})));//null
    System.out.println("Result = " + arrayToString(howSum(8, new int[]{2, 3, 5})));//[2, 2, 2, 2]
    System.out.println("Result = " + arrayToString(howSum(300, new int[]{7, 14})));//null

  }
}
