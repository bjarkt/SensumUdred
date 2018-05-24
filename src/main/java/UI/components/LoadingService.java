package UI.components;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class LoadingService<T> extends Service<T>{

    @Override
    protected Task<T> createTask() {
        return new Task<T>(){
            @Override
            protected T call() throws Exception{
                T res = null;
                Thread.sleep(5000);
                return res;
            }
        };
    }
}
