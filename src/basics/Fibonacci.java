package basics;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

  /*
   * Time complexity: O(2^n)
   * Space complexity: O(n)
   */
  public static long fibonacci(long n) {
    if (n <= 2) {
      return 1;
    }

    return fibonacci(n - 1) + fibonacci(n - 2);
  }

  /* With memoization
   * Time complexity: O(2n) => O(n)
   * Space complexity: O(n)
   */
  public static long fibonacci(long n, Map<Long, Long> memo) {
    if (memo.containsKey(n)) {
      return memo.get(n);
    }

    if (n <= 2) {
      return 1;
    }

    memo.put(n, fibonacci(n - 1, memo) + fibonacci(n - 2, memo));

    return memo.get(n);
  }


  public static void main(String... args) {

    System.out.println("Result = " + fibonacci(6));//8
    System.out.println("Result = " + fibonacci(7));//13
    System.out.println("Result = " + fibonacci(8));//21
    System.out.println("Result = " + fibonacci(50, new HashMap<>()));//12586269025
  }
}
