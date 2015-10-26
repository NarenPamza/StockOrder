package com.sahaj;

public class Sell extends Order
{
	public Sell()
	{
		super("Sell");
	}

	@Override
	protected String getSide()
	{
		return side;
	}
}
