package leetcode.veryCommon.graph;

import java.util.*;
import java.util.stream.IntStream;

public class WordLadderII {
    public static HashMap<String, List<String>> getAdjWordMap(List<String> wordList) {
        HashMap<String, List<String>> adjWordMap = new HashMap<>();

        wordList.stream().forEach(word -> {
            getWordPattern(word).stream().forEach(pattern -> {
                List<String> tmpList = adjWordMap.getOrDefault(pattern, new ArrayList<>());
                tmpList.add(word);
                adjWordMap.put(pattern, tmpList);
            });
        });
        return adjWordMap;
    }

    public static List<String> getWordPattern(String input) {
        List<String> ret = new ArrayList<>();
        IntStream.range(0, input.length()).forEach(i -> {
            ret.add(input.substring(0, i) + "*" + input.substring(i + 1, input.length()));
        });
        return ret;
    }

    //BFS With Custom Class
    class BfsNode {
        String word;
        LinkedHashSet<String> path;

        public BfsNode(String word, LinkedHashSet<String> path) {
            this.word = word;
            this.path = path;
        }
    }

    public List<List<String>> findLadders1(String beginWord, String endWord, List<String> wordList) {
        HashMap<String, List<String>> adjWordMap = getAdjWordMap(wordList);
        List<List<String>> outList = new ArrayList<>();
        //Used to reduce the BFS path by removing any word which was visited in a lesser number of cycles from future paths.
        HashMap<String, Integer> wordFoundLevel = new HashMap<>();
        int level = 0;
        Queue<BfsNode> q = new LinkedList<>();
        LinkedHashSet<String> path = new LinkedHashSet<>();
        path.add(beginWord);
        q.add(new BfsNode(beginWord, path));
        q.add(null);
        wordFoundLevel.put(beginWord, level);

        while (!q.isEmpty()) {
            BfsNode node = q.poll();
            if (node == null) {
                level++;
                if (!q.isEmpty())
                    q.add(null);
                if (outList.size() > 0) return outList;
            } else {
                for (String pattern : getWordPattern(node.word)) {
                    for (String newWord : adjWordMap.getOrDefault(pattern, new ArrayList<>())) {
                        if (!node.path.contains(newWord) && wordFoundLevel.getOrDefault(newWord, 0) <= level) {
                            wordFoundLevel.put(newWord, level);
                            if (newWord.equals(endWord)) {
                                node.path.add(newWord);
                                outList.add(new LinkedList<>(node.path));
                            } else {
                                LinkedHashSet<String> newPath = new LinkedHashSet<>(node.path);
                                newPath.add(newWord);
                                BfsNode nextNode = new BfsNode(newWord, newPath);
                                q.add(nextNode);
                            }
                        }
                    }
                }
            }
        }
        return outList;
    }
}
