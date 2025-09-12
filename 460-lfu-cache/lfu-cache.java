class LFUCache {
    int capacity, minFreq, currSize;
    Map<Integer, DoublyLinkedList> freqMap;
    Map<Integer, DLLNode> cache;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.currSize = 0;
        this.freqMap = new HashMap<>();
        this.cache = new HashMap<>();
    }

    public int get(int key) {
        DLLNode node = cache.get(key);
        if (node == null)
            return -1;
        updateNode(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (capacity == 0)
            return;

        if (cache.containsKey(key)) {
            DLLNode node = cache.get(key);
            node.val = value;
            updateNode(node);
        } else {
            currSize++;
            if (currSize > capacity) {
                DoublyLinkedList minFreqList = freqMap.get(minFreq);
                DLLNode nodeToRemove = minFreqList.tail.prev;
                minFreqList.removeNode(nodeToRemove);
                cache.remove(nodeToRemove.key);
                currSize--;
            }
            minFreq = 1;
            DLLNode node = new DLLNode(key, value);
            DoublyLinkedList currList = freqMap.getOrDefault(1, new DoublyLinkedList());
            currList.addNode(node);
            freqMap.put(1, currList);
            cache.put(key, node);
        }
    }

    private void updateNode(DLLNode node) {
        int currFreq = node.freq;
        DoublyLinkedList currList = freqMap.get(currFreq);
        currList.removeNode(node);

        if (currFreq == minFreq && currList.listSize == 0)
            minFreq++;
        node.freq++;
        DoublyLinkedList newList = freqMap.getOrDefault(node.freq, new DoublyLinkedList());
        newList.addNode(node);
        freqMap.put(node.freq, newList);
    }

    class DLLNode {
        int key;
        int val;
        int freq;
        DLLNode prev;
        DLLNode next;

        public DLLNode(int key, int val) {
            this.key = key;
            this.val = val;
            this.freq = 1;
        }
    }

    class DoublyLinkedList {
        int listSize;
        DLLNode head;
        DLLNode tail;

        public DoublyLinkedList() {
            this.listSize = 0;
            head = new DLLNode(0, 0);
            tail = new DLLNode(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        public void addNode(DLLNode currNode) {
            DLLNode nextNode = head.next;
            currNode.next = nextNode;
            currNode.prev = head;
            head.next = currNode;
            nextNode.prev = currNode;
            listSize++;
        }

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
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */