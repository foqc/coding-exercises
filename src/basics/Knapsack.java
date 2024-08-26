package basics;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Knapsack {

  /*
   * Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum
   * total value in the knapsack. In other words, given two integer arrays val[0..n-1] and wt[0..n-1]
   * which represent values and weights associated with n items respectively. Also given an integer W which
   * represents knapsack capacity, find out the maximum value subset of val[] such that sum of the weights of
   * this subset is smaller than or equal to W. You cannot break an item, either pick the complete
   * item or don’t pick it (0–1 property).
   */

  /*
   * m=weight, n= val array length
   * Time complexity: O(2^n+m)
   * Space complexity: O(n*m)
   */
  public static int solve(int w, int n, int[] val, int[] wt) {
    if (n == 0 || w == 0) {
      return 0;
    }

    if (wt[n - 1] > w) {
      return solve(w, n - 1, val, wt);
    }

    int maxValWithoutCurr = solve(w, n - 1, val, wt);
    int maxValWithCurr = val[n - 1] + solve(w - wt[n - 1], n - 1, val, wt);
    return Math.max(maxValWithoutCurr, maxValWithCurr);
  }

  /*
   * m=weight, n= val array length
   * Time complexity: O(n*m)
   * Space complexity: O(n*m)
   */
  public static int solve(int w, int n, int[] val, int[] wt, Map<String, Integer> memo) {
    String key = w + "-" + n;
    if (memo.containsKey(key)) {
      return memo.get(key);
    }
    if (n == 0 || w == 0) {
      return 0;
    }

    if (wt[n - 1] > w) {
      return solve(w, n - 1, val, wt, memo);
    }

    int maxValWithoutCurr = solve(w, n - 1, val, wt, memo);
    int maxValWithCurr = val[n - 1] + solve(w - wt[n - 1], n - 1, val, wt, memo);
    memo.put(key, Math.max(maxValWithoutCurr, maxValWithCurr));
    return memo.get(key);
  }

  static class Data {

    int total;
    int capacity;
    String path;

    Data(int total, int capacity, String path) {
      this.total = total;
      this.capacity = capacity;
      this.path = path;
    }
  }

  /*
   * Using BSF algorithm
   * m=weight, n= val array length
   * Time complexity: O(n*m)
   * Space complexity: O(n*m*m) -> O(n*m^2) additional m due to queue Data stores path
   */
  public static int solve2(int w, int n, int[] val, int[] wt) {
    Queue<Data> queue = new LinkedList<>();
    queue.add(new Data(0, w, ""));
    int max = 0;

    while (!queue.isEmpty()) {
      Data lastData = queue.poll();
      max = Math.max(lastData.total, max);

      for (int item = 0; item < n; item++) {
        // we can use this key "[" + item + "_" + val[item] + "]" instead {33, 20, 33}
        // if we want to take duplicated values. Another versions of solve(...) already takes duplicated values
        String key = "[" + val[item] + "]";
        if (lastData.path.contains(key)) {
          continue;
        }

        int weightOfCurr = wt[item];
        int remainingCapacity = lastData.capacity - weightOfCurr;

        int maxValWithCurr = 0;
        if (weightOfCurr <= lastData.capacity) {
          maxValWithCurr = val[item] + lastData.total;
        }

        queue.add(new Data(maxValWithCurr, remainingCapacity, lastData.path.concat(key)));
      }
    }

    return max;
  }


  public static void main(String... args) {
    System.out.println("Result: " + solve(10, 4, new int[]{10, 40, 30, 50}, new int[]{5, 4, 6, 3}));
    System.out.println("Result: " + solve(10, 4, new int[]{10, 40, 30, 50}, new int[]{5, 4, 6, 3},
        new HashMap<>()));
  }

}
