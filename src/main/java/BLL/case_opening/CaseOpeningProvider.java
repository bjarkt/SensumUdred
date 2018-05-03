package BLL.case_opening;

import BLL.case_opening.third_party_information.*;

import java.io.*;

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
		try {
			IRequest request = new Request(service, departmentIndex);

			request.now();

			IAttachment attachment = request.getAttachment();

			if(attachment.getType() == AttachmentEnum.TEXT) {
				System.out.println(new String(attachment.getData()).replaceAll("\\|", System.lineSeparator()));
			} else {
				// PDF something..
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}