package onboarding;

import java.util.Arrays;
import java.util.List;

public class Problem3 {

    static int count369(int number) {
        int count = 0;
        while (number > 0) {
            int n = number % 10;
            if (n == 3 || n == 6 || n == 9) {
                count++;
            }
            number /= 10;
        }
        return count;
    }


    public static int solution(int number) {
        int answer = 0;
        for (int i = 0; i <= number; i++) {
            answer += count369(i);
        }
        return answer;
    }
}
