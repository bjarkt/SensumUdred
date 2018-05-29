package ACQ;

public abstract class Task implements ITask{

    ElucidationTaskState state;

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