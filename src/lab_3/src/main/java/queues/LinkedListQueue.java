package queues;

import common.Node;

import java.util.NoSuchElementException;

import static common.Constants.CAPACITY;

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
    public void enqueue(E element) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full.");
        }
        Node<E> newNode = new Node<>(element);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        E element = front.getElement();
        front = front.getNext();
        if (isEmpty()) {
            rear = null;
        }
        size--;
        return element;
    }

    @Override
    public E front() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
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
        return size == CAPACITY;
    }

}
