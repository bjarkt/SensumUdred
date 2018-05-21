package BLL.case_opening;

import ACQ.IHttp;
import BLL.case_opening.third_party_information.*;

import java.io.*;

public class CaseOpeningProvider implements ICaseOpeningService {
	private IHttp httpClient;

	/**
	 *
	 * {@inheritDoc}
	 */
	@Override
	public void setHttpClient(IHttp httpClient) {
		this.httpClient = httpClient;
	}

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
			IRequest request = new Request(service, departmentIndex, httpClient);

			request.now();

			IAttachment attachment = request.getAttachment();

			if(attachment.getType() == AttachmentEnum.TEXT) {
				String result = new String(attachment.getData()).replaceAll("\\|", System.lineSeparator());
			} else {
				// PDF something..
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}