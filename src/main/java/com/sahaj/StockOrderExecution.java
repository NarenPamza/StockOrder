package com.sahaj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StockOrderExecution
{
	public static void main(String[] args)
	{
		System.out.println("Welcome 2 Stock Order Execution");

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in)))
		{
			System.out.println("Enter the Input in the following order seperated by ',' and press enter: ");
			System.out.println("Side, Company, Quantity");
			String input = null;

			while ((input = br.readLine()) != null)
			{
				if (input != null && input.length() > 1)
				{
					String[] split = input.split(",");
					Order order;
					if ("Buy".equalsIgnoreCase(split[0].trim()))
					{
						order = new Buy();
					} else
					{
						order = new Sell();
					}
					order.setCompanyName(split[1] != null ? split[1].trim() : null);
					order.setQuantity(Integer.parseInt(split[2] != null ? split[2].trim() : null));
					StockOrderManager.getInstance().createOrder(order.getCompanyName(), order);
					StockOrderManager.getInstance().printOutput();
				}
			}

		} catch (Exception exception)
		{
			exception.printStackTrace();
		}

	}
}
