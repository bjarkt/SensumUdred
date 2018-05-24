package UI.task;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Task<T> {
    private ExecutorService executor;
    private CompletableFuture<T> myData;

    public Task(Supplier<T> data) {
        executor = Executors.newFixedThreadPool(1);

        myData = CompletableFuture.supplyAsync(data, executor);
    }

    public void setOnSucceeded(CallbackWithData<T> callback) {
         myData.thenAcceptAsync(new Consumer<T>() {
            @Override
            public void accept(T t) {
                callback.action(t);
                executor.shutdown();
            }
        });
    }
}

