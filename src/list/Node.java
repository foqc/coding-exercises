package list;

public class Node {
    private Integer value;
    private Node next;

    public Node() {
        this.value = null;
        this.next = null;
    }

    public Node(Integer value) {
        this.value = value;
        this.next = null;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
