import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import queues.ArrayQueue;
import queues.LinkedListQueue;
import queues.Queue;
import queues.VectorQueue;
import stacks.Stack;
import java.util.stream.Stream;
import static org.junit.Assert.*;

@SuppressWarnings("ALL")
public class QueueTest {

    public static Stream<Queue<Integer>> queueProvider() {
        return Stream.of(
                new VectorQueue<Integer>(),
                new ArrayQueue<Integer>(),
                new LinkedListQueue<Integer>()
        );
    }

    @ParameterizedTest
    @MethodSource("queueProvider")
    void testEnqueue(Queue<Integer> queue) {
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.front().intValue());
        queue.enqueue(2);
        assertEquals(1, queue.front().intValue());
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        assertEquals(1, queue.front().intValue());
        assertTrue(queue.isFull());
    }

    @ParameterizedTest
    @MethodSource("queueProvider")
    void testDequeue(Queue<Integer> queue) {
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(2, queue.dequeue().intValue());
        assertEquals(3, queue.dequeue().intValue());
        assertTrue(queue.isEmpty());

        assertThrows(IllegalStateException.class, queue::dequeue);
    }

    @ParameterizedTest
    @MethodSource("queueProvider")
    void testPeek(Queue<Integer> queue) {
        queue.enqueue(4);
        queue.enqueue(5);
        assertEquals(4, queue.front().intValue());
        assertEquals(4, queue.front().intValue());
        queue.dequeue();
        assertEquals(5, queue.front().intValue());
    }

    @ParameterizedTest
    @MethodSource("queueProvider")
    void testIsEmpty(Queue<Integer> queue) {
        assertTrue(queue.isEmpty());
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("queueProvider")
    void testIsFull(Queue<Integer> queue) {
        assertFalse(queue.isFull());
        queue.enqueue(1);
        assertFalse(queue.isFull());
        queue.enqueue(2);
        assertFalse(queue.isFull());
        queue.enqueue(3);
        assertFalse(queue.isFull());
        queue.enqueue(4);
        assertFalse(queue.isFull());
        queue.enqueue(5);
        assertTrue(queue.isFull());
    }


}