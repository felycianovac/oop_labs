package lab_3.implementation.stacks;


import static lab_3.implementation.common.Constants.CAPACITY;

@SuppressWarnings("ALL")
public class ArrayStack<E> implements Stack<E> {
    private E[] arr;
    private int top;


    public ArrayStack() {
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
            throw new IllegalStateException("Stack is empty.");
        } else {
            return arr[top--];
        }
    }

    @Override
    public E peek(){
        if(isEmpty()){
            throw new IllegalStateException("Stack is empty.");
        } else {
            return arr[top];
        }
    }

    @Override
    public boolean isEmpty(){
        return top == -1;
    }

    @Override
    public boolean isFull(){
        return top == CAPACITY - 1;
    }

    @Override
    public int size(){
        return top + 1;
    }


}
