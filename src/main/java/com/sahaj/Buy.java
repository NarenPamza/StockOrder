package com.sahaj;

public class Buy extends Order
{

	public Buy()
	{
		super("Buy");
	}

	@Override
	protected String getSide()
	{
		return side;
	}
}
