package lab_3.test;

import lab_3.implementation.stacks.Stack;

public class StackTest {

    public static void testStack(Stack<Integer> stack){

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        System.out.println("Stack full: " + stack.isFull());
        System.out.println("Stack size: " + stack.size());
        stack.pop();
        System.out.println("Stack peek: " + stack.peek());
        System.out.println("Stack size: " + stack.size());

        stack.push(5);
        System.out.println("Stack top after push 5:" + stack.peek());
        System.out.println("Stack full: " + stack.isFull());
        System.out.println("Stack empty:" + stack.isEmpty());


    }
}

