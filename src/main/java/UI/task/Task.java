package UI.task;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

/**
 * Task is a wrapper class around {@link CompletableFuture}, which adds a {@link Task#setOnSucceeded(CallbackWithData)}
 * callback method, that calls when the data is available.
 * Also has a {@link Task#onRunningTasksChanged(ChangeListener)}, which is listens on a integer property, of how many
 * current Tasks are running, which is handy for displaying loading animations.
 * @param <T> type of data to return.
 */
public class Task<T> {
    private ExecutorService executor;
    private CompletableFuture<T> myData;
    private static IntegerProperty runningTasks = new SimpleIntegerProperty();

    /**
     * Create a new Task, with a {@link Supplier} method, that returns an object of type T
     * @param data method to get data from somewhere
     */
    public Task(Supplier<T> data) {
        executor = Executors.newFixedThreadPool(1);

        runningTasks.setValue(runningTasks.getValue()+1);
        myData = CompletableFuture.supplyAsync(data, executor);
    }

    /**
     * What shall happen when the data is available
     * @param callback callback method, that handles the data
     */
    public void setOnSucceeded(CallbackWithData<T> callback) {
         myData.thenAcceptAsync(t -> {
             callback.action(t);
             executor.shutdown();
             runningTasks.setValue(runningTasks.getValue()-1);
         });
    }

    /**
     * ChangeListener that listens on the amount of running tasks
     * @param changeListener running tasks change listener
     */
    public static void onRunningTasksChanged(ChangeListener<Number> changeListener) {
        runningTasks.addListener(changeListener);
    }
}

