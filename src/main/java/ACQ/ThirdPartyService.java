package ACQ;

/**
 * Contains all the different services the system have access to.
 */
public enum ThirdPartyService {
	TEST(-1, new String[] { "Fantasy Island", "Azeroth", "Cow Level" }),
	HOSPITAL(5, new String[] { "Pharmacy", "Physchiatric", "Therapy" }),
	GOVERNMENT(37, new String[] { "Department 1", "Department 2" });

	private int id;
	private String[] departments;

	ThirdPartyService(int id, String[] departments) {
		this.id = id;
		this.departments = departments;
	}

	/**
	 * Returns the id of the service.
	 * NOTE: These ideas do not necessary be in order.
	 * @return id of the service
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns all the department the service has underneath it.
	 * @return a string array containing all the departments
	 */
	public String[] getDepartments() {
		return departments;
	}
}
