package BLL.case_opening.third_party_information;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class Request implements IRequest {
	private ThirdPartyService service;
	private int departmentIndex;
	private IAttachment attachment;
	private Date timeOfLastestRequest;

	public Request(ThirdPartyService service, int departmentIndex) {
		this.service = service;
		this.departmentIndex = departmentIndex;
	}

	@Override
	public ThirdPartyService getService() {
		return service;
	}

	@Override
	public int getDepartmentIndex() {
		return departmentIndex;
	}

	@Override
	public IAttachment getAttachment() {
		return attachment;
	}

	@Override
	public Date timeOfLastestRequest() {
		return timeOfLastestRequest;
	}

	@Override
	public void now() throws IOException {
		if(departmentIndex < service.getDepartments().length) {
			String query = "cpr=" + (long) (2000000000 + Math.random() * 8000000000L) +
					"&service=" + service.ordinal() +
					"&department=" + departmentIndex;

			String[] data = receiveCredentialsFormat("https://sensumudred-api.herokuapp.com/api/case-request", query);

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
	private String[] receiveCredentialsFormat(String urlString, String query) throws IOException {
		String[] data = null;

		URL url = new URL(urlString);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("POST");

		conn.setRequestProperty("Accept", "application/text");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("api-key", "1234");

		conn.setDoOutput(true);

		OutputStream os = conn.getOutputStream();
		os.write(query.getBytes());
		os.flush();

		if(conn.getResponseCode() != 200) {
			System.out.println("Error in Response Code...");
		} else {
			System.out.println("Request successful!");

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line;
			String type = null;
			String value = null;

			while((line = reader.readLine()) != null) {
				if(type == null) {
					type = line.split("\t")[1];
				} else if(value == null) {
					value = line.split("\t")[1];
				}
			}

			conn.disconnect();

			data = new String[] { type, value };
		}

		return data;
	}

	private byte[] getPDFFromUrl(String urlString) throws IOException {
		URL pdfUrl = new URL(urlString);

		HttpURLConnection conn = (HttpURLConnection) pdfUrl.openConnection();

		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/pdf");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("api-key", "1234");

		byte[] pdfData = null;

		if(conn.getResponseCode() != 200) {
			System.out.println("Error in Response Code...");
		} else {
			System.out.println("PDF from Url successful!");

			byte[] fileData = new byte[1024];

			ByteArrayOutputStream buffer = new ByteArrayOutputStream(conn.getContentLength());

			int length;
			while((length = conn.getInputStream().read(fileData)) > -1) {
				buffer.write(fileData, 0, length);
			}

			conn.disconnect();

			pdfData = buffer.toByteArray();
		}

		return pdfData;
	}
}
