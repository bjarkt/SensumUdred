package UI.task;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class Task<T> {
    private ExecutorService executor;
    private CompletableFuture<T> myData;
    private CallbackWithoutData runningCallback;

    public Task(Supplier<T> data, CallbackWithoutData runningCallback) {
        executor = Executors.newFixedThreadPool(1);

        runningCallback.action();
        myData = CompletableFuture.supplyAsync(data, executor);
    }

    public void setOnSucceeded(CallbackWithData<T> callback) {
        myData.thenAccept(callback::action);
        executor.shutdown();
    }
}

