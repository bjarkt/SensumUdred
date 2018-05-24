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

	public Elucidation() {
		caseworkers = new HashSet<>();
	}

	@Override
	public IUser getCitizen() {
		return citizen;
	}

	@Override
	public void addCaseworker(IUser... caseworkers) {
		this.caseworkers.addAll(Arrays.asList(caseworkers));
	}

	@Override
	public Set<IUser> getCaseworkers() {
		return caseworkers;
	}

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