package lab_3.implementation.stacks;


public class ArrayUpStack<E> implements Stack<E> {
    private E[] arr;
    private int top;
    private static final int CAPACITY = 5;


    @SuppressWarnings("unchecked")
    public ArrayUpStack() {
        arr = (E[]) new Object[CAPACITY];
        top = -1;
    }

    @Override
    public void push(E element){
        if(isFull()){
            throw new IllegalStateException("Stack is full.");
        } else {
            arr[++top] = element;
        }
    }

    @Override
    public E pop(){
        if(isEmpty()){
            throw new IllegalStateException("Stack is empty");
        }
        E element = arr[top];
        arr[top--] = null;
        return element;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return arr[top];
    }

    @Override
    public boolean isEmpty() {
        return (top == -1);
    }

    @Override
    public int size() {
        return top+1;
    }

    @Override
    public boolean isFull() {
        return (top == CAPACITY - 1);
    }

}
