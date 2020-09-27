package basics;

public class Test {
    public Boolean isUnique(String text) {
        char[] chars = new char[128];
        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);
            if (chars[letter] > 0) return false;
            chars[letter]++;
        }

        return true;
    }

    public int getCharValue(char c) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int v = Character.getNumericValue(c);
        if (v >= a && v <= z) {
            return v - a;
        }
        return -1;
    }

    public Boolean isPermutationOfPalindrome(String text) {
        int[] chars = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
        for (int i = 0; i < text.length(); i++) {
            int letter = getCharValue(text.charAt(i));
            if (letter != -1) chars[letter]++;
        }
        int count = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] % 2 != 0) {
                count++;
                if (count > 1) return false;
            }
        }

        return true;
    }

    public Boolean oneEditAway(String word1, String word2) {
        int count = 0, i = 0, j = 0;
        boolean isSameLength = word1.length() == word2.length();
        while (i < word1.length() && j < word2.length()) {
            char a = word1.charAt(i);
            char b = word2.charAt(j);

            if (a != b) {
                if (!isSameLength) {
                    j++;
                    i--;
                }
                count++;
            }
            if (count > 1) return false;

            i++;
            j++;
        }

        return true;
    }

    public boolean isPalindromeWithOneDiffer(String text) {
        int[] chars = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
        for (int i = 0; i < text.length(); i++) {
            int letter = getCharValue(text.charAt(i));
            if (letter != -1) chars[letter]++;
        }
        int count = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] % 2 != 0) {
                count++;
                if (count > 2) return false;
            }
        }

        return true;
    }

    public String compress(String first) {
        int length = first.length();
        int c = 0;
        String result = "";
        for (int i = 0; i < length; i++) {
            int letter = first.charAt(i);
            c++;
            if (i + 1 >= length || letter != first.charAt(i + 1)) {
                result += (char) letter + "" + c;
                c = 0;
            }
        }

        return result.length() < first.length() ? result : first;
    }

    public int[][] rotateMatrix(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n / 2; i++) {
            int first = i;
            int last = n - 1 - first;
            for (int j = first; j < last; j++) {
                int offset = j - first;

                int top = matrix[first][j];
                int rigth = matrix[j][last];

                int left = matrix[last - offset][first];
                int bottom = matrix[last][last - offset];

                matrix[first][j] = rigth;
                matrix[j][last] = bottom;
                matrix[last][last - offset] = left;
                matrix[last - offset][first] = top;

            }
        }
        return matrix;
    }

    public void showMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] nullifyRow(int[][] matrix, int row) {
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[row][i] = 0;
        }
        return matrix;
    }

    public int[][] nullifyCol(int[][] matrix, int col) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][col] = 0;
        }
        return matrix;
    }

    public int[][] zeroMatrix(int[][] matrix) {
        boolean hasRowZero = false;
        boolean hasColZero = false;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                hasRowZero = true;
                break;
            }
        }
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                hasColZero = true;
                break;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) nullifyRow(matrix, i);
        }

        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) nullifyCol(matrix, i);
        }

        if (hasRowZero) nullifyRow(matrix, 0);
        if (hasColZero) nullifyCol(matrix, 0);

        return matrix;
    }

    public static void main(String... args) {
        Test t = new Test();
        int[][] m = {
                {0, 0, 3},
                {4, 5, 6},
                {7, 8, 9}};
        t.showMatrix(t.zeroMatrix(m));
    }


}
