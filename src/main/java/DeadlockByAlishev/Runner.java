package DeadlockByAlishev;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
    private Account accountOne = new Account(1000);
    private Account accountTwo = new Account(1000);

    private Lock lockOne = new ReentrantLock();
    private Lock lockTwo = new ReentrantLock();

    public void runnerForFirstThread() {
        for (int counter = 0; counter < 90; counter++) {
            lockOne.lock();
            lockTwo.lock();
            try {
                Account.transferMoneyFromAccountOneToAccountTwo(accountOne, accountTwo, 10);
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lockOne.unlock();
                lockTwo.unlock();
            }
        }
    }

    public void runnerForSecondThread() {
        for (int counter = 0; counter < 50; counter++) {
            //lockTwo.lock();
            lockOne.lock();
            lockTwo.lock();
            try {
                Account.transferMoneyFromAccountOneToAccountTwo(accountTwo, accountOne, 10);
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            finally {
                lockOne.unlock();
                lockTwo.unlock();
            }
        }
    }

    public void printMoneyStatistics() {
        System.out.println("Total money: " + (accountOne.getBalance() + accountTwo.getBalance()));
        System.out.println("First account money: " + (accountOne.getBalance()));
        System.out.println("Second account money: " + (accountTwo.getBalance()));
    }

}
