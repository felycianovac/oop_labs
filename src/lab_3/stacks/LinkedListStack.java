package lab_3.stacks;
public class LinkedListStack<E> implements Stack<E> {
    private Node<E> top;
    private final int CAPACITY = 5;

    public LinkedListStack() {
        top = null;
    }

    @Override
    public void push(E element){
        if(isFull()){
            throw new IllegalStateException("Stack is full.");
        }
        Node<E> newNode = new Node<>(element);
        newNode.next = top;
        top = newNode;
    }

    @Override
    public E pop(){
        if(isEmpty()){
            throw new IllegalStateException("Stack is empty");
        }
        E element = top.element;
        top = top.next;
        return element;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.element;
    }

    @Override
    public boolean isEmpty() {
        return (top == null);
    }

    @Override
    public int size() {
        int count = 0;
        Node<E> current = top;
        while(current != null){
            count++;
            current = current.next;
        }
        return count;
    }

    @Override
    public boolean isFull() {
        return (CAPACITY== size());
    }

}
