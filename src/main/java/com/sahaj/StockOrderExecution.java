package com.sahaj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.sahaj.Utils.Utility;

public class StockOrderExecution
{
	public static void main(String[] args)
	{
		System.out.println("Welcome to Stock Order Execution");

		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(System.in)))
		{
			System.out
					.println("Enter the Input in the following order seperated by ',' and press enter: ");
			System.out.println("Side, Company, Quantity");
			String input = null;

			while ((input = bufferedReader.readLine()) != null)
			{
				if (input != null && input.length() > 1)
				{
					String[] split = input.split(",");

					if (split == null || split.length != 3)
					{
						System.out.println("Input Values are Invalid");
						System.out.println("Usage: Side,Company,Quantity");
						System.out.println("Example: Buy,ABC,10");
						throw new Error("Input Values are invalid");
					}

					String side = split[0];
					String company = split[1];

					if (Utility.isStringNullOrEmpty(side)
							|| Utility.isStringNullOrEmpty(company))
					{
						throw new Error(
								"Input Value of Side or Company is Null or Empty");
					}

					if (Utility.isStringNullOrEmpty(split[2]))
					{
						throw new Error(
								"Input Value of Quantity is Null or Empty");
					}

					int quantity = Integer.parseInt(split[2].trim());

					if (quantity < 0)
					{
						throw new Error(
								"Input Value of Quantity is Less than O");
					}

					/*
					 * Create the Order Instance
					 */
					StockOrderManager.getInstance().createOrder(side.trim(),
							company.trim(), quantity);
				}
				else
				{
					/**
					 * Print the Order Output
					 */
					StockOrderManager.getInstance().printOrderOutput();
				}
			}

		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}

	}
}
