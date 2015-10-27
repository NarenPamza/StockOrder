package com.sahaj;

public abstract class Order
{
	protected String side;

	private String companyName;

	private int quantity;

	private int remainingQuantity;

	private Status status;

	public Status getStatus()
	{
		return status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}

	public Order(String side)
	{
		this.side = side;
	}

	public String getCompanyName()
	{
		return companyName;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public int getRemainingQuantity()
	{
		return remainingQuantity;
	}

	public void setRemainingQuantity(int remainingQuantity)
	{
		this.remainingQuantity = remainingQuantity;
		if(remainingQuantity > 0)
		{
			this.setStatus(Status.OPEN);
		}
		else
		{
			this.setStatus(Status.CLOSED);
		}
	}

	public static enum Status
	{
		OPEN("Open"), CLOSED("Closed");

		private String statusCode;

		private Status(String s)
		{
			statusCode = s;
		}

		public String getStatusCode()
		{
			return statusCode;
		}
	}

	@Override
	public String toString()
	{
		StringBuilder aBuilder = new StringBuilder();
		aBuilder.append("Side: " + side).append(", CompanyName: " + companyName).append(", Quantity: " + quantity)
				.append(", RemainingQty: " + remainingQuantity).append(", Status: " + status.getStatusCode());
		return aBuilder.toString();
	}

	protected abstract String getSide();
}
