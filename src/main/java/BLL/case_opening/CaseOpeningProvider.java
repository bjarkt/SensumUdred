package BLL.case_opening;

import BLL.case_opening.third_party_information.ThirdPartyService;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

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
		String query = "cpr=" + (long) (2000000000 + Math.random() * 8000000000L) +
				"&service=" + 1 +
				"&department=" + departmentIndex;

		try {
			String[] data = receiveCredentialsFormat(query);

			byte[] attachmentBytes;

			if(data[0].equals("PDF")) {
				attachmentBytes = getPDFFromUrl(data[1]);
			} else {
				attachmentBytes = data[1].getBytes();
			}

			if(departmentIndex < service.getDepartments().length) {

			}
		} catch(IOException e) {
			e.printStackTrace();
		}


	}

	/**
	 * Receives what form the credentials are and the value itself.
	 * @param query a query based on the server specifications
	 * @return a string array with a length of two. First is type and second is value
	 * @throws IOException if the requested protocol is wrong or the buffer cannot be read to, this exception occurs
	 */
	private String[] receiveCredentialsFormat(String query) throws IOException {
		String[] data = null;

		URL url = new URL("https://sensumudred-api.herokuapp.com/api/case-request");

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("POST");

		conn.setDoInput(true);
		conn.setDoOutput(true);

		conn.setRequestProperty("Accept", "application/text");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		OutputStream os = conn.getOutputStream();
		os.write(query.getBytes());
		os.flush();

		if(conn.getResponseCode() != 200) {
			System.out.println("FEJL!");
		} else {
			System.out.println("Request sent.");

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

	private byte[] getPDFFromUrl(String url) throws IOException {
		URL pdfUrl = new URL(url);

		HttpURLConnection conn = (HttpURLConnection) pdfUrl.openConnection();

		conn.setRequestMethod("GET");

		conn.setRequestProperty("Accept", "application/pdf");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		byte[] fileData = new byte[1024];

		ByteArrayOutputStream buffer = new ByteArrayOutputStream(Integer.parseInt(conn.getHeaderField("Content-Length")));

		int length;
		while((length = conn.getInputStream().read(fileData)) > -1) {
			buffer.write(fileData, 0, length);
		}

		conn.disconnect();

		return buffer.toByteArray();
	}
}