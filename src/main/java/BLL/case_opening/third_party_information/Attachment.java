package BLL.case_opening.third_party_information;

import ACQ.AttachmentEnum;
import ACQ.IAttachment;

public class Attachment implements IAttachment {
	private long number;
	private AttachmentEnum type;
	private byte[] data;

	public Attachment(AttachmentEnum type, byte[] data, long number) {
		this.type = type;
		this.data = data;
		this.number = number;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AttachmentEnum getType() {
		return type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public byte[] getData() {
		return data;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getNumber() {
		return number;
	}
}
