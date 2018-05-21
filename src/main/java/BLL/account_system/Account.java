package BLL.account_system;

import ACQ.IAccount;

public class Account implements IAccount {
	private String username;
	private boolean locked;
	private int securityLevel;

	public Account(String username, int securityLevel) {
		this.username = username;
		this.securityLevel = securityLevel;
	}

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
	 * Set the lock state of the account.
	 * @param locked true or false
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
}