package basics;

import java.util.Arrays;

public class FindPath {

  /*
   * Mini maze
   * Given m * n matrix find path
   * The letter I is the beginning where you must begin to traverse the matrix.
   * Number 1 is walled and cannot pass through there.
   * The number 0 is a path, it means that you can walk there.
   */

  public static boolean canMove(Character[][] table, boolean[][] visited, int m, int n) {
    return (m >= 0 && m < table.length && n >= 0 && n < table.length && !visited[m][n] &&
        (table[m][n] == '0' || table[m][n] == 'I'));
  }

  /*
   * m=rows length, n=cols length
   * Time complexity: O(2^n*m)
   * Space complexity: O(m*n)
   */
  public static boolean move(Character[][] table, boolean[][] visited, int m, int n) {
    if (m >= 0 && m < table.length && n >= 0 && n < table.length && table[m][n] == 'S') {
      visited[m][n] = true;
      return true;
    }
    if (canMove(table, visited, m, n)) {
      visited[m][n] = true;

      if (move(table, visited, m + 1, n)) {
        return true;
      }
      if (move(table, visited, m, n + 1)) {
        return true;
      }
      if (move(table, visited, m - 1, n)) {
        return true;
      }
      return move(table, visited, m, n - 1);
    }

    return false;
  }


  public static void main(String... args) {
    Character[][] matrix = {
        {'I', '0', '0', '1', 'S'},
        {'1', '0', '1', '1', '0'},
        {'1', '0', '0', '1', '0'},
        {'0', '0', '1', '0', '0'},
        {'1', '0', '0', '0', '0'}};

    boolean[][] visited = new boolean[matrix.length][matrix[0].length];//by default the matrix has false
    System.out.println(
        "Can Move? " + move(matrix, visited, 0, 0) + " R: " + Arrays.deepToString(visited));
  }

}
