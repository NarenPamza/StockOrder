package com.sahaj;

public class Sell extends Order
{
	public Sell()
	{
		super("Sell");
	}

	@Override
	public void setRemainingQuantity(int remainingQuantity)
	{
		this.remainingQuantity = remainingQuantity;
		if (remainingQuantity > 0)
		{
			this.setStatus(Status.OPEN);
		}
		else
		{
			this.setStatus(Status.CLOSED);
		}
	}
}
