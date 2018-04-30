package BLL.case_opening;

public enum ThirdPartyService {
	HOSPITAL(new String[] { "Pharmacy", "Physchiatric", "Therapy" }),
	GOVERNMENT(new String[] { "Department 1", "Department 2" });

	private String[] departments;

	ThirdPartyService(String[] departments) {
		this.departments = departments;
	}

	public String[] getDepartments() {
		return departments;
	}
}
