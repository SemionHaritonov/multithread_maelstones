import java.util.concurrent.*;

public class RunnableVSCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("lambda from anonymous Runnable");
            }
        });

        executorService.submit(() -> {
            System.out.println("lambda from anonymous Callable");
            return 5;
        });

        executorService.shutdown();
    }
}
