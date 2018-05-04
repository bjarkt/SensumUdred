package BLL.case_opening.third_party_information;

import BLL.case_opening.IHttp;

import java.io.IOException;
import java.util.Date;
/**
 * Describes what the behaviour of a Request should contain.
 */
public interface IRequest {
	/**
	 * Returs the service the request was for.
	 * @return a service limited by the {@link ThirdPartyService}.
	 */
	ThirdPartyService getService();

	/**
	 * Returns an integer, depending on which department within the service
	 * the request is targeted at.
	 * @return an integer between 0 and the length of the department list, minus one.
	 */
	int getDepartmentIndex();

	/**
	 * Returns the request' attachment.
	 * @return null, if an error occurred when requesting, otherwise the attachment
	 */
	IAttachment getAttachment();

	/**
	 * Returns a date when the request was made.
	 * @return null, if an error occurred when requesting, otherwise the date
	 */
	Date timeOfLastestRequest();

	/**
	 * Sends the request to the selected service and department.
	 * If exception did not throw, the {@link IRequest#getAttachment()}
	 * and {@link IRequest#timeOfLastestRequest()} should be available for use.
	 * @throws IOException the request was not successful
	 */
	void now() throws IOException;
}