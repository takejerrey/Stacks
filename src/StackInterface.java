/*
 * Author: Jacob Terrey
 * An interface for the stack classes to implement
 */
public interface StackInterface <T> {
    /**
     * Add a value to a stack
     * @param newEntry value to add
     */
    public void push(T newEntry);

    /**
     * Remove value from the top of the stack
     * @return the value in the top of the stack
     */
    public T pop();

    /**
     * Peek to see the top of the stack
     * @return the value at top of the stack
     */
    public T peek();

    /**
     * Check if the stack is empty
     * @return true if the stack is empty, false if not
     */
    public boolean isEmpty();

    /**
     * Clear the stack
     */
    public void clear();

    /**
     * Convert an infix expression to postfix
     * @param infix expression to be converted
     * @return string containing postfix expression
     */
    public String convertToPostfix(String infix);

    /**
     *
     * @param postfix expression to be evaluated
     * @return the evaluated expression
     */
    public String evaluatePostfix(String postfix);

}
