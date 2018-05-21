package BLL.account_system;

import ACQ.IAccount;

public class Account implements IAccount {
	private String username;
	private int securityLevel;

	public Account(String username, int securityLevel) {
		this.username = username;
		this.securityLevel = securityLevel;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public int getSecurityLevel() {
		return securityLevel;
	}

	public void setUsername(String newUsername) {
		this.username = newUsername;
	}
}