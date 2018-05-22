package DAL.dataobject;

import ACQ.IAccount;

public class AccountData implements IAccount {
	private String username;
	private boolean locked;
	private int securityLevel;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getUsername() {
		return username;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isLocked() {
		return locked;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSecurityLevel() {
		return securityLevel;
	}

	/**
	 * Set the username.
	 * @param username any username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Set the locked boolean.
	 * @param locked true or false
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * Set the securitylevel.
	 * @param securityLevel any integer
	 */
	public void setSecurityLevel(int securityLevel) {
		this.securityLevel = securityLevel;
	}
}
