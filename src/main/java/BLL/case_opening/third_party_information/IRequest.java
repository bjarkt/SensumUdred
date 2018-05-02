package BLL.case_opening.third_party_information;

import java.util.Date;

public interface IRequest {
	boolean isAccepted();
	IAttachment getAttachment();
	Date sent();
	Date received();
}