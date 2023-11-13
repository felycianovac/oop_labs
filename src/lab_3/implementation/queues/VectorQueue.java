package lab_3.implementation.queues;

import java.util.Vector;

import static lab_3.implementation.common.Constants.CAPACITY;

public class VectorQueue<E> implements Queue<E>{
    private Vector<E> vector;


    public VectorQueue(){
        vector = new Vector<>();
    }

    @Override
    public void enqueue(E element){
        if(isFull()){
            throw new IllegalStateException("Queue is full.");
        } else {
            vector.add(element);
        }
    }

    @Override
    public E dequeue(){
        if(isEmpty()){
            throw new IllegalStateException("Queue is empty.");
        } else {
            return vector.remove(0);
        }
    }

    @Override
    public E front(){
        if(isEmpty()){
            throw new IllegalStateException("Queue is empty.");
        } else {
            return vector.get(0);
        }
    }

    @Override
    public boolean isEmpty(){
        return vector.isEmpty();
    }

    @Override
    public int size(){
        return vector.size();
    }

    @Override
    public boolean isFull(){
        return vector.size()== CAPACITY;
    }
}
