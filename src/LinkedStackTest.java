
public class LinkedStackTest {
    public static void main (String args[]){
        String s;
        StackInterface<String> linkStack = new LinkedStack<String>();
        s = linkStack.convertToPostfix("a*b/(c-a)+d*e");
        System.out.println(s);
    }
}
