package lab_3.stacks;

public class ArrayDownStack<E> implements Stack<E> {
    private E[] arr;
    private int bottom;
    private final int CAPACITY = 5;

    @SuppressWarnings("unchecked")
    public ArrayDownStack() {
        arr = (E[]) new Object[CAPACITY];
        bottom = arr.length;
    }

    @Override
    public void push(E element){
        if(isFull()){
            throw new IllegalStateException("Stack is full.");
        } else {
            arr[--bottom] = element;
        }
    }

    @Override
    public E pop(){
        if(isEmpty()){
            throw new IllegalStateException("Stack is empty");
        }
        E element = arr[bottom];
        arr[bottom++] = null;
        return element;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return arr[bottom];
    }

    @Override
    public boolean isEmpty() {
        return (bottom == arr.length);
    }

    @Override
    public int size() {
        return arr.length;
    }

    @Override
    public boolean isFull() {
        return (bottom == 0);
    }
}
