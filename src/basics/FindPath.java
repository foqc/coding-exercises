package basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class FindPath {

  /*
   * Mini maze
   * Given m * n matrix find path
   * The letter I is the beginning where you must begin to traverse the matrix.
   * Number 1 is walled and cannot pass through there.
   * The number 0 is a path, it means that you can walk there.
   */

  public static boolean canMove(Character[][] table, boolean[][] visited, int m, int n) {
    return (m >= 0 && m < table.length && n >= 0 && n < table[0].length && !visited[m][n] &&
        (table[m][n] == '0' || table[m][n] == 'I'));
  }

  public static boolean isGoal(Character[][] table, int m, int n) {
    return (m >= 0 && m < table.length && n >= 0 && n < table[0].length && table[m][n] == 'S');
  }

  /*
   * m=rows length, n=cols length
   * Time complexity: O(2^n*m)
   * Space complexity: O(m*n)
   */
  public static boolean canFindPath(Character[][] table, boolean[][] visited, int m, int n) {
    if (isGoal(table, m, n)) {
      visited[m][n] = true;
      return true;
    }
    if (canMove(table, visited, m, n)) {
      visited[m][n] = true;

      if (canFindPath(table, visited, m + 1, n)) {
        return true;
      }
      if (canFindPath(table, visited, m, n + 1)) {
        return true;
      }
      if (canFindPath(table, visited, m - 1, n)) {
        return true;
      }
      return canFindPath(table, visited, m, n - 1);
    }

    return false;
  }

  /*
   * m=rows length, n=cols length
   * Time complexity:  O(n^m*m) additional m because of copy array operation will take m in the worst case
   * Space complexity: O(m*m) -> O(m^2) because it stores path in each recursion
   */
  public static List<String> findPath(Character[][] table, boolean[][] visited, int m, int n) {
    String element = "(" + m + ", " + n + ")";
    if (isGoal(table, m, n)) {
      List<String> path = new ArrayList<>();
      path.add(0, element);
      visited[m][n] = true;
      return path;
    }
    if (canMove(table, visited, m, n)) {
      visited[m][n] = true;

      List<String> up = findPath(table, visited, m + 1, n);
      if (up != null) {
        up.add(0, element);
        return up;
      }
      List<String> right = findPath(table, visited, m, n + 1);
      if (right != null) {
        right.add(0, element);
        return right;
      }
      List<String> down = findPath(table, visited, m - 1, n);
      if (down != null) {
        down.add(0, element);
        return down;
      }
      List<String> left = findPath(table, visited, m, n - 1);
      if (left != null) {
        left.add(0, element);
        return left;
      }
    }

    return null;
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


  public static List<List<String>> addElementToPaths(List<List<String>> currentPaths,
      String element) {
    return currentPaths.stream().map(item -> {
      List<String> newList = new ArrayList<>(item);
      newList.add(0, element);
      return newList;
    }).collect(Collectors.toList());
  }

  /*
   * TODO: currently only check one path because of visited array, fix to find out all the paths.
   * m=rows length, n=cols length
   * Time complexity: O((n^m) * m) additional m because of copy array operation will take m in the worst case
   * Space complexity: O(m*m) -> O(m^2)
   */
  public static List<List<String>> findAllPaths(Character[][] table, boolean[][] visited, int m,
      int n) {
    String element = "(" + m + ", " + n + ")";
    if (isGoal(table, m, n)) {
      List<String> path = new ArrayList<>();
      path.add(0, element);
      visited[m][n] = true;

      return new ArrayList<>() {{
        add(path);
      }};
    }

    List<List<String>> allPaths = new ArrayList<>();
    if (canMove(table, visited, m, n)) {
      visited[m][n] = true;

      List<List<String>> up = addElementToPaths(findAllPaths(table, visited, m + 1, n), element);
      allPaths.addAll(up);

      List<List<String>> right = addElementToPaths(findAllPaths(table, visited, m, n + 1), element);
      allPaths.addAll(right);

      List<List<String>> down = addElementToPaths(findAllPaths(table, visited, m - 1, n), element);
      allPaths.addAll(down);

      List<List<String>> left = addElementToPaths(findAllPaths(table, visited, m, n - 1), element);
      allPaths.addAll(left);
    }

    return allPaths;
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
