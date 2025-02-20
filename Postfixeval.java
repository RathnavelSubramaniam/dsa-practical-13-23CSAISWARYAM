import java.io.*;
import java.util.*;

class stack {
    int size;
    int item[];
    int top;

    public stack() {
        size = 100;
        item = new int[size];
        top = -1;
    }

    public void push(int ele) {
        if (top == (size - 1)) {
            System.out.println("Stack Overflow");
        } else {
            top++;
            item[top] = ele;
        }
    }

    public int pop() {
        if (top == -1) {
            System.out.println("Invalid Postfix string; Operators are given more than operands");
            return (-1);
        } else {
            int x = item[top];
            top--;
            return (x);
        }
    }

    public int peek() {
        if (top == -1) {
            System.out.println("No Elements");
            return (-1);
        } else
            return (item[top]);
    }

    public void display() {
        System.out.println();
        if (top == -1) {
            System.out.println("No Elements");
        } else {
            System.out.println("Stack is");
            for (int i = 0; i <= top; i++)
                System.out.println(item[i]);
        }
    }

    public boolean isEmpty() {
        if (top == -1)
            return true;
        else
            return false;
    }
}

class evalpostfix {
    stack st = new stack();
    String postfix;

    public evalpostfix(String str) {
        postfix = str;
    }

    public boolean isOperand(char ch) {
        return (ch >= '0' && ch <= '9');
    }

    public int eval() {
        char po[] = postfix.toCharArray();
        int i = 0;
        int a, b;

        for (i = 0; i < po.length; i++) {
            if (isOperand(po[i])) {
                st.push(po[i] - '0');
            } else {
                if (!st.isEmpty()) {
                    b = st.pop();
                    a = st.pop();

                    if (a == -1 || b == -1) {
                        return -1;
                    }

                    switch (po[i]) {
                        case '+':
                            st.push(b + a);
                            break;
                        case '-':
                            st.push(b - a);
                            break;
                        case '*':
                            st.push(a * b);
                            break;
                        case '/':
                            st.push(b / a);
                            break;
                        case '%':
                            st.push(b % a);
                            break;
                        default:
                            System.out.println("Invalid operator");
                            return -1;
                    }
                } else {
                    return -1;
                }
            }
        }

        int res_val = st.pop();
        if (!st.isEmpty()) {
            return -1;
        } else {
            return res_val;
        }
    }
}

public class Postfixeval {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter postfix string");
        String str = sc.nextLine();
        evalpostfix epf = new evalpostfix(str);
        int res = epf.eval();
        if (res != -1)
            System.out.println("Result: " + res);
        else
            System.out.println("Invalid Postfix String");
    }
}