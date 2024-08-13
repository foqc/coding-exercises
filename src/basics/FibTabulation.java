package basics;

public class FibTabulation {

  /*
   * Time complexity: O(n)
   * Space complexity: O(n)
   */
  public static long fibonacci(int n) {
    long[] table = new long[n + 1];

    table[1] = 1;
    for (int i = 0; i <= n; i++) {
      if (i + 1 <= n) {
        table[i + 1] += table[i];
      }
      if (i + 2 <= n) {
        table[i + 2] += table[i];
      }
    }

    return table[n];
  }

  public static void main(String... args) {
    System.out.println("Result = " + fibonacci(6));//8
    System.out.println("Result = " + fibonacci(7));//13
    System.out.println("Result = " + fibonacci(8));//21
    System.out.println("Result = " + fibonacci(50));//12586269025
  }
}
