import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import stacks.ArrayStack;
import stacks.LinkedListStack;
import stacks.Stack;
import stacks.VectorStack;

import java.util.stream.Stream;

import static org.junit.Assert.*;

@SuppressWarnings("ALL")
public class StackTest {

    public static Stream<Stack<Integer>> stackProvider(){
        return Stream.of(
                new VectorStack<Integer>(),
                new ArrayStack<Integer>(),
                new LinkedListStack<Integer>()
        );
    }

    @ParameterizedTest
    @MethodSource("stackProvider")
    void testPush(Stack<Integer> stack) {
        assertTrue(stack.isEmpty());
        stack.push(1);
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.peek().intValue());
        stack.push(2);
        assertEquals(2, stack.peek().intValue());
        stack.push(3);
        stack.push(4);
        stack.push(5);
        assertEquals(5, stack.peek().intValue());
        assertTrue(stack.isFull());
    }

    @ParameterizedTest
    @MethodSource("stackProvider")
    void testPop(Stack<Integer> stack) {
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop().intValue());
        assertEquals(2, stack.pop().intValue());
        assertTrue(stack.isEmpty());

        assertThrows(IllegalStateException.class, stack::pop);
    }

    @ParameterizedTest
    @MethodSource("stackProvider")
    void testPeek(Stack<Integer> stack) {
        stack.push(4);
        stack.push(5);
        assertEquals(5, stack.peek().intValue());
        assertEquals(5, stack.peek().intValue());
        stack.pop();
        assertEquals(4, stack.peek().intValue());

        stack.pop();
        assertThrows(IllegalStateException.class, stack::peek);
    }


    @ParameterizedTest
    @MethodSource("stackProvider")
    void testIsEmpty(Stack<Integer> stack) {
        assertTrue(stack.isEmpty());
        stack.push(6);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }


    @ParameterizedTest
    @MethodSource("stackProvider")
    void testIsFull(Stack<Integer> stack) {
        if (stack instanceof ArrayStack || stack instanceof VectorStack) {
            for (int i = 0; i < 5; i++) {
                stack.push(i);
                if (i < 4) {
                    assertFalse(stack.isFull());
                } else {
                    assertTrue(stack.isFull());
                }
            }

            assertThrows(IllegalStateException.class, () -> stack.push(100));
        }
    }

}

