package basics;

import java.util.*;

public class Hacking {

    public static int getSubset(int[] arr, int total, int i) {
        System.out.println("here//.." + total);
        if (total == 0) return 1;
        else if (total < 0 || i < 0) return 0;
        else if (total < arr[i]) return getSubset(arr, total, i - 1);
        else return getSubset(arr, total, i - 1) + getSubset(arr, total - arr[i], i - 1);
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(candidates, 0, target, 0, temp, result);

        return result;
    }

    private static void helper(int[] candidates, int start, int target, int sum,
                               List<Integer> list, List<List<Integer>> result) {
        if (sum > target) {
            return;
        }

        if (sum == target) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            list.add(candidates[i]);
            helper(candidates, i + 1, target, sum + candidates[i], list, result);
            list.remove(list.size() - 1);
        }
    }

    public static boolean isUnique(String data) {
        int checker = 0;
        for (int i = 0; i < data.length(); i++) {
            int val = data.charAt(i) - 'a';
            if ((checker & (1 << val)) > 0) {
                return false;
            }
            checker |= (1 << val);
        }
        return true;
    }


    public List<String> permute(char input[]) {
        Map<Character, Integer> countMap = new TreeMap<>();
        for (char ch : input) {
            countMap.compute(ch, (key, val) -> {
                if (val == null) {
                    return 1;
                } else {
                    return val + 1;
                }
            });
        }

        char str[] = new char[countMap.size()];
        int count[] = new int[countMap.size()];
        int index = 0;
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            str[index] = entry.getKey();
            count[index] = entry.getValue();
            index++;
        }
        List<String> resultList = new ArrayList<>();
        char result[] = new char[input.length];
        permuteUtil(str, count, result, 0, resultList);
        return resultList;
    }

    public void permuteUtil(char str[], int count[], char result[], int level, List<String> resultList) {
        if (level == result.length) {
            System.out.println("::::::::::::::::::::::::::::::::::::: " + level);
            resultList.add(new String(result));
            return;
        }

        for (int i = 0; i < str.length; i++) {
            if (count[i] == 0) {
                continue;
            }
            result[level] = str[i];
            count[i]--;
            permuteUtil(str, count, result, level + 1, resultList);
            count[i]++;
            System.out.println(i + " c:: " + count[i]);
        }
    }

    public void printBynaries(int n, String prefix, String l) {
        System.out.println("printBynaries(" + n + " , " + prefix + ") : " + l);
        if (n == 0) {
            System.out.println(prefix);
        } else {
            printBynaries(n - 1, prefix + "0", "1st");
            printBynaries(n - 1, prefix + "1", "2nd");
        }
    }

    public void printDices(int n, List<Integer> chosen, int total, int desired) {
        if (n == 0) {
            System.out.print("\n {");
            chosen.forEach(x -> System.out.print(x + ", "));
            System.out.print("}\n");
        } else {
            for (int i = 1; i <= 6; i++) {

                if (total + i + 1 * (n - 1) <= desired &&
                        total + i + 6 * (n - 1) >= desired) {
                    chosen.add(i);
                    printDices(n - 1, chosen, total + i, desired);
                    chosen.remove(chosen.size() - 1);
                }
            }
        }
    }

    void replaceSpaces(char[] str, int trueLength) {
        int spaceCount = 0, index, i = 0;
        for (i = 0; i < trueLength; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }
        index = trueLength + spaceCount * 2;
        if (trueLength < str.length) str[trueLength] = '\0';
        for (i = trueLength - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                str[index - 1] = '0';
                str[index - 2] = '2';
                str[index - 3] = '%';
                index = index - 3;
            } else {
                str[index - 1] = str[i];
                index--;
            }
        }

    }


    public static void main(String[] args) {
//        String data = "eabE";
//        System.out.println(isUnique(data));

//        String data = "Mr John Smith  ";
        Hacking sp = new Hacking();
//        char[] a = data.toCharArray();
//        sp.replaceSpaces(a, 13);
//
//        for (int i = 0; i < a.length; i++) {
//            System.out.print(a[i]);
//        }
        sp.printBynaries(4, "", "");
//        List<Integer> l = new ArrayList<>();
//        sp.printDices(2, l, 0, 6);
//        sp.permute("ABC".toCharArray()).forEach(s -> System.out.println(s));
    }
}
