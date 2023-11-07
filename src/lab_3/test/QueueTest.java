package lab_3.test;

import lab_3.implementation.queues.Queue;

public class QueueTest {

    public static void testQueue(Queue<Integer> queue){

            queue.enqueue(1);
            queue.enqueue(2);
            queue.enqueue(3);
            queue.enqueue(4);

            System.out.println("Queue full: " + queue.isFull());
            System.out.println("Queue size: " + queue.size());
            queue.dequeue();
            System.out.println("Queue peek: " + queue.peek());
            System.out.println("Queue size: " + queue.size());

            queue.enqueue(5);
            System.out.println("Queue top after push 5:" + queue.peek());
            System.out.println("Queue full: " + queue.isFull());
            System.out.println("Queue empty:" + queue.isEmpty());

    }
}
