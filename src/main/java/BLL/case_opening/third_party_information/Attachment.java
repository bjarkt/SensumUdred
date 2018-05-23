package BLL.case_opening.third_party_information;

public class Attachment implements IAttachment {
	private long number;
	private AttachmentEnum type;
	private byte[] data;

	public Attachment(AttachmentEnum type, byte[] data, long number) {
		this.type = type;
		this.data = data;
		this.number = number;
	}

	@Override
	public AttachmentEnum getType() {
		return type;
	}

	@Override
	public byte[] getData() {
		return data;
	}

	@Override
	public long getNumber() {
		return number;
	}
}
