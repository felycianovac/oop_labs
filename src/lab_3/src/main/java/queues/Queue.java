package queues;

public interface Queue<E> {
    void enqueue(E element);
    E dequeue();
    E front();
    boolean isEmpty();
    int size();
    boolean isFull();
}
