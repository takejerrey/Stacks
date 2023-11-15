import java.util.EmptyStackException;
import java.util.Stack;

public class LinkedStack<T> implements StackInterface <T> {

    private Node topNode;
    private Node firstNode;

    public LinkedStack() {
        topNode = null;
    }

    private class Node {
        private T data;
        private Node next;

        private Node(T dataPortion) {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }

        private T getData() {
            return data;
        }

        private void setData(T newData) {
            data = newData;
        }

        private Node getNextNode() {
            return next;
        }

        private void setNextNode(Node nextNode) {
            next = nextNode;
        }
    }

    @Override
    public void push(T newEntry) {
        Node newNode = new Node(newEntry, topNode);
        topNode = newNode;
    }

    @Override
    public T pop() {
        T top = peek();
        topNode = topNode.getNextNode();
        return top;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            return topNode.getData();
        }
    }

    @Override
    public boolean isEmpty() {
        return topNode == null;
    }

    @Override
    public void clear() {
        topNode = null;
    }

    public int Precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }

    @Override
    public String convertToPostfix(String infix) {
        String ans = new String("");
        LinkedStack<Character> stack = new LinkedStack<Character>();
        for (int i = 0; i < infix.length(); ++i) {
            char c = infix.charAt(i);


            if (Character.isLetterOrDigit(c))
                ans += c;

                // If the scanned character is an '(',
                // push it to the stack.
            else if (c == '(')
                stack.push(c);


            else if (c == ')') {
                while (!stack.isEmpty() &&
                        stack.peek() != '(')
                    ans += stack.pop();

                stack.pop();
            } else {
                while (!stack.isEmpty() && Precedence(c)
                        <= Precedence(stack.peek())) {

                    ans += stack.pop();
                }
                stack.push(c);
            }

        }


        while (!stack.isEmpty()) {
            if (stack.peek() == '(')
                return "Invalid Expression";
            ans += stack.pop();
        }
        return ans;
    }

    @Override
    public String evaluatePostfix(String postfix) {
        return "";
    }
}
