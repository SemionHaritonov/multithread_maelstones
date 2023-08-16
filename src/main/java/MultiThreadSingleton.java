public class MultiThreadSingleton {

    private static volatile MultiThreadSingleton instance;

    public static MultiThreadSingleton getInstance() {
        MultiThreadSingleton current = instance;
        if (current == null) {
            synchronized (MultiThreadSingleton.class) {
                current = instance;
                if (current == null) {
                    instance = current = new MultiThreadSingleton();
                }
            }
        }
        return current;
    }

}
