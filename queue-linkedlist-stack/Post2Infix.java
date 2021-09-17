import java.util.Stack;
import java.util.Scanner;
public class Post2Infix
{

    public boolean isOperand(char x)
    {
         return (x >= 'a' && x <= 'z') ||
           (x >= 'A' && x <= 'Z') ||
           (x >= '0' && x <= '9');
    }

    // Get Infix for a given postfix expression
    public String getInfix(String exp)
    {
        Stack<String> s = new Stack<String>();

        for (int i = 0; i < exp.length(); i++)
        {
            // Push operands
            if (isOperand(exp.charAt(i)))
            {
                s.push(exp.charAt(i) + "");
            }

            /*  Assume that input is
                a valid postfix and expect
                an operator. */
            else
            {
                String op1 = s.peek();
                s.pop();
                String op2 = s.peek();
                s.pop();
                s.push("(" + op2 + exp.charAt(i) +
                    op1 + ")");
            }
        }

        /*  There must be a single element
            in stack now which is the required
            infix.  */
        return s.peek();
    }
    
    public static void main(String[]a){
        Scanner in = new Scanner(System.in);
        
        Post2Infix p2i = new Post2Infix();
        String postExp;
        System.out.print("Enter postfix expression: ");
        postExp = in.next();
        
        System.out.println("Infix is " + p2i.getInfix(postExp));
    }
}