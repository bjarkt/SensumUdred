package BLL.case_opening;

import BLL.access_system.AccessLevel;

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
	@AccessLevel(2)
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