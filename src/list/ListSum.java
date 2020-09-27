package list;

public class ListSum {

    static int len(Node n) {
        int c = 0;
        while (n != null) {
            c++;
            n = n.getNext();
        }
        return c;
    }

    static Node insertBefore(Node l, int value) {
        Node node = new Node();
        node.setValue(value);
        if (l != null) node.setNext(l);
        return node;
    }

    static Node padList(Node h, int padding) {
        for (int i = 0; i < padding; i++)
            h = insertBefore(h, 0);
        return h;
    }

    static PartialSum addListHelper(Node l1, Node l2) {
        if (l1 == null && l2 == null) {
            PartialSum sum = new PartialSum();
            return sum;
        }

        PartialSum sum = addListHelper(l1.getNext(), l2.getNext());

        int val = sum.carry + l1.getValue() + l2.getValue();

        Node fullResult = insertBefore(sum.sum, val % 10);

        sum.sum = fullResult;
        sum.carry = val / 10;
        return sum;
    }

    /*add 2 list with different lengths  BACKWARDS 7 1 6 + 5 9 2 = 1 3 0 8*/
    static Node addLists(Node l1, Node l2) {
        int len1 = len(l1);
        int len2 = len(l2);

        if (len1 < len2) l1 = padList(l1, len2 - len1);
        else l2 = padList(l2, len1 - len2);

        PartialSum sum = addListHelper(l1, l2);
        if (sum.carry == 0) return sum.sum;
        else return insertBefore(sum.sum, sum.carry);
    }

    /*add 2 list with the same length FORWARD > 7 1 6 + 5 9 2 = 2 1 9*/
    static Node addLists(Node l, Node l2, int carry) {
        if (l == null && l2 == null && carry == 0)
            return null;

        Node result = new Node();
        if (l != null) carry += l.getValue();
        if (l2 != null) carry += l2.getValue();

        result.setValue(carry % 10);

        if (l != null || l2 != null) {
            Node more = addLists(l == null ? null : l.getNext(), l2 == null ? null : l2.getNext(), carry > 10 ? 1 : 0);
            result.setNext(more);
        }
        return result;
    }

    public static void main(String... args) {

        Node node = new Node(7);
        Node node1 = new Node(1);
        Node node2 = new Node(6);
        Node node3 = new Node(5);
        Node node4 = new Node(9);
        Node node5 = new Node(2);
        Node node6 = new Node(6);

        node.setNext(node1);
        node1.setNext(node2);

        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);

        GList.showList(addLists(node, node3));
        GList.showList(addLists(node, node3, 0));
        GList.showList(padList(new Node(6), 2));


    }
}
