package onboarding;

public class Problem4 {
    public static String solution(String word) {
        StringBuilder sb = new StringBuilder();

        for (char ch : word.toCharArray()) {
            if (!Character.isAlphabetic(ch)) {
                sb.append(ch);
            } else if (Character.isUpperCase(ch)) {
                sb.append((char) ('Z' - ch + 'A'));
            } else {
                sb.append((char) ('z' - ch + 'a'));
            }
        }

        return sb.toString();
    }
}
