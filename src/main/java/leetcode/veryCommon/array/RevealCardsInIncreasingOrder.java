package leetcode.veryCommon.array;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class RevealCardsInIncreasingOrder {
    public int[] deckRevealedIncreasing1(int[] deck) {
        int N = deck.length;
        Deque<Integer> dq = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            dq.add(i);
        }

        Arrays.sort(deck);
        int[] ans = new int[N];
        for (int card : deck) {
            ans[dq.pollFirst()] = card;
            if (!dq.isEmpty())
                dq.add(dq.pollFirst());
        }
        return ans;
    }
}
