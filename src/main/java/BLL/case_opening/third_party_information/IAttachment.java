package BLL.case_opening.third_party_information;

public interface IAttachment {
	AttachmentEnum getType();
	byte[] getData();
}