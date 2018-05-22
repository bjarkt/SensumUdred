package DAL.dataobject;

import ACQ.IAccount;
import ACQ.IProfile;
import ACQ.IUser;

public class ProfileData implements IProfile {
	private IUser user;
	private IAccount account;

	public ProfileData() {
		this.user = null;
		this.account = null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUser getUser() {
		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IAccount getAccount() {
		return account;
	}

	/**
	 * Set the user.
	 * @param user any user
	 */
	public void setUser(IUser user) {
		this.user = user;
	}

	/**
	 * Set the account.
	 * @param account any account
	 */
	public void setAccount(IAccount account) {
		this.account = account;
	}
}