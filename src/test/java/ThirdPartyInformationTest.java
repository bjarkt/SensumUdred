import ACQ.AttachmentEnum;
import ACQ.IHttp;
import ACQ.IRequest;
import ACQ.ThirdPartyService;
import BLL.case_opening.CaseOpeningProvider;
import BLL.case_opening.ICaseOpeningService;
import BLL.case_opening.third_party_information.Request;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ThirdPartyInformationTest {

	IHttp httpClient = TestHelper.getHttpClient();

	long[] attachnums = {1,2};

	@Test
	public void CaseOpeningTest() {
		ICaseOpeningService caseOpeningService = new CaseOpeningProvider();
		caseOpeningService.setHttpClient(httpClient);

		caseOpeningService.requestThirdPartyCredentials(ThirdPartyService.TEST, 0, attachnums[0]);
		caseOpeningService.requestThirdPartyCredentials(ThirdPartyService.TEST, 1, attachnums[1]);
	}

	@Test
	public void requestPdfTest() {
		try {
			IRequest request = new Request(ThirdPartyService.TEST, 0, httpClient, attachnums[0]);

			boolean isPDF = request.getAttachment().getType() == AttachmentEnum.PDF;
			boolean isTestService = request.getService() == ThirdPartyService.TEST;
			boolean isAttachmentValid = request.getAttachment() != null;

			assert isPDF && isTestService && isAttachmentValid;
		} catch(IOException e) {
			System.out.println("ERROR occurred when testing 'requestPdfTest() in ThirdPartyInformationTest'.");
		}
	}

	@Test
	public void requestTextTest() {
		try {
			IRequest request = new Request(ThirdPartyService.TEST, 1, httpClient, attachnums[1]);

			boolean isPDF = request.getAttachment().getType() == AttachmentEnum.TEXT;
			boolean isTestService = request.getService() == ThirdPartyService.TEST;
			boolean isAttachmentValid = request.getAttachment() != null;

			assert isPDF && isTestService && isAttachmentValid;
		} catch(IOException e) {
			System.out.println("ERROR occurred when testing 'requestTextTest() in ThirdPartyInformationTest'.");
		}
	}
}
