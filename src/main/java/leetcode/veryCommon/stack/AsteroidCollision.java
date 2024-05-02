package leetcode.veryCommon.stack;

import java.util.Stack;

public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] > 0)
                stack.push(asteroids[i]);
            else if (!stack.isEmpty() && stack.peek() > 0 && asteroids[i] < 0) {
                boolean bothDestroyed = false;
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() <= Math.abs(asteroids[i])) {
                    int val = stack.pop();
                    if (val == Math.abs(asteroids[i])) {
                        bothDestroyed = true;
                        break;
                    }
                }
                if (!bothDestroyed && (stack.isEmpty() || stack.peek() < 0))
                    stack.push(asteroids[i]);
            } else
                stack.push(asteroids[i]);
        }
        return stack.stream().mapToInt(a -> a).toArray();
    }
}
