package basics;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

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

  static class Data {

    int rows;
    int cols;

    Data(int rows, int cols) {
      this.rows = rows;
      this.cols = cols;
    }
  }


  /*
   * Using BSF algorithm
   * Time complexity: O(2^n+m)
   * Space complexity: O(n+m)
   */
  public static long gridTraveler2(int m, int n) {
    Queue<Data> queue = new LinkedList<>();
    queue.add(new Data(m, n));
    long total = 0L;
    while (!queue.isEmpty()) {
      Data lastValue = queue.poll();
      if (lastValue.rows == 1 && lastValue.cols == 1) {
        total += 1;
      }

      if (lastValue.rows > 0 && lastValue.cols > 0) {
        queue.add(new Data(lastValue.rows - 1, lastValue.cols));
        queue.add(new Data(lastValue.rows, lastValue.cols - 1));
      }

    }

    return total;
  }


  public static void main(String... args) {

    System.out.println("Result = " + gridTraveler(1, 1));//1
    System.out.println("Result = " + gridTraveler(2, 3));//3
    System.out.println("Result = " + gridTraveler(3, 2));//3
    System.out.println("Result = " + gridTraveler(3, 3));//6
    System.out.println("Result = " + gridTraveler(18, 18, new HashMap<>()));//2333606220
  }
}
