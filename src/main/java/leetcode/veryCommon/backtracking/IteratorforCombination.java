package leetcode.veryCommon.backtracking;

public class IteratorforCombination {
    class CombinationIterator {
        int[] idxArr;
        String characters;
        int len;
        int combinationLength;
        int diff;

        public CombinationIterator(String characters, int combinationLength) {
            this.idxArr = new int[combinationLength];
            this.characters = characters;
            this.combinationLength = combinationLength;
            diff = this.characters.length() - this.combinationLength;
            this.len = characters.length() - 1;
            for (int i = 0; i < combinationLength; i++)
                idxArr[i] = i;
            idxArr[combinationLength - 1] = combinationLength - 2;
        }

        public String next() {
            StringBuilder sb = new StringBuilder();
            for (int i = combinationLength - 1; i >= 0; i--) {
                if (idxArr[i] >= i + diff) {
                    idxArr[i] = Math.min(idxArr[i - 1] + 2, len);
                    continue;
                } else {
                    idxArr[i] = Math.min(idxArr[i] + 1, len);
                    break;
                }
            }

            for (int i = 0; i < combinationLength; i++)
                sb.append(characters.charAt(idxArr[i]));
            return sb.toString();
        }

        public boolean hasNext() {
            for (int i = 0; i < combinationLength; i++)
                if (idxArr[i] < i + diff)
                    return true;
            return false;
        }
    }
}
