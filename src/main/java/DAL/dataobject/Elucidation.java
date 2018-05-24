package DAL.dataobject;

import ACQ.IElucidation;
import ACQ.IUser;
import ACQ.IDialog;
import BLL.open_case.Guardianship;
import javafx.util.Pair;

import java.util.*;

public class Elucidation implements IElucidation {
	private IUser citizen;
	private Set<IUser> caseworkers;
	private Date creationTime;
	private IDialog dialog;

	public Elucidation(IUser citizen, Set<IUser> caseworkers, Date creationTime, IDialog dialog) {
		this.citizen = citizen;
		this.caseworkers = caseworkers;
		this.creationTime = creationTime;
		this.dialog = dialog;
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
	 * Not implemented.
	 */
	@Override
	public void addCaseworker(IUser... caseworkers) {

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
	 * Not implemented.
	 */
	@Override
	public void openCase(String description, List<Pair<String, String>> offerings, List<String> grantings, IUser guardian, Guardianship guardianship, String informationAboutAuthority, boolean citizenAgreesWithOpeningOfCase, String citizensMunicipality, String specialCircumstances) {

	}

	@Override
	public Date getCreationDate() {
		return creationTime;
	}

	@Override
	public IDialog getDialog() {
		return dialog;
	}

	public void setCitizen(IUser citizen) {
		this.citizen = citizen;
	}

	public void setCaseworkers(Set<IUser> caseworkers) {
		this.caseworkers = caseworkers;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public void setDialog(IDialog dialog) {
		this.dialog = dialog;
	}
}