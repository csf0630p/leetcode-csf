// 1.0
class LRUCache {

    private LinkedHashMap<Integer, Integer> map;
    private int CAPACITY;
    
    public LRUCache(int capacity) {
        this.CAPACITY = capacity;
        map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true){
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > CAPACITY;
            }
        };
    }
    
    public int get(int key) {
        return map.getOrDefault(key, -1);
    }
        
    public void set(int key, int value) {
        map.put(key, value);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
 
 // 2.0
public class LRUCache {
    private class LinkNode {
        int key;
        int val;
        LinkNode pre = null;
        LinkNode next = null;
        LinkNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private int cap;
    private HashMap<Integer, LinkNode> map;
    private LinkNode head;
    private LinkNode tail;
    public LRUCache(int capacity) {
        this.cap = capacity;
        this.map = new HashMap<Integer, LinkNode>();
        this.head = new LinkNode(-1, -1);
        this.tail = new LinkNode(-1, -1);
        head.next = tail;
        tail.pre = head;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            LinkNode temp = map.get(key);
            moveUsedToTail(temp);
            return temp.val;
        } else {
            return -1;
        }
    }
    
    public void set(int key, int value) {
        if (map.containsKey(key)) {
            LinkNode temp = map.get(key);
            temp.val = value;
            moveUsedToTail(temp);   
        } else {
            if (map.size() >= cap) {
                map.remove(head.next.key);
                removeHead();
            }
            LinkNode newNode = new LinkNode(key, value);
            addTail(newNode);
            map.put(key, newNode);
        }
    }
    
    public void moveUsedToTail(LinkNode node) {
        removeNode(node);
        addTail(node);
    }

    public void removeHead(){//O(1)
        removeNode(head.next);
    }
    public void addTail(LinkNode node){//O(1)
        tail.pre.next = node;
        node.pre = tail.pre;
        node.next = tail;
        tail.pre = node;
    }
    public void removeNode(LinkNode node) {//O(1)
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
}

//2.1
class LRUCache {
    class Node{
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value){
            this.key=key;
            this.value=value;
        }
    }
    
    Node head;
    Node tail;
    HashMap<Integer, Node> map = null;
    int cap = 0;
 
    public LRUCache(int capacity) {
        this.cap = capacity;
        this.map = new HashMap<>();
    }
 
    public int get(int key) {
        if(map.get(key)==null){
            return -1;
        }
 
        //move to tail
        Node t = map.get(key);
 
        removeNode(t);
        offerNode(t);
 
        return t.value;
    }
 
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node t = map.get(key);
            t.value = value;
 
            //move to tail
            removeNode(t);
            offerNode(t);
        }else{
            if(map.size()>=cap){
                //delete head
                map.remove(head.key);
                removeNode(head);
            }
 
            //add to tail
            Node node = new Node(key, value);
            offerNode(node);
            map.put(key, node);
        }
    }
 
    private void removeNode(Node n){
        if(n.prev!=null){
            n.prev.next = n.next;
        }else{
            head = n.next;
        }
 
        if(n.next!=null){
            n.next.prev = n.prev;
        }else{
            tail = n.prev;
        }
    }
 
    private void offerNode(Node n){
        if(tail!=null){
            tail.next = n;
        }
 
        n.prev = tail;
        n.next = null;
        tail = n;
 
        if(head == null){
            head = tail;   
        }
    }
}

//2.2
class LRUCache {
    class Node{
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value){
            this.key=key;
            this.value=value;
        }
    }
    
    Node head = null;
    Node tail = null;
    HashMap<Integer, Node> map = null;
    int cap = 0;
 
    public LRUCache(int capacity) {
        this.cap = capacity;
        this.map = new HashMap<>();
    }
 
    public int get(int key) {
        if(map.get(key)==null){
            return -1;
        }
 
        //move to head
        Node t = map.get(key);
 
        removeNode(t);
        offerNode(t);
 
        return t.value;
    }
 
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node t = map.get(key);
            t.value = value;
 
            //move to head
            removeNode(t);
            offerNode(t);
        }else{
            if(map.size()>=cap){
                //delete tail
                map.remove(tail.key);
                removeNode(tail);
            }
 
            //add to head
            Node node = new Node(key, value);
            offerNode(node);
            map.put(key, node);
        }
    }
 
    private void removeNode(Node n){
        if(n != head){
            n.prev.next = n.next;
        }else{
            head = n.next;
        }
 
        if(n != tail){
            n.next.prev = n.prev;
        }else{
            tail = n.prev;
        }
    }
 
    private void offerNode(Node n){
        if(head!=null){
            head.prev = n;
        }
 
        n.next = head;
        n.prev = null;
        head = n;
 
        if(tail == null){
            tail = head;   
        }
    }
}
