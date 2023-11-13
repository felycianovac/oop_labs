package stacks;

import java.util.Vector;

import static common.Constants.CAPACITY;

public class VectorStack<E> implements Stack<E> {

    private Vector<E> vector;


    public VectorStack(){
        vector = new Vector<>();
    }

    @Override
    public void push(E element){
        if(isFull()){
            throw new IllegalStateException("Stack is full.");
        } else {
            vector.add(element);
        }
    }

    @Override
    public E pop(){
        if(isEmpty()){
            throw new IllegalStateException("Stack is empty.");
        } else {
            return vector.remove(vector.size() - 1);
        }
    }

    @Override
    public E peek(){
        if(isEmpty()){
            throw new IllegalStateException("Stack is empty.");
        } else {
            return vector.get(vector.size() - 1);
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
