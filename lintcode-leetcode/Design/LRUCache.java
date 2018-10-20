/** 146. LRU Cache(leetcode) / 134. LRU Cache(lintcode)
 *      Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following 
 *  operations: get and put.
 *         1) get(key) - Get the value (will always be positive) of the key if the key exists in the cache, 
 *      otherwise return -1.
 *         2) put(key, value) - Set or insert the value if the key is not already present. When the cache reached 
 *      its capacity, it should invalidate the least recently used item before inserting a new item.
 *
 *      Follow up: Could you do both operations in O(1) time complexity?
 *
 *      Example: LRUCache cache = new LRUCache( 2 /* capacity */ );
 *                  cache.put(1, 1);
 *                  cache.put(2, 2);
 *                  cache.get(1);       // returns 1
 *                  cache.put(3, 3);    // evicts key 2
 *                  cache.get(2);       // returns -1 (not found)
 *                  cache.put(4, 4);    // evicts key 1
 *                  cache.get(1);       // returns -1 (not found)
 *                  cache.get(3);       // returns 3
 *                  cache.get(4);       // returns 4
 */

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
 
 //My Method: Use TreeMap + HashMap
 class LRUCache {
    TreeMap<Integer, Integer> used_list; // used_list<timestamp, key>
    Map<Integer, Integer> map; // map<key, value>
    Map<Integer, Integer> time;// time<key, timestamp>
    
    int timestamp;
    int capacity;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        timestamp = 0;
        used_list = new TreeMap<Integer, Integer>();
        map = new HashMap<Integer, Integer>();
        time = new HashMap<Integer, Integer>();
    }
    
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        int old_timestamp = time.get(key);
        time.put(key, timestamp);
        used_list.remove(old_timestamp);
        used_list.put(timestamp, key);
        timestamp++;
        return map.get(key);
    }
    
    public void put(int key, int value) {
        
        if(map.containsKey(key)){// set new value to key
            int old_timestamp = time.get(key);
            used_list.remove(old_timestamp);
        }
        else{// insert (key, value) pair
            if(map.size() == capacity){
                int need_remove_timestamp = used_list.firstKey();
                int need_remove_key = used_list.get(need_remove_timestamp);
                time.remove(need_remove_key);
                map.remove(need_remove_key);
                used_list.remove(need_remove_timestamp);
            }
            
        }
        used_list.put(timestamp, key);
        time.put(key, timestamp);
        map.put(key, value);
        timestamp++;
    }
}

//Method 2: Linked List + HashMap
class LRUCache {
    class Node{
        int key;
        int value;
        Node next;
        Node pre;
        
        public Node(int key, int value){
            this.key = key;
            this.value = value;
            next = null;
            pre = null;
        }
    }
    
    Node head;
    Node tail;
    Map<Integer, Node> map; // map<key, node/pointer to node>
    int capacity;
    int size;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.pre = head;
        map = new HashMap<Integer, Node>();
        size = 0;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        Node node = map.get(key);
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
        
        return node.value;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.pre = head;
            node.next = head.next;
            head.next.pre = node;
            head.next = node;
        }
        else{
            if(size == capacity){// reach full size
                //delete the last node;
                Node lastNode = tail.pre;
                lastNode.pre.next = tail;
                tail.pre = lastNode.pre;
                map.remove(lastNode.key);
                size--;
            }
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            newNode.pre = head;
            newNode.next = head.next;
            head.next.pre = newNode;
            head.next = newNode;
            size++;
        }
    }
}
