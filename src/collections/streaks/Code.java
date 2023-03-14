package collections.streaks;

import java.util.*;

public class Code {

    public static List<List<String>> getStreakList(String input) {
        if (input.isEmpty()) {
            return List.of();
        }

        List<List<String>> streak = new ArrayList<>();

        for (String character : input.split("")) {
            boolean unique = true;

            for (List<String> strings : streak) {
                if (strings.get(0).equals(character)) {
                    strings.add(character);
                    unique = false;
                    break;
                }
            }

            if (!unique) {
                continue;
            }

            List<String> element = new ArrayList<>();
            element.add(character);
            streak.add(element);
        }

        return streak;
    }


}
