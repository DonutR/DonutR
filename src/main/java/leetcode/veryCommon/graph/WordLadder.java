package leetcode.veryCommon.graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WordLadder {
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashMap<String, List<String>> adjWordMap = getAdjWordMap(wordList);

        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        q.add(null);
        HashSet<String> visited = new HashSet<>();
        visited.add(beginWord);

        int out = 1;
        while (!q.isEmpty()) {
            String word = q.poll();
            if (word == null) {
                out++;
                if (!q.isEmpty())
                    q.add(null);
            } else {
                for (String pattern : getWordPattern(word)) {
                    for (String newWord : adjWordMap.getOrDefault(pattern, new ArrayList<>())) {
                        if (!visited.contains(newWord)) {
                            if (newWord.equals(endWord))
                                return ++out;
                            q.add(newWord);
                            visited.add(newWord);
                        }
                    }
                }
            }
        }
        return 0;
    }

    public static HashMap<String, List<String>> getAdjWordMap(List<String> wordList) {
        HashMap<String, List<String>> adjWordMap = new HashMap<>();

        wordList.stream()
                .forEach(word -> getWordPattern(word).stream()
                        .forEach(pattern -> adjWordMap.computeIfAbsent(pattern, y -> new ArrayList<>()).add(word))
                );
        return adjWordMap;
    }

    public static List<String> getWordPattern(String input) {
        return IntStream.range(0, input.length())
                .mapToObj(i -> input.substring(0, i) + "*" + input.substring(i + 1, input.length()))
                .collect(Collectors.toList());
    }
}
