import ACQ.HttpAcceptType;
import ACQ.HttpMethod;
import ACQ.IHttp;
import BLL.case_opening.CaseOpeningProvider;
import BLL.case_opening.ICaseOpeningService;
import BLL.case_opening.third_party_information.AttachmentEnum;
import BLL.case_opening.third_party_information.IRequest;
import BLL.case_opening.third_party_information.Request;
import BLL.case_opening.third_party_information.ThirdPartyService;
import DAL.PersistentFacade;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

public class ThirdPartyInformationTest {

	IHttp httpClient = TestHelper.getHttpClient();

	@Test
	public void CaseOpeningTest() {
		ICaseOpeningService caseOpeningService = new CaseOpeningProvider();
		caseOpeningService.setHttpClient(httpClient);

		caseOpeningService.requestThirdPartyCredentials(ThirdPartyService.TEST, 0);
		caseOpeningService.requestThirdPartyCredentials(ThirdPartyService.TEST, 1);
	}

	@Test
	public void requestPdfTest() {
		try {
			IRequest request = new Request(ThirdPartyService.TEST, 0, httpClient);

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
			IRequest request = new Request(ThirdPartyService.TEST, 1, httpClient);

			boolean isPDF = request.getAttachment().getType() == AttachmentEnum.TEXT;
			boolean isTestService = request.getService() == ThirdPartyService.TEST;
			boolean isAttachmentValid = request.getAttachment() != null;

			assert isPDF && isTestService && isAttachmentValid;
		} catch(IOException e) {
			System.out.println("ERROR occurred when testing 'requestTextTest() in ThirdPartyInformationTest'.");
		}
	}
}
