package lab_3.implementation.queues;

public class ArrayDownQueue<E> implements Queue<E> {
    private E[] queue;
    private int front;
    private int rear;
    private int size;
    private final int CAPACITY = 5;

    @SuppressWarnings("unchecked")
    public ArrayDownQueue(){
        queue = (E[]) new Object[CAPACITY];
        front = CAPACITY-1;
        rear = CAPACITY;
        size = 0;
    }

    @Override
    public void enqueue(E element){
        if(isFull()){
            throw new IllegalStateException("Queue is full.");
        } else {
            rear = (rear - 1 + CAPACITY) % CAPACITY;
            queue[rear] = element;
            size++;
        }
    }

    @Override
    public E dequeue(){
        if(isEmpty()){
            throw new IllegalStateException("Queue is empty");
        }
        E element = queue[front];
        queue[front] = null;
        front = (front - 1 + CAPACITY) % CAPACITY;
        size--;
        return element;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue[CAPACITY-size()-1];
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
