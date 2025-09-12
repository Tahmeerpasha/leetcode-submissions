class LFUCache {
    int capacity, minFreq, currSize;
    // freqMap: maps frequency -> doubly linked list of nodes with that frequency
    Map<Integer, DoublyLinkedList> freqMap;
    // cache: maps key -> DLLNode (for O(1) access to node)
    Map<Integer, DLLNode> cache;

    public LFUCache(int capacity) {
        this.capacity = capacity; // max capacity of LFU cache
        this.minFreq = 0; // minimum frequency across all nodes (used for eviction)
        this.currSize = 0; // current number of nodes in cache
        this.freqMap = new HashMap<>();
        this.cache = new HashMap<>();
    }

    // Get value for a key
    public int get(int key) {
        DLLNode node = cache.get(key);
        if (node == null)
            return -1; // key not found
        updateNode(node); // increase its frequency
        return node.val;
    }

    // Put key-value into cache
    public void put(int key, int value) {
        if (capacity == 0) // no space at all
            return;

        if (cache.containsKey(key)) {
            // If key exists, update value and frequency
            DLLNode node = cache.get(key);
            node.val = value;
            updateNode(node);
        } else {
            currSize++;
            // If over capacity -> remove LFU node
            if (currSize > capacity) {
                // get list of nodes with minimum frequency
                DoublyLinkedList minFreqList = freqMap.get(minFreq);
                // remove least recently used node from that list (tail.prev)
                DLLNode nodeToRemove = minFreqList.tail.prev;
                minFreqList.removeNode(nodeToRemove);
                cache.remove(nodeToRemove.key); // remove from cache map
                currSize--;
            }
            // Add new node with frequency = 1
            minFreq = 1; // reset minFreq because we are inserting a new node
            DLLNode node = new DLLNode(key, value);
            // get DLL for freq=1 (create if not exists)
            DoublyLinkedList currList = freqMap.getOrDefault(1, new DoublyLinkedList());
            currList.addNode(node); // insert at head (most recently used)
            freqMap.put(1, currList); // update freqMap
            cache.put(key, node); // store node in cache map
        }
    }

    // Increase frequency of a node and move it to correct list
    private void updateNode(DLLNode node) {
        int currFreq = node.freq;
        DoublyLinkedList currList = freqMap.get(currFreq);
        currList.removeNode(node); // remove from old freq list

        // if this node was the only one with minFreq, increase minFreq
        if (currFreq == minFreq && currList.listSize == 0)
            minFreq++;

        node.freq++; // increase frequency
        // move node to new frequency list
        DoublyLinkedList newList = freqMap.getOrDefault(node.freq, new DoublyLinkedList());
        newList.addNode(node);
        freqMap.put(node.freq, newList);
    }

    // Doubly Linked List Node
    class DLLNode {
        int key; // key
        int val; // value
        int freq; // frequency of access
        DLLNode prev;
        DLLNode next;

        public DLLNode(int key, int val) {
            this.key = key;
            this.val = val;
            this.freq = 1; // new nodes always start with frequency 1
        }
    }

    // Doubly Linked List to maintain nodes with same frequency
    class DoublyLinkedList {
        int listSize; // number of nodes in the list
        DLLNode head; // dummy head
        DLLNode tail; // dummy tail

        public DoublyLinkedList() {
            this.listSize = 0;
            head = new DLLNode(0, 0); // dummy head
            tail = new DLLNode(0, 0); // dummy tail
            head.next = tail;
            tail.prev = head;
        }

        // Always add new node right after head (most recently used in this frequency list)
        public void addNode(DLLNode currNode) {
            DLLNode nextNode = head.next;
            currNode.next = nextNode;
            currNode.prev = head;
            head.next = currNode;
            nextNode.prev = currNode;
            listSize++;
        }

        // Remove any node from the list
        public void removeNode(DLLNode currNode) {
            DLLNode prevNode = currNode.prev;
            DLLNode nextNode = currNode.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            listSize--;
        }
    }
}

/**
 * Usage:
 * LFUCache obj = new LFUCache(capacity);
 * int val = obj.get(key);
 * obj.put(key, value);
 */