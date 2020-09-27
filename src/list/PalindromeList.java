package list;

public class PalindromeList {

    /**
     * Implementing a function to check if a linked list is palindrome
     * @param list
     * @return
     */
    static boolean isPalindrome(Node list) {
        int values[] = new int[10];
        while (list != null) {
            if (list.getValue() >= 0 && list.getValue() <= 9) values[list.getValue()]++;
            list = list.getNext();
        }

        int count = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] % 2 != 0) {
                count++;
                if (count > 1) return false;
            }
        }

        return true;
    }

    public static void main(String... args) {

        Node node = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(2);
        Node node4 = new Node(1);

        node.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);

        System.out.println(isPalindrome(node));


    }
}
