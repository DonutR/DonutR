package leetcode.veryCommon.design;

import java.util.Hashtable;

/**
 * Use Custom Dequeue and HashTable
 * <p>
 * Dequeue Diagram
 * <p>
 * <------------
 * ^            ^
 * Head        Tail
 * <-  ->       <-  ->
 * pre post     pre post
 * <p>
 * The problem can be solved with a hashtable that keeps track of the keys and its values in the double linked list.
 * One interesting property about double linked list is that the node can remove itself without other reference.
 * In addition, it takes constant time to add and remove nodes from the head or tail.
 * <p>
 * One particularity about the double linked list that I implemented is that I create a pseudo head and tail to mark the boundary, so that we don't need to check the NULL node during the update.
 * This makes the code more concise and clean, and also it is good for the performance.
 */
public class LRUCache {

    private CacheDqueue head;
    private CacheDqueue tail;

    private Hashtable<Integer, CacheDqueue> cache = new Hashtable<Integer, CacheDqueue>();
    private int count;
    private int capacity;


    class CacheDqueue {
        int key;
        int value;
        CacheDqueue pre;
        CacheDqueue post;
    }

    //Always add the new node right after head;
    private void addNode(CacheDqueue node) {
        node.pre = head;
        node.post = head.post;

        head.post.pre = node;
        head.post = node;
    }

    //Remove an existing node from the linked list.
    private void removeNode(CacheDqueue node) {
        CacheDqueue pre = node.pre;
        CacheDqueue post = node.post;

        pre.post = post;
        post.pre = pre;
    }

    //Move certain node in between to the head.
    private void moveToHead(CacheDqueue node) {
        this.removeNode(node);
        this.addNode(node);
    }

    // pop the current tail.
    private CacheDqueue popTail() {
        CacheDqueue res = tail.pre;
        this.removeNode(res);
        return res;
    }


    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;

        head = new CacheDqueue();
        tail = new CacheDqueue();

        head.post = tail;
        tail.pre = head;

        head.pre = null;
        tail.post = null;
    }

    public int get(int key) {
        CacheDqueue node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // move the accessed node to the head;
        this.moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        CacheDqueue node = cache.get(key);

        if (node == null) {
            node = new CacheDqueue();
            node.key = key;
            node.value = value;

            cache.put(key, node);
            this.addNode(node);

            ++count;
            if (count > capacity) {
                // pop the tail
                CacheDqueue tailNode = this.popTail();
                cache.remove(tailNode.key);
                --count;
            }
        } else {
            // update the value.
            node.value = value;
            this.moveToHead(node);
        }
    }
}
