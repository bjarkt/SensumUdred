package BLL.ACQ;

public abstract class Task {

    ElucidationState state;

    public Task(){
        state = ElucidationState.INQUIRY;
    }

    public ElucidationState getState() {
        return state;
    }

    protected void setState(ElucidationState state){
        this.state = state;
    }

}
