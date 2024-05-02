package leetcode.veryCommon.hardTop50;

public class IntegertoEnglishWords {
    private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if (num == 0)
            return "Zero";
        String words = "";
        int i = 0;
        while (num > 0) {
            int rem = num % 1000;
            num /= 1000;
            if (rem > 0)
                words = hundredsToWords(rem) + " " + THOUSANDS[i] + " " + words;
            i++;
        }
        return words.trim();
    }

    public String hundredsToWords(int num) {
        String words = "";
        if (num < 20) {
            words = LESS_THAN_20[num];
        } else {
            if (num % 100 < 20 && num % 100 > 0) {
                words = " " + LESS_THAN_20[num % 100];
                num /= 100;
            } else {
                if (num % 10 > 0)
                    words = " " + LESS_THAN_20[num % 10];
                num /= 10;
                if (num > 0 && num % 10 > 0)
                    words = " " + TENS[num % 10] + words;
                num /= 10;
            }
            if (num > 0)
                words = LESS_THAN_20[num] + " Hundred" + words;
        }
        return words.trim();
    }
}
