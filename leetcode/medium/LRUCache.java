// https://leetcode.com/problems/lru-cache/
// Problem: LRU Cache
// Difficulty: Medium
// Tag: HashMap, Design, Linked List
// Design a data structure that supports get and put operations in O(1) time using LRU eviction policy.

import java.util.HashMap;

public class LRUCache {
    int capacity;
    HashMap<Integer, Node> map;
    Node head, tail; // Dummy nodes for doubly linked list i.e. head & tail == null

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();

        head = new Node(); // dummy head
        tail = new Node(); // dummy tail

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        Node node = map.get(key);
        removeNode(node);
        addNodeToHead(node);

        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addNodeToHead(node);
        } else {
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addNodeToHead(newNode);

            if (map.size() > capacity) {
                Node lru = tail.prev;
                removeNode(lru);
                map.remove(lru.key);
            }
        }
    }

    // Add a node right after head
    void addNodeToHead(Node node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    // Remove a node from the list
    void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Node class for doubly linked list
    static class Node {
        int key, value;
        Node prev, next;

        public Node() {}
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // Main method to test the LRU Cache
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println("Get 1: " + cache.get(1)); // returns 1

        cache.put(3, 3); // evicts key 2
        System.out.println("Get 2: " + cache.get(2)); // returns -1

        cache.put(4, 4); // evicts key 1
        System.out.println("Get 1: " + cache.get(1)); // returns -1
        System.out.println("Get 3: " + cache.get(3)); // returns 3
        System.out.println("Get 4: " + cache.get(4)); // returns 4
    }
}
