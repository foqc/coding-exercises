package basics;

public class GridTravelerTab {
  /*
   * Say that you are a traveler in 2D grid. You begin in the top left corner and your goal is to
   * travel to the bottom right corner. You may only move down or right.
   * In how many ways can you travel to the goal in a grid with dimensions m *n?
   */

  /*
   * Time complexity: O(n*m)
   * Space complexity: O(n+m)
   */
  public static long gridTraveler(int m, int n) {
    long[][] table = new long[m + 1][n + 1];

    table[1][1] = 1;//this is a base case when table is 1 by 1 will have only 1 way to travel it is equivalent to recursive base case if (m == 1 && n == 1) return 1;
    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        if (i + 1 <= m) {
          table[i + 1][j] += table[i][j];
        }
        if (j + 1 <= n) {
          table[i][j + 1] += table[i][j];
        }
      }
    }

    return table[m][n];
  }

  public static void main(String... args) {

    System.out.println("Result = " + gridTraveler(1, 1));//1
    System.out.println("Result = " + gridTraveler(2, 3));//3
    System.out.println("Result = " + gridTraveler(3, 2));//3
    System.out.println("Result = " + gridTraveler(3, 3));//6
    System.out.println("Result = " + gridTraveler(18, 18));//2333606220
  }
}
