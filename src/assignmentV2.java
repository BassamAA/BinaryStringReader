import java.util.Stack;

public class assignmentV2 { //iterative revealStr method using stack

    public static void main(String[] args) {

        revealStr("101**1*0"); // input a string of 0's , 1's and * char
    }

    static void revealStr(String binary) {
        //last in first out scheme (LIFO)

        int ind; // ind is the index
        int count = 0;
        Stack<String> stringStack = new Stack<String>(); //generic string time stack
        stringStack.push(binary); //pushing our string binary onto the stack

        //isEmpty boolean value
        while(!stringStack.isEmpty()) { // loop stops when stack is empty

            String revealedString = stringStack.pop(); // storing the popped binary string in revealed string to be processed

            ind = revealedString.indexOf('*');
            if(ind != -1) { // if a * char has not been found it will return -1

                for (char i = '0'; i <= '1'; i++) { //looping through '0' and '1' char at indexOf *
                    String totalString = revealedString.substring(0,ind) + i + revealedString.substring(ind+1);
                    stringStack.push(totalString); //pushing each permutation (0 , 1) of the * in the revealed string
                }
            }
            else { // if no * character is found
                count++;
                System.out.println(revealedString);
            }
        }
        System.out.println("The count is equal to " + count);

    }

}
