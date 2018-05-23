package ACQ;

public abstract class Task implements ITask{

    ElucidationState state;

    public Task(){
        state = ElucidationState.INQUIRY;
    }

    @Override
    public ElucidationState getState() {
        return state;
    }

    protected void setState(ElucidationState state){
        this.state = state;
    }

}
