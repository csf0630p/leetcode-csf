// 1.0 Java ReentrantLock + Condition + Circular Queue Solution
// https://leetcode.com/problems/design-bounded-blocking-queue/discuss/380140/Java-ReentrantLock-%2B-Condition-Solution
import java.util.concurrent.locks.ReentrantLock; 
import java.util.concurrent.locks.Condition; 

class BoundedBlockingQueue {
    private ReentrantLock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();
    private int[] queue;
    private int tail = 0;
    private int head = 0;
    private int size = 0;
    public BoundedBlockingQueue(int capacity) {
        queue = new int[capacity];
    }
    
    public void enqueue(int element) throws InterruptedException {
        try {
            lock.lock();
            while(size == queue.length) {
                full.await();
            }
            queue[tail++] = element;
            tail %= queue.length;
            size++;
            empty.signal();
        } finally {
            lock.unlock();
        }
    }
    
    public int dequeue() throws InterruptedException {
        try {
            lock.lock();
            while(size == 0) {
                empty.await();
            }
            int res = queue[head++];
            head %= queue.length;
            size--;
            full.signal();
            return res;
        } finally {
            lock.unlock();
        }
    }
    
    public int size() throws InterruptedException {
        lock.lock();
		try {
			return this.size;
		} finally {
			lock.unlock();
		}
    }
}

//1.1 use linkedList
import java.util.concurrent.locks.ReentrantLock; 
import java.util.concurrent.locks.Condition; 

class BoundedBlockingQueue {
    private ReentrantLock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();
    private LinkedList<Integer> queue;
    private int tail = 0;
    private int head = 0;
    private int maxSize = 0;
    public BoundedBlockingQueue(int capacity) {
        queue = new LinkedList<>();
        this.maxSize = capacity;
    }
    
    public void enqueue(int element) throws InterruptedException {
        try {
            lock.lock();
            while(maxSize == queue.size()) {
                full.await();
            }
            queue.add(element);
            empty.signal();
        } finally {
            lock.unlock();
        }
    }
    
    public int dequeue() throws InterruptedException {
        try {
            lock.lock();
            while(queue.isEmpty()) {
                empty.await();
            }
            int res = queue.get(0);
            queue.remove();
            full.signal();
            return res;
        } finally {
            lock.unlock();
        }
    }
    
    public int size() throws InterruptedException {
		try {
			lock.lock();			
			return this.queue.size();
		} finally {
			lock.unlock();
		}
    }
}

// wrong: Semaphores, because no reentrant
// https://leetcode.com/problems/design-bounded-blocking-queue/discuss/380303/Java-8ms-Solution-using-Semaphores

// wrong: synchronized is not very efficient in this case because there's only 1 wait set for producers and consumers. It would be better to use a lock with 2 conditions.
// https://leetcode.com/problems/design-bounded-blocking-queue/discuss/381219/Java-7ms-Solution-using-Signals

// 2.0 synchronized and reentrant operation with while loop
class BoundedBlockingQueue {

    private Queue<Integer> q = null;
    private int capacity = 0;
    public BoundedBlockingQueue(int capacity) {
        this.q = new LinkedList<>();
        this.capacity = capacity;
    }
    
    public void enqueue(int element) throws InterruptedException {
        boolean produced = false;
        while (!produced) {
            synchronized (this.q) {
                if (this.q.size() < capacity) {
                    q.offer(element);
                    produced = true;
                }
            }
        }
    }
    
    public int dequeue() throws InterruptedException {
        boolean consumed = false;
        Integer ret = null;
        while (!consumed) {
            synchronized (this.q) {
                if (!this.q.isEmpty()) {
                    ret = this.q.poll();
                    consumed = true;
                }
            }
        }
        return ret;
    }
    
    public int size() {
        return this.q.size();
    }
}

//https://blog.csdn.net/antony9118/article/details/51500278