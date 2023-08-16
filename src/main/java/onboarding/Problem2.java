package onboarding;

import java.util.Arrays;
import java.util.Stack;

public class Problem2 {

    public static String solution(String cryptogram) {
        Stack<String> stack = new Stack();

        Arrays.stream(cryptogram.split(""))
                .forEach(ch -> {
                    if (!stack.isEmpty() && stack.lastElement().equals(ch)) {
                        stack.pop();
                    } else {
                        stack.add(ch);
                    }
                });

        return String.join("", stack);
    }
}
