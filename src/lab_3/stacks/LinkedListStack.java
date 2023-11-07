package lab_3.stacks;

import lab_3.common.Node;

public class LinkedListStack<E> implements Stack<E> {
    private Node<E> top;
    private int size;
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
        newNode.setNext(top);
        top = newNode;
        size++;
    }

    @Override
    public E pop(){
        if(isEmpty()){
            throw new IllegalStateException("Stack is empty");
        }
        E element = top.getElement();
        top = top.getNext();
        size--;
        return element;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.getElement();
    }

    @Override
    public boolean isEmpty() {
        return (top == null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isFull() {
        return (CAPACITY== size());
    }

}
