public class ArrayStackTest {
    public static void main (String args[]){
        String s;
        StackInterface<String> arrayStack = new ResizeableArrayStack<>();
        s = arrayStack.evaluatePostfix("ab*ca-/de*+");
        System.out.println(s);
    }

}
