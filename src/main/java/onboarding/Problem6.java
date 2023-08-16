package onboarding;

import java.util.*;
import java.util.stream.Collectors;

public class Problem6 {

    static boolean haveDuplicateSequence(String str1, String str2) {
        for (int i = 0; i < str1.length() - 1; i++) {
            String sequence = str1.substring(i, i + 2);
            if (str2.contains(sequence)) {
                return true;
            }
        }
        return false;
    }

    static List<String> splitIntoSequences(String str) {
        List<String> sequences = new ArrayList<>();
        for (int i = 0; i < str.length() - 1; i++) {
            char ch1 = str.charAt(i);
            char ch2 = str.charAt(i + 1);
            sequences.add(String.format("%c%c", ch1, ch2));
        }
        return sequences;
    }


    public static List<String> solution(List<List<String>> forms) {
        Map<String, Integer> registeredSequences = new HashMap<>();
        List<String> duplicateUserEmails = new ArrayList<>();

        for (List<String> form : forms) {
            String name = form.get(1);

            List<String> sequences = splitIntoSequences(name);
            sequences.forEach(sequence -> {
                registeredSequences.merge(sequence, 1, Integer::sum);
            });
        }


        for (List<String> form : forms) {
            String name = form.get(1);
            String email = form.get(0);

            List<String> sequences = splitIntoSequences(name);
            boolean hasDuplicateSequence
                    = sequences.stream().anyMatch(s -> registeredSequences.get(s) > 1);
            if (hasDuplicateSequence) {
                duplicateUserEmails.add(email);
            }
        }

        return duplicateUserEmails.stream().sorted().collect(Collectors.toList());
    }
}
