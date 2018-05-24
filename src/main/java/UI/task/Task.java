package UI.task;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class Task<T> {
    private ExecutorService executor;
    private Supplier<T> data;
    private CompletableFuture<T> myData;

    public Task(Supplier data) {
        executor = Executors.newFixedThreadPool(1);

        this.data = data;
        myData = CompletableFuture.supplyAsync(data, executor);
    }

    public void setOnSucceeded(CallbackWithData<T> callback) {
        myData.thenAccept(callback::action);
        executor.shutdown();
    }
}

