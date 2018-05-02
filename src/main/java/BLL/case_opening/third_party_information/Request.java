package BLL.case_opening.third_party_information;

import java.util.Date;

public class Request implements IRequest {
	private boolean accepted;
	private IAttachment attachment;
	private Date sent;
	private Date received;

	public Request() {
		this.accepted = false;
		this.attachment = null;
		this.sent = new Date();
	}

	@Override
	public boolean isAccepted() {
		return false;
	}

	@Override
	public IAttachment getAttachment() {
		return null;
	}

	@Override
	public void setAttachment(IAttachment attachment) {
		this.accepted = true;
		this.received = new Date();
		this.attachment = attachment;
	}

	@Override
	public Date sent() {
		return null;
	}

	@Override
	public Date received() {
		return null;
	}
}
