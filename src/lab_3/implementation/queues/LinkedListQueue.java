package lab_3.implementation.queues;

import lab_3.implementation.common.Node;

public class LinkedListQueue<E> implements Queue<E>{
    private Node<E> front;
    private Node<E> rear;
    private int size;

    public LinkedListQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    @Override
    public void enqueue(E e) {
        Node<E> newNode = new Node<>(e);
        if (isEmpty()) {
            front = newNode;
        } else {
            rear.setNext(newNode);
        }
        rear = newNode;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E element = front.getElement();
        front = front.getNext();
        size--;
        if (isEmpty()) {
            rear = null;
        }
        return element;
    }

    @Override
    public E front() {
        if (isEmpty()) {
            return null;
        }
        return front.getElement();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean isFull() {
        return false;
    }

}
