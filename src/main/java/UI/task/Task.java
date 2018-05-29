package UI.task;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class Task<T> {
    private ExecutorService executor;
    private CompletableFuture<T> myData;
    private static IntegerProperty runningTasks = new SimpleIntegerProperty();

    public Task(Supplier<T> data) {
        executor = Executors.newFixedThreadPool(1);

        runningTasks.setValue(runningTasks.getValue()+1);
        myData = CompletableFuture.supplyAsync(data, executor);
    }

    public void setOnSucceeded(CallbackWithData<T> callback) {
         myData.thenAcceptAsync(t -> {
             callback.action(t);
             executor.shutdown();
             runningTasks.setValue(runningTasks.getValue()-1);
         });
    }


    public static void onRunningTasksChanged(ChangeListener<Number> changeListener) {
        runningTasks.addListener(changeListener);
    }
}

