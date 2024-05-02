package leetcode.veryCommon.design;

import java.util.HashMap;
import java.util.Random;

public class EncodeandDecodeTinyURL {
    String tinyUrlPrefix = "http://tinyurl.com/";
    HashMap<String, String> urlToTiny = new HashMap<>();
    HashMap<String, String> tinyToUrl = new HashMap<>();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (!urlToTiny.containsKey(longUrl)) {
            while (!urlToTiny.containsKey(longUrl)) {
                String encodedStr = "" + new Random().nextInt(Integer.MAX_VALUE);
                if (!tinyToUrl.containsKey(encodedStr)) {
                    tinyToUrl.put(tinyUrlPrefix + encodedStr, longUrl);
                    urlToTiny.put(longUrl, tinyUrlPrefix + encodedStr);
                }
            }
        }
        return urlToTiny.get(longUrl);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return tinyToUrl.getOrDefault(shortUrl, "");
    }
}
