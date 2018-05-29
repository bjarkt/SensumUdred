package DAL.dataobject;

import ACQ.IDialog;
import ACQ.IMeeting;
import ACQ.IUser;

import java.util.Collection;
import java.util.Set;

public class Dialog implements IDialog {
	private Set<IMeeting> meetings;

	/**
	 * {@inheritDoc}
	 * Not implemented.
	 */
	@Override
	public IMeeting createMeeting(IUser currentUser) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Not implemented.
	 */
	@Override
	public boolean cancelMeeting(IMeeting meetingToCancel, IUser currentUser) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * Not implemented.
	 */
	@Override
	public Collection<IMeeting> getMeetingBySSN(String ssn) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<IMeeting> getMeetings() {
		return meetings;
	}

	/**
	 * Set the meetings
	 * @param meetings any set with meetings
	 */
	public void setMeetings(Set<IMeeting> meetings) {
		this.meetings = meetings;
	}
}