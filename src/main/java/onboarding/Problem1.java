package onboarding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Problem1 {
    static enum Operation {
        ADDITION,
        MULTIPLICATION,
    }

    static List<Integer> splitNumbers(List<Integer> numbers) {
        ArrayList<Integer> result = new ArrayList<>();
        for (Integer number : numbers) {
            result.addAll(splitNumber(number));
        }
        return result;
    }

    static List<Integer> splitNumber(Integer number) {
        return Arrays.stream(Integer.toString(number).split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    static int operateBy(Operation operation, int number1, int number2) {
        if (Operation.ADDITION == operation) {
            return number1 + number2;
        }
        return number1 * number2;
    }

    static int getMaxNumberBy(Operation operation, List<Integer> numbers) {
        int maxNumber = Integer.MIN_VALUE;
        for (Integer number1 : numbers) {
            for (Integer number2 : numbers) {
                maxNumber = Math.max(maxNumber, operateBy(operation, number1, number2));
            }
        }
        return maxNumber;
    }

    static int getMaxNumber(List<Integer> numbers) {
        List<Integer> splitNumbers = splitNumbers(numbers);
        return Math.max(
                getMaxNumberBy(Operation.ADDITION, splitNumbers),
                getMaxNumberBy(Operation.MULTIPLICATION, splitNumbers)
        );
    }

    public static int solution(List<Integer> pobi, List<Integer> crong) {
        if ((pobi.get(1) - pobi.get(0)) != (crong.get(1) - crong.get(0))) {
            return -1;
        }
        if (pobi.get(0) == 0 || pobi.get(1) == 400 || crong.get(0) == 0 || crong.get(1) == 400) {
            return -1;
        }
        int pobiMaxNumber = getMaxNumber(pobi);
        int crongMaxNumber = getMaxNumber(crong);
        int result = Integer.compare(pobiMaxNumber, crongMaxNumber);
        if (result == -1) {
            return 2;
        }
        return result;
    }
}