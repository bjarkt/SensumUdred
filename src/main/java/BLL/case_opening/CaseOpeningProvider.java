package BLL.case_opening;

import BLL.case_opening.third_party_information.ThirdPartyService;

public class CaseOpeningProvider implements ICaseOpeningService {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ThirdPartyService[] getThirdPartyServices() {
		return ThirdPartyService.values();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void requestThirdPartyCredentials(ThirdPartyService service, int departmentIndex) {
		if(departmentIndex < service.getDepartments().length) {
			// send a request to the department somehow...
			// method unknown at this stage.
			/*
				
			 */
		}
	}
}