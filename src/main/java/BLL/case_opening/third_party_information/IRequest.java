package BLL.case_opening.third_party_information;

import java.io.IOException;
import java.util.Date;

public interface IRequest {
	ThirdPartyService getService();
	int getDepartmentIndex();
	IAttachment getAttachment();
	Date timeOfLastestRequest();
	void now() throws IOException;
}