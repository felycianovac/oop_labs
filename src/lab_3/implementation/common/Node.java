package lab_3.implementation.common;

public class Node<E> {
    private E element;
    private Node next;

    public Node(E element) {
        this.element = element;
        this.next = null;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }
    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
