package DAL.dataobject;

import ACQ.ITheme;
import ACQ.ThemeEnum;

public class Theme implements ITheme {
	private ThemeEnum themeEnum;
	private String documentation;
	private String subtheme;
	private int levelOfFunction;

	public Theme(ThemeEnum themeEnum, String documentation, String subtheme, int levelOfFunction) {
		this.themeEnum = themeEnum;
		this.documentation = documentation;
		this.subtheme = subtheme;
		this.levelOfFunction = levelOfFunction;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ThemeEnum getTheme() {
		return themeEnum;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDocumentation() {
		return documentation;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSubtheme() {
		return subtheme;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getLevelOfFunction() {
		return levelOfFunction;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLevelOfFunction(int levelOfFunction) throws IllegalArgumentException {
		this.levelOfFunction = levelOfFunction;
	}

	/**
	 * {@inheritDoc}
	 * Not implemented.
	 */
	@Override
	public int compareTo(ITheme theme) {
		return 0;
	}
}