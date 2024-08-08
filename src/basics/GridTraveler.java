package basics;

import java.util.HashMap;
import java.util.Map;

public class GridTraveler {

  /*
   * Say that you are a traveler in 2D grid. You begin in the top left corner and your goal is to
   * travel to the bottom right corner. You may only move down or right.
   * In how many ways can you travel to the goal in a grid with dimensions m *n?
   */

  /*
   * Time complexity: O(2^n+m)
   * Space complexity: O(n+m)
   */
  public static long gridTraveler(long m, long n) {

    if (m == 0 || n == 0) {
      return 0;
    }
    if (m == 1 && n == 1) {
      return 1;
    }

    return gridTraveler(m - 1, n) + gridTraveler(m, n - 1);
  }


  /* With memoization
   * Time complexity: O(n*m)
   * Space complexity: O(n+m)
   */
  public static long gridTraveler(long m, long n, Map<String, Long> memo) {
    String key = n + "-" + m;
    if (memo.containsKey(key)) {
      return memo.get(key);
    }
    if (m == 0 || n == 0) {
      return 0;
    }
    if (m == 1 && n == 1) {
      return 1;
    }

    memo.put(key, gridTraveler(m - 1, n, memo) + gridTraveler(m, n - 1, memo));

    return memo.get(key);
  }


  public static void main(String... args) {

    System.out.println("Result = " + gridTraveler(1, 1));//1
    System.out.println("Result = " + gridTraveler(2, 3));//3
    System.out.println("Result = " + gridTraveler(3, 2));//3
    System.out.println("Result = " + gridTraveler(3, 3));//6
    System.out.println("Result = " + gridTraveler(18, 18, new HashMap<>()));//2333606220
  }
}
