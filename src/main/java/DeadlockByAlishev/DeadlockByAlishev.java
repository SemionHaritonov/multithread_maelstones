package DeadlockByAlishev;

public class DeadlockByAlishev {
    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.printMoneyStatistics();

        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.runnerForFirstThread();
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.runnerForSecondThread();
            }
        });

        threadOne.start();
        threadTwo.start();

        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        runner.printMoneyStatistics();

    }
}
