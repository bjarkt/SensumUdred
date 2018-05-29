package DAL.dataobject;

import ACQ.IEBoks;
import ACQ.IMeeting;
import ACQ.IUser;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

public class Meeting implements IMeeting {
	private IUser creator;
	private Set<IUser> participants;
	private String information;
	private GregorianCalendar meetingDate;
	private IEBoks eBoks;
	private boolean isCancelled;
	private int id;

	/**
	 * {@inheritDoc}
	 * Not implemented.
	 * @return always false
	 */
	@Override
	public boolean sendMeetingMessage() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * Not implemented!
	 * @return always false
	 */
	@Override
	public boolean cancelMeeting(IUser currentUser) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * Not implemented.
	 */
	@Override
	public void addParticipant(IUser... participant) {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<IUser> getParticipants() {
		return participants;
	}

	/**
	 * {@inheritDoc}
	 * Not implemented!
	 * @return always false
	 */
	@Override
	public boolean isUserParticipating(IUser participant) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * Not implemented!
	 */
	@Override
	public void removeParticipant(IUser participant) {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getInformation() {
		return information;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setInformation(String information) {
		this.information = information;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getMeetingDate() {
		return new Date(meetingDate.getTimeInMillis());
	}

	/**
	 * {@inheritDoc}
	 * Not implemented.
	 */
	@Override
	public void setMeetingDate(int year, int month, int day, int hour, int minute) {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNumber() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 * Not implemented.
	 */
	@Override
	public void setNumber(int n) {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUser getCreator() {
		return creator;
	}

	/**
	 * Set the creator.
	 * @param creator creator of the dialog
	 */
	public void setCreator(IUser creator) {
		this.creator = creator;
	}

	/**
	 * Set the participants
	 * @param participants participants of the meeting
	 */
	public void setParticipants(Set<IUser> participants) {
		this.participants = participants;
	}

	/**
	 * Set the meeting date.
	 * @param timestamp any timestamp
	 */
	public void setMeetingDate(Timestamp timestamp) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(timestamp);

		GregorianCalendar gcal = new GregorianCalendar();
		gcal.setTime(cal.getTime());

		this.meetingDate = gcal;
	}

	/**
	 * Set cancel boolean for the meeting.
	 * @param cancelled true or false
	 */
	public void setCancelled(boolean cancelled) {
		isCancelled = cancelled;
	}

	/**
	 * Set the id (number) of the meeting.
	 * Has to be unique.
	 * @param id an unique number starting from 1
	 */
	public void setId(int id) {
		this.id = id;
	}
}