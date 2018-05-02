package BLL.case_opening;

import BLL.security_system.AccessLevel;

/***
 * Contains and describes all the services the opening case operations.
 */
public interface ICaseOpeningService {
	/**
	 * Returns all the types of Third Party Services the system provides.
	 * @return all third party services, including their departments
	 */
	@AccessLevel(2)
	ThirdPartyService[] getThirdPartyServices();

	/**
	 * Sends a request to the selected third party services.
	 * Cannot be fully implemented since a test integrated system cannot be accessed.
	 * @param service any {@link ThirdPartyService}
	 * @param departmentIndex index of the department within the selected service (starts at 0(
	 */
	@AccessLevel(2)
	void requestThirdPartyCredentials(ThirdPartyService service, int departmentIndex);
}