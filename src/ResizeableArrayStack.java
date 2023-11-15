import java.util.Arrays;
import java.util.EmptyStackException;

public class ResizeableArrayStack<T> implements StackInterface<T>{
    private T[] stack;
    private int topIndex;
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    public ResizeableArrayStack(){
        this(DEFAULT_CAPACITY);
    }
    public ResizeableArrayStack(int capacity){
        integrityOK = true;
        checkCapacity(capacity);
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[])new Object[capacity];
        stack = tempStack;
        topIndex = -1;
        integrityOK = true;

    }
    public void checkCapacity(int size){
        if(size > MAX_CAPACITY){
            throw new IllegalStateException("Attempt to create a stack whose capacity exceeds allowed maximum of " +
                    MAX_CAPACITY);
        }
    }
    public boolean checkIntegrity(){
        return integrityOK;
    }
    @Override
    public void push(T newEntry) {
        checkIntegrity();
        ensureCapacity();
        topIndex++;
        stack[topIndex] = newEntry;
        //topIndex++;
    }
    public void ensureCapacity(){
        if(topIndex >= stack.length -1){
            int newLength = 2* stack.length;
            checkCapacity(newLength);
            stack = Arrays.copyOf(stack, newLength);
        }
    }

    @Override
    public T pop() {
        checkIntegrity();
        if(isEmpty()){
            throw new EmptyStackException();
        }
        else{
            T top = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
            return top;
        }
    }

    @Override
    public T peek() {
        checkIntegrity();
        if(isEmpty()){
            throw new EmptyStackException();
        }
        else{
            return stack[topIndex];
        }
    }

    @Override
    public boolean isEmpty() {
        boolean status = false;
        if(topIndex < 0){
            status = true;
        }
        return status;
    }

    @Override
    public void clear() {
        checkIntegrity();
        while(topIndex > -1){
            stack[topIndex] = null;
            topIndex--;
        }
    }
    public String convertToPostfix(String infix){
        return "";
    }
    @Override
    public String evaluatePostfix(String postfix) {
        ResizeableArrayStack<String> valueStack = new ResizeableArrayStack<>();
        for (int i = 0; i < postfix.length(); i++) {
            char nextCharacter = postfix.charAt(i);
            if (Character.isDigit(nextCharacter)) {
                String num = String.valueOf(Character.getNumericValue(nextCharacter));
                valueStack.push(num);
            } else if (Character.isLetter(nextCharacter)) {
                // assuming variables are single-letter characters
                String value = String.valueOf(getValue(nextCharacter));
                valueStack.push(value);
            } else if (isOperator(nextCharacter)) {
                String operandTwo = valueStack.pop();
                String operandOne = valueStack.pop();
                String result = String.valueOf(performOperation(nextCharacter, operandOne, operandTwo));
                valueStack.push(result);
            } // Ignore unexpected characters
        }
        return valueStack.peek();
    }

    private static int getValue(char variable) {
        switch(variable){
            case 'a': return 2;
            case 'b': return 3;
            case 'c': return 4;
            case 'd': return 5;
            case 'e': return 6;
            default: return 0;
        }
    }

    private static boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/' || c == '^');
    }

    private static int performOperation(char operator, String operandOne, String operandTwo) {
        int opOne = Integer.parseInt(operandOne);
        int opTwo = Integer.parseInt(operandTwo);
        switch (operator) {
            case '+':
                return opOne + opTwo;
            case '-':
                return opOne - opTwo;
            case '*':
                return opOne * opTwo;
            case '/':
                return opOne / opTwo;
            case '^':
                return (int) Math.pow(opOne, opTwo);
            default:
                return 0;
        }
    }

}
