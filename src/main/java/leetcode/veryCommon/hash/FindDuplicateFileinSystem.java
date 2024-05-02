package leetcode.veryCommon.hash;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FindDuplicateFileinSystem {
    public List<List<String>> findDuplicate(String[] paths) {
        HashMap<String, List<String>> fs = new HashMap<>();
        for (String s : paths) {
            String[] sSplit = s.split(" ");
            String dir = sSplit[0];
            for (int i = 1; i < sSplit.length; i++) {
                String[] fileArr = sSplit[1].split("\\(");
                String fileN = fileArr[0];
                String data = fileArr[1].substring(0, fileArr[1].length() - 1);
                List<String> files = fs.getOrDefault(data, new LinkedList<>());
                files.add(dir + "/" + fileN);
                fs.put(data, files);
            }
        }
        return fs.entrySet().stream().map(a -> a.getValue()).filter(a -> a.size() > 1).collect(Collectors.toList());
    }
}
