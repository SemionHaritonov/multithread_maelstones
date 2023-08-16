public class MyShittyDeadlock {

    class One {
        public synchronized void doWorkOne(Two two) throws InterruptedException {
            System.out.println("doWorkOne works");
            two.doWorkTwo(this);
            Thread.sleep(1000);
            System.out.println("doWorkOne works after waiting");
        }
    }

    class Two {
        public synchronized void doWorkTwo(One one) throws InterruptedException {
            System.out.println("doWorkTwo works");
            one.doWorkOne(this);
            Thread.sleep(1000);
            System.out.println("doWorkTwo works after waiting");
        }
    }

    public static void main(String[] args) {
        MyShittyDeadlock myShittyDeadlock = new MyShittyDeadlock();
        One one = myShittyDeadlock.new One();
        Two two = myShittyDeadlock.new Two();

        Thread threadOne = new Thread(() -> {
            try {
                one.doWorkOne(two);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread threadTwo = new Thread(() -> {
            try {
                two.doWorkTwo(one);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
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
    }
}
