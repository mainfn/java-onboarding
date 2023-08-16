package onboarding;

import java.util.*;
import java.util.stream.Collectors;

public class Problem7 {

    static void giveScoreTo(String name, int score, Map<String, Integer> scoreMap) {
        scoreMap.computeIfPresent(name,
                (s, prevScore) -> prevScore + score);
        scoreMap.putIfAbsent(name, score);
    }

    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        List<String> friendNames;
        Map<String, Integer> scoreMap = new HashMap<>();


        friendNames = friends.stream()
                .filter(names -> !names.contains(user))
                .map(names -> names.get(0).equals(user) ? names.get(1) : names.get(0))
                .distinct()
                .collect(Collectors.toList());

        visitors.stream()
                .filter(visitor -> !friendNames.contains(visitor))
                .forEach(name -> giveScoreTo(name, 1, scoreMap));

        friends.stream()
                .filter(names -> !names.contains(user))
                .forEach(names -> {
                    String name1 = names.get(0);
                    String name2 = names.get(1);
                    if (!friendNames.contains(name2) && friendNames.contains(name1)) {
                        giveScoreTo(name2, 10, scoreMap);
                    }
                    if (!friendNames.contains(name1) && friendNames.contains(name2)) {
                        giveScoreTo(name1, 10, scoreMap);
                    }
                });

        return scoreMap.entrySet()
                .stream()
                .sorted((o1, o2) -> {
                    Integer score1 = o1.getValue();
                    Integer score2 = o2.getValue();
                    if (!Objects.equals(score1, score2)) {
                        return score2 - score1;
                    }
                    String name1 = o1.getKey();
                    String name2 = o2.getKey();
                    return name1.compareTo(name2);
                })
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }


}
