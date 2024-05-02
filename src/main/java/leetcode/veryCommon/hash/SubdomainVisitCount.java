package leetcode.veryCommon.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SubdomainVisitCount {
    public List<String> subdomainVisits(String[] cpdomains) {
        HashMap<String, Integer> mp = new HashMap<>();
        for (String s : cpdomains) {
            Integer ct = Integer.parseInt(s.split(" ")[0]);
            String[] domainArr = s.split(" ")[1].split("\\.");
            for (int i = 0; i < domainArr.length; i++) {
                String domain = Arrays.stream(Arrays.copyOfRange(domainArr, i, domainArr.length)).collect(Collectors.joining("."));
                mp.put(domain, mp.getOrDefault(domain, 0) + ct);
            }
        }
        return mp.entrySet().stream().map(es -> es.getValue() + " " + es.getKey()).collect(Collectors.toList());
    }
}
