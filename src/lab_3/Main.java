package lab_3;

import lab_3.implementation.queues.ArrayDownQueue;
import lab_3.implementation.queues.ArrayUpQueue;
import lab_3.implementation.queues.LinkedListQueue;
import lab_3.implementation.stacks.ArrayDownStack;
import lab_3.implementation.stacks.ArrayUpStack;
import lab_3.implementation.stacks.LinkedListStack;
import lab_3.test.QueueTest;
import lab_3.test.StackTest;

public class Main {
    public static void main(String[] args) {

//
//        System.out.println("Stack Array Up Tests:");
//        StackTest.testStack(new ArrayUpStack<Integer>());
//        System.out.println("Stack Array Down Tests:");
//        StackTest.testStack(new ArrayDownStack<Integer>());
//        System.out.println("Stack Linked List Tests:");
//        StackTest.testStack(new LinkedListStack<Integer>());

        System.out.println("Queue Array Up Tests:");
        QueueTest.testQueue(new ArrayUpQueue<Integer>());
        System.out.println("Queue Array Down Tests:");
        QueueTest.testQueue(new ArrayDownQueue<Integer>());
        System.out.println("Queue Linked List Tests:");
        QueueTest.testQueue(new LinkedListQueue<Integer>());


    }
}
