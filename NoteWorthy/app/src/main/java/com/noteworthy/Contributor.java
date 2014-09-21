package com.noteworthy;

public class Contributor {
	int pictureResource;
	String name;
	String contributionClass;
	int contributionAmt;
	public Contributor(int pictureResource, String name, String contributionClass, int contributionAmt)
	{
		this.pictureResource = pictureResource;
		this.name = name;
		this.contributionClass = contributionClass;
		this.contributionAmt = contributionAmt;
	}
}
