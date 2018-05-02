package BLL.case_opening.third_party_information;

import java.util.Date;

public interface IRequest {
	boolean isAccepted();
	IAttachment getAttachment();
	void setAttachment(IAttachment attachment);
	Date sent();
	Date received();
}