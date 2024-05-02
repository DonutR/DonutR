package leetcode.veryCommon.stack;

import java.util.Stack;
import java.util.stream.Collectors;

public class SimplifyPath {
    public String simplifyPath(String path) {
        String[] pathArr = path.split("/");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < pathArr.length; i++) {
            if (pathArr[i].equals("") || pathArr[i].equals("."))
                continue;
            else if (pathArr[i].equals("..") && !stack.isEmpty())
                stack.pop();
            else if (!pathArr[i].equals(".."))
                stack.push(pathArr[i]);
        }
        return "/" + stack.stream().collect(Collectors.joining("/"));
    }
}
