// 1.0 semaphore
import java.util.concurrent.*;

class FizzBuzz {
    
    private int n;
    private Semaphore sem, sem3, sem5, sem15;

    public FizzBuzz(int n) {
        this.n = n;
        sem = new Semaphore(1);
        sem3 = new Semaphore(0);
        sem5 = new Semaphore(0);
        sem15 = new Semaphore(0);
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 3; i <= n; i += 3) {
            sem3.acquire();
            printFizz.run();
            if ((i + 3) % 5 == 0) // skip multiples of 15.
                i += 3;
            sem.release();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            sem5.acquire();
            printBuzz.run();
            if ((i + 5) % 3 == 0) // skip multiples of 15.
                i += 5;
            sem.release();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 15; i <= n; i += 15) {
            sem15.acquire();
            printFizzBuzz.run();
            sem.release();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; ++i) {
            sem.acquire();
            if (i % 15 == 0) {
                sem15.release();
            }else if (i % 5 == 0) {
                sem5.release();
            }else if (i % 3 == 0) {
                sem3.release();
            }else {
                printNumber.accept(i);
                sem.release();
            } 
        }        
    }
    
}

// 2.0 synchronized & wait & notify
class FizzBuzz {
    
    private int n;
    private int currentNumber = 1;
    
    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public synchronized void fizz(Runnable printFizz) throws InterruptedException {
        while (currentNumber <= n) {
            if (currentNumber % 3 != 0 || currentNumber % 5 == 0) {
                wait();
                continue;
            }
            printFizz.run();
            currentNumber += 1;
            notifyAll();
        }
    }

    // printBuzz.run() outputs "buzz".
    public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
        while (currentNumber <= n) {
            if (currentNumber % 5 != 0 || currentNumber % 3 == 0) {
                wait();
                continue;
            }
            printBuzz.run();
            currentNumber += 1;
            notifyAll();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (currentNumber <= n) {
            if (currentNumber % 15 != 0) {
                wait();
                continue;
            }
            printFizzBuzz.run();
            currentNumber += 1;
            notifyAll();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void number(IntConsumer printNumber) throws InterruptedException {
        while (currentNumber <= n) {
            if (currentNumber % 3 == 0 || currentNumber % 5 == 0) {
                wait();
                continue;
            }
            printNumber.accept(currentNumber);
            currentNumber += 1;
            notifyAll();
        }
    }
}