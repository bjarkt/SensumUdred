package BLL.case_opening.third_party_information;

import BLL.ACQ.HttpAcceptType;
import BLL.ACQ.HttpMethod;
import BLL.case_opening.IHttp;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A Request implements the {@link IRequest} interface to provide the implementation.
 * It is used to make a request to a server for third-party information.
 */
public class Request implements IRequest {
	private ThirdPartyService service;
	private int departmentIndex;
	private IAttachment attachment;
	private Date timeOfLastestRequest;


	private IHttp httpClient;

	/**
	 * Constructs a Request with a ThirdPartyService and a department index.
	 * It invokes the {@link Request#now()}, however, it can done manually,
	 * if a refresh is required.
	 * @param service
	 * @param departmentIndex
	 * @throws IOException
	 */
	public Request(ThirdPartyService service, int departmentIndex, IHttp httpClient) throws IOException {
		this.service = service;
		this.departmentIndex = departmentIndex;
		this.httpClient = httpClient;

		now();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ThirdPartyService getService() {
		return service;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getDepartmentIndex() {
		return departmentIndex;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IAttachment getAttachment() {
		return attachment;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date timeOfLastestRequest() {
		return timeOfLastestRequest;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void now() throws IOException {
		if(departmentIndex < service.getDepartments().length) {
			Map<String, Object> queryMap = new HashMap<>();
			queryMap.put("cpr", (long) (2000000000 + Math.random() * 8000000000L));
			queryMap.put("service", service.getId());
			queryMap.put("department", departmentIndex);


			String[] data = receiveCredentialsFormat("https://sensumudred-api.herokuapp.com/api/case-request", queryMap);

			byte[] attachmentBytes;

			if(data[0].equals("PDF")) {
				attachmentBytes = getPDFFromUrl(data[1]);
			} else {
				attachmentBytes = data[1].getBytes();
			}

			if(attachmentBytes != null) {
				attachment = new Attachment(AttachmentEnum.valueOf(data[0].toUpperCase()), attachmentBytes);
				this.timeOfLastestRequest = new Date();
			}
		}
	}

	/**
	 * Receives what form the credentials are and the value itself.
	 * @param query a query based on the server specifications
	 * @return a string array with a length of two, where the first is the type (TEXT or PDF) and second is value
	 * @throws IOException if the requested protocol is wrong or the buffer cannot be read to, this exception occurs
	 */
	private String[] receiveCredentialsFormat(String urlString, Map<String, Object> query) throws IOException {
		String dataResponse = new String(httpClient.makeHttpRequest(urlString, query, HttpMethod.POST, HttpAcceptType.TEXT));

		String[] lineParts = dataResponse.split("\n");
		String type = lineParts[0].split("\t")[1];
		String value = lineParts[1].split("\t")[1];

		return new String[] {type, value};
	}

	/**
	 * Downloads a PDF from a specific url (http://homepage.com/test.pdf)
	 * and returns the file as a byte array.
	 * @param urlString any specific url
	 * @return null, if url not correct
	 * @throws IOException when data the read, but error occurs by writing to the byte array.
	 */
	private byte[] getPDFFromUrl(String urlString) throws IOException {
		byte[] dataResponse = httpClient.makeHttpRequest(urlString, null, HttpMethod.GET, HttpAcceptType.PDF);
		return dataResponse;
	}
}
