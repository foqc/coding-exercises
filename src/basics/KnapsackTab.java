package basics;

public class KnapsackTab {

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
   * Time complexity: O(n*m)
   * Space complexity: O(n*m) because 2D array (matrix)
   */
  public static int solve(int w, int n, int[] val, int[] wt) {
    int[][] table = new int[n + 1][w + 1];
    for (int r = 0; r <= w; r++) {
      table[0][r] = 0;
    }

    for (int c = 0; c <= n; c++) {
      table[c][0] = 0;
    }

    for (int item = 0; item <= n; item++) {
      for (int capacity = 0; capacity <= w; capacity++) {
        if (item >= n) {
          continue;
        }
        int maxValWithoutCurr = table[item][capacity];
        int maxValWithCurr = 0;

        int weightOfCurr = wt[item];
        if (weightOfCurr <= capacity) { // We check if the knapsack can fit the current item

          int remainingCapacity = capacity - weightOfCurr; // remainingCapacity must be at least 0
          // Add the maximum value obtainable with the remaining capacity
          maxValWithCurr = val[item] + table[item][remainingCapacity];
        }

        table[item + 1][capacity] = Math.max(maxValWithoutCurr, maxValWithCurr);
      }
    }

    return table[n][w];
  }

  public static void main(String... args) {

    System.out.println("Result: " + solve(10, 4, new int[]{10, 40, 30, 50}, new int[]{5, 4, 6, 3}));
  }
}