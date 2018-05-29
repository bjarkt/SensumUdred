package BLL;

import ACQ.*;

import java.util.*;

public class Elucidation implements IElucidation {
    private long id;
    private IUser citizen;
    private Set<IUser> caseworkers;
    private ITask task;
    private Date dateOfOpening;
    private IDialog dialog;


    public Elucidation(long id, IUser citizen, IUser creator, IDialog dialog, ITask task){
        this.id = id;
        dateOfOpening = new Date();
        this.citizen = citizen;
        caseworkers = new HashSet<>();
        caseworkers.add(creator);
        this.task = task;
        this.dialog = dialog;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUser getCitizen() {
        return citizen;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCaseworker(IUser... caseworker) {
        caseworkers.addAll(Arrays.asList(caseworker));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<IUser> getCaseworkers() {
        return caseworkers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ITask getTask() {
        return task;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Date getCreationDate() {
        return dateOfOpening;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDialog getDialog() {
        return dialog;
    }

    public void setDateOfOpening(Date dateOfOpening) {
        this.dateOfOpening = dateOfOpening;
    }

}
