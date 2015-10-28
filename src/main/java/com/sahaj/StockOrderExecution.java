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
				if (!input.isEmpty())
				{
					String[] inputs = input.split(",");

					if (!isValidInputLength(inputs))
					{
						new StockOrderException(
								"Invalid Input: Input Values are not equal to 3");
					}

					String side = inputs[0].trim();
					String company = inputs[1].trim();
					int quantity = 0;
					try
					{
						quantity = Integer.parseInt(inputs[2].trim());
					}
					catch (NumberFormatException numberFormatException)
					{
						new StockOrderException(
								"Invalid Input: Quantity value is not an Integer value",
								numberFormatException);
					}

					if (isValidInputs(side, company, quantity))
					{
						/*
						 * Create the Order Instance
						 */
						StockOrderManager.getInstance().createOrder(
								side.trim(), company.trim(), quantity);
					}
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
			new StockOrderException(
					"Exception while executing the Stock Order Execution",
					exception);
		}
	}

	public static boolean isValidInputs(String side, String company,
			int quantity) throws StockOrderException
	{
		if (Utility.isStringNullOrEmpty(side)
				|| Utility.isStringNullOrEmpty(company))
		{
			throw new StockOrderException(
					"Invalid Input: Input Value of Side or Company is Empty");
		}

		if (!("Sell".equalsIgnoreCase(side) || "Buy".equalsIgnoreCase(side)))
		{
			throw new StockOrderException(
					"Invalid Input: Input Value of Side should be 'Buy' or 'Sell'");
		}

		if (quantity <= 0)
		{
			throw new StockOrderException(
					"Invalid Input: Input Value of Quantity is Less than or equal to O");
		}

		return true;
	}

	public static boolean isValidInputLength(String[] inputs)
			throws StockOrderException
	{
		if (inputs.length != 3)
		{
			System.out.println("Invalid Input: Input Values are Invalid");
			System.out.println("Usage: Side,Company,Quantity");
			System.out.println("Example: Buy,ABC,10");
			throw new StockOrderException(
					"Invalid Input: Input Values are not equal to 3");
		}

		return true;
	}
}
