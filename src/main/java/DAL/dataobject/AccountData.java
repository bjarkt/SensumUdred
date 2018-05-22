package DAL.dataobject;

import ACQ.IAccount;

public class AccountData implements IAccount {
	private String username;
	private boolean locked;
	private int securityLevel;

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isLocked() {
		return locked;
	}

	@Override
	public int getSecurityLevel() {
		return securityLevel;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public void setSecurityLevel(int securityLevel) {
		this.securityLevel = securityLevel;
	}
}
