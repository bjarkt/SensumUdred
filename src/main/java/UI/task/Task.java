package UI.task;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Task<T> {
    private ExecutorService executor;
    private CompletableFuture<T> myData;
    private BooleanProperty finished;

    public Task(Supplier<T> data) {
        executor = Executors.newFixedThreadPool(1);

        finished = new SimpleBooleanProperty(false);
        myData = CompletableFuture.supplyAsync(data, executor);
    }

    public void setOnSucceeded(CallbackWithData<T> callback) {
         myData.thenAcceptAsync(new Consumer<T>() {
            @Override
            public void accept(T t) {
                callback.action(t);
                finished.setValue(true);
                executor.shutdown();
            }
        });
    }

    public void setFinishedListener(ChangeListener<Boolean> changeListener) {
        finished.addListener(changeListener);
    }
}

