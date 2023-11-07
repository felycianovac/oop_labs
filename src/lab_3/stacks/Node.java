package lab_3.stacks;

public class Node<E> {
    E element;
    Node next;

    public Node(E element) {
        this.element = element;
        this.next = null;
    }
}
