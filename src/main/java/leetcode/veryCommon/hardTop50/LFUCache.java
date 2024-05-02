package leetcode.veryCommon.hardTop50;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {
    Map<Integer, CacheDqueueNode> lfuCache;
    Map<Integer, LRUCache> countMap;
    int capacity;
    int min;
    int count;

    public LFUCache(int capacity) {
        this.capacity=capacity;

        lfuCache = new HashMap<>();
        countMap = new HashMap<>();
    }

    public int get(int key) {
        if (!lfuCache.containsKey(key))
            return -1;
        else {
            CacheDqueueNode node = lfuCache.get(key);
            updateAccessCt(node);
            System.out.println(min);
            return node.value;
        }
    }

    public void put(int key, int value) {
        if (!lfuCache.containsKey(key)) {
            CacheDqueueNode node = new CacheDqueueNode();
            node.key = key;
            node.value = value;

            lfuCache.put(key, node);

            count++;

            if (count <= capacity) {
                updateAccessCt(node);
            } else if (count > capacity) {
                CacheDqueueNode delNode = countMap.get(min).popTail();
                lfuCache.remove(delNode.key);
                count--;
            }

            min = 1;
        } else {
            CacheDqueueNode node = lfuCache.get(key);
            updateAccessCt(node);
        }
    }

    public void updateAccessCt(CacheDqueueNode node) {
        if (countMap.containsKey(node.accessCount)) {
            LRUCache tmpCache = countMap.get(node.accessCount);
            tmpCache.removeNode(node);
            if (tmpCache.isEmpty())
                min = node.accessCount + 1;
        }
        node.accessCount++;
        if (!countMap.containsKey(node.accessCount)) {
            LRUCache tmpCache = new LRUCache();
            tmpCache.addNode(node);
            countMap.put(node.accessCount, tmpCache);
        } else {
            countMap.get(node.accessCount).addNode(node);
        }
    }

    public class CacheDqueueNode {
        int key;
        int value;
        int accessCount;
        CacheDqueueNode pre;
        CacheDqueueNode post;
    }

    public class LRUCache {
        int size;

        private CacheDqueueNode head;
        private CacheDqueueNode tail;

        public boolean isEmpty() {
            return size == 0 ? true : false;
        }

        //Always add the new node right after head;
        public void addNode(CacheDqueueNode node) {
            node.pre = head;
            node.post = head.post;

            head.post.pre = node;
            head.post = node;

            size++;
        }

        //Remove an existing node from the linked list.
        public void removeNode(CacheDqueueNode node) {
            CacheDqueueNode pre = node.pre;
            CacheDqueueNode post = node.post;

            pre.post = post;
            post.pre = pre;

            size--;
        }

        // pop the current tail.
        public CacheDqueueNode popTail() {
            CacheDqueueNode res = tail.pre;
            this.removeNode(res);

            size--;

            return res;
        }


        public LRUCache() {
            head = new CacheDqueueNode();
            tail = new CacheDqueueNode();

            head.post = tail;
            tail.pre = head;

            head.pre = null;
            tail.post = null;
        }
    }
}
