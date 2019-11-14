// 1.0 semaphore

class DiningPhilosophers {

    private Semaphore[] forks = new Semaphore[5];
    private Semaphore eatingPeople = new Semaphore(4); // 1~4 all ok
    
    public DiningPhilosophers() {
        for (int i = 0; i < 5; i++){
            forks[i] = new Semaphore(1);
        }
    }
    
    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        
        Semaphore leftFork = forks[(philosopher + 5 - 1 ) % 5];
        Semaphore rightFork = forks[philosopher];
        
        eatingPeople.acquire();
        
        leftFork.acquire();
        rightFork.acquire();
        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();
        leftFork.release();
        rightFork.release();
        
        eatingPeople.release();
        
    }
}

// 2.0 lock
class DiningPhilosophers {
    boolean[] eating;
    Object lock;
    public DiningPhilosophers() {
        eating=new boolean[5];
        lock=new Object();'
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        int left=(philosopher+4)%5;
        int right=(philosopher+1)%5;
        while(true) {
            synchronized(lock) {
                if(eating[left]||eating[right]) {
                    lock.wait();
                    continue;
                }
                eating[philosopher]=true;
            }
            break;
        }
        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();
        synchronized(lock) {
            eating[philosopher]=false;
            lock.notifyAll();
        }
    }
}

// single thread
class DiningPhilosophers {

    public DiningPhilosophers() {
        
    }

    // call the run() method of any runnable to execute its code
    public synchronized void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();        
    }
}