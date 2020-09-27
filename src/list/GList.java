package list;

public class GList {
    public static void showList(Node node) {
        while (node != null) {
            System.out.print(node.getValue() + " - ");
            node = node.getNext();
        }
        System.out.println();
    }

    static Node kthToLast(Node head, int k, Index idx) {
        if (head == null) return null;
        Node node = kthToLast(head.getNext(), k, idx);
        idx.value = idx.value + 1;

        if (k == idx.value) return head;
        return node;
    }

    public static void main(String... args) {
        Node node = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        node.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);

        Index in = new Index();
        System.out.println(kthToLast(node, 1, in).getValue());

    }
}
