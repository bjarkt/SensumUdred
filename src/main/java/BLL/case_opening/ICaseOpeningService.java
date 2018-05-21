package BLL.case_opening;

import ACQ.IHttp;
import BLL.case_opening.third_party_information.ThirdPartyService;

/***
 * Contains and describes all the services the opening case operations.
 */
public interface ICaseOpeningService {
	void setHttpClient(IHttp required);
	/**
	 * Returns all the types of Third Party Services the system provides.
	 * @return all third party services, including their departments
	 */
	ThirdPartyService[] getThirdPartyServices();

	/**
	 * Sends a request to the selected third party services.
	 * Cannot be fully implemented since a test integrated system cannot be accessed.
	 * @param service any {@link ThirdPartyService}
	 * @param departmentIndex index of the department within the selected service (starts at 0(
	 */
	void requestThirdPartyCredentials(ThirdPartyService service, int departmentIndex);
}