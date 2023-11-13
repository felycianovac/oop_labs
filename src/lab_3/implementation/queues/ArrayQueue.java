package lab_3.implementation.queues;

import static lab_3.implementation.common.Constants.CAPACITY;

public class ArrayQueue<E> implements Queue<E>{
    private E[] arr;
    private int front;
    private int rear;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayQueue(){
        arr = (E[]) new Object[CAPACITY];
        front = this.size = 0;
        rear = - 1;
    }

    @Override
    public void enqueue(E element){
        if(isFull()){
            throw new IllegalStateException("Queue is full.");
        } else {
            rear = (rear + 1) % arr.length;
            arr[rear] = element;
            size++;
        }
    }

    @Override
    public E dequeue(){
        if(isEmpty()){
            throw new IllegalStateException("Queue is empty");
        }
        E element = arr[front];
        front = (front + 1) % arr.length;
        size--;
        return element;
    }

    @Override
    public E front() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return arr[front];
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isFull() {
        return (size == CAPACITY);
    }
}
