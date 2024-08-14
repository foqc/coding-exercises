package basics;

public class CanSumTab {
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
   * Time complexity: O(m*n)
   * Space complexity: O(m)
   */
  public static boolean canSum(int targetSum, int[] numbers) {
    // numbers array will not change (no need to shrink or add elements) so only targetSum, will contribute to the tabulation process, so we create a 1D array.
    boolean[] table = new boolean[targetSum + 1];
    for (int i = 0; i <= targetSum; i++) {
      table[i] = false;
    }

    table[0] = true;//if(targetSum == 0) return true equivalent in recursive implementation
    for (int i = 0; i <= targetSum; i++) {
      if (!table[i]) {// we only tabulate when is true
        continue;
      }
      for (int number : numbers) {
        if (i + number <= targetSum) {
          table[i + number] = true;
        }
      }
    }

    return table[targetSum];
  }

  public static void main(String... args) {

    System.out.println("Result = " + canSum(7, new int[]{2, 3}));//true
    System.out.println("Result = " + canSum(7, new int[]{5, 3, 4, 7}));//true
    System.out.println("Result = " + canSum(7, new int[]{2, 4}));//false
    System.out.println("Result = " + canSum(8, new int[]{2, 3, 5}));//true
    System.out.println("Result = " + canSum(300, new int[]{7, 14}));//false
  }
}
