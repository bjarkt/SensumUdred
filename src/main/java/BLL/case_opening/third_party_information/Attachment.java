package BLL.case_opening.third_party_information;

public class Attachment implements IAttachment {
	private AttachmentEnum type;
	private byte[] data;

	public Attachment(AttachmentEnum type, byte[] data) {
		this.type = type;
		this.data = data;
	}

	@Override
	public AttachmentEnum getType() {
		return type;
	}

	@Override
	public byte[] getData() {
		return data;
	}
}
