package com.sahaj;

public class Buy extends Order
{
	public Buy()
	{
		super("Buy");
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
