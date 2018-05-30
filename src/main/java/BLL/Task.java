package BLL;

import ACQ.ElucidationTaskState;
import ACQ.ITask;

public abstract class Task implements ITask {

    private ElucidationTaskState state;

    public Task(){
        state = ElucidationTaskState.INQUIRY;
    }

    @Override
    public ElucidationTaskState getState() {
        return state;
    }

    protected void setState(ElucidationTaskState state){
        this.state = state;
    }
}