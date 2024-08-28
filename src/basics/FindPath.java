package basics;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

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

  public static boolean isGoal(Character[][] table, int m, int n) {
    return (m >= 0 && m < table.length && n >= 0 && n < table.length && table[m][n] == 'S');
  }

  /*
   * m=rows length, n=cols length
   * Time complexity: O(2^n*m)
   * Space complexity: O(m*n)
   */
  public static boolean findPath(Character[][] table, boolean[][] visited, int m, int n) {
    if (isGoal(table, m, n)) {
      visited[m][n] = true;
      return true;
    }
    if (canMove(table, visited, m, n)) {
      visited[m][n] = true;

      if (findPath(table, visited, m + 1, n)) {
        return true;
      }
      if (findPath(table, visited, m, n + 1)) {
        return true;
      }
      if (findPath(table, visited, m - 1, n)) {
        return true;
      }
      return findPath(table, visited, m, n - 1);
    }

    return false;
  }

  static class Data {

    int rows;
    int cols;
    String path;

    Data(int rows, int cols, String path) {
      this.rows = rows;
      this.cols = cols;
      this.path = path;
    }
  }

  /*
   * m=rows length, n=cols length
   * Time complexity: O(2^n*m)
   * Space complexity: O(m*n)
   */
  public static boolean findPath2(Character[][] table, boolean[][] visited, int m, int n) {
    Queue<Data> queue = new LinkedList<>();
    queue.add(new Data(m, n, ""));

    while (!queue.isEmpty()) {
      Data lastData = queue.poll();
      int lastRows = lastData.rows;
      int lastCols = lastData.cols;
      if (isGoal(table, lastRows, lastCols)) {
        return true;
      }

      if (canMove(table, visited, lastRows, lastCols)) {
        String path = lastData.path.concat("(" + lastRows + ", " + lastCols + ")");
        visited[lastRows][lastCols] = true;
        queue.add(new Data(lastRows + 1, lastCols, path));
        queue.add(new Data(lastRows - 1, lastCols, path));
        queue.add(new Data(lastRows, lastCols + 1, path));
        queue.add(new Data(lastRows, lastCols - 1, path));
      }
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
        "Has path? " + findPath2(matrix, visited, 0, 0) + " R: " + Arrays.deepToString(visited));
  }

}
