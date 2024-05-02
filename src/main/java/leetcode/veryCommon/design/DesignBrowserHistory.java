package leetcode.veryCommon.design;

public class DesignBrowserHistory {
    class BrowserHistory {
        public class Node {
            String url;
            Node prev, next;

            public Node(String url) {
                this.url = url;
                prev = null;
                next = null;
            }
        }

        Node curr, head;

        public BrowserHistory(String homepage) {
            head = new Node(homepage);
            curr = head;
        }

        public void visit(String url) {
            Node next = new Node(url);
            curr.next = next;
            next.prev = curr;
            curr = curr.next;
        }

        public String back(int steps) {
            while (curr.prev != null && steps-- > 0)
                curr = curr.prev;
            return curr.url;
        }

        public String forward(int steps) {
            while (curr.next != null && steps-- > 0)
                curr = curr.next;
            return curr.url;
        }
    }
}
