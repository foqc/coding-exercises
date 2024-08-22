package basics;

import java.util.HashMap;
import java.util.Map;

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


  public static void main(String... args) {
    System.out.println("Result: " + solve(10, 4, new int[]{10, 40, 30, 50}, new int[]{5, 4, 6, 3}));
    System.out.println("Result: " + solve(10, 4, new int[]{10, 40, 30, 50}, new int[]{5, 4, 6, 3},
        new HashMap<>()));
  }

}
