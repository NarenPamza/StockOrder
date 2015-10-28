package com.sahaj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sahaj.Order.Status;

public class StockOrderManager
{
	private static StockOrderManager stockOrderManager;

	private Map<String, List<Order>> orders = new HashMap<String, List<Order>>();

	private List<Order> orderOutputList = new ArrayList<Order>();

	private StockOrderManager()
	{
	}

	public static StockOrderManager getInstance()
	{
		if (stockOrderManager == null)
		{
			stockOrderManager = new StockOrderManager();
		}
		return stockOrderManager;
	}

	public void createOrder(String side, String companyName, int quantity)
	{
		try
		{
			Order order = null;
			if ("Buy".equalsIgnoreCase(side))
			{
				order = new Buy();
			}
			else if ("Sell".equalsIgnoreCase(side))
			{
				order = new Sell();
			}
			order.setCompanyName(companyName);
			order.setQuantity(quantity);

			// Create the Order
			addOrder(companyName, order);
		}
		catch (Exception exception)
		{
			new StockOrderException("Exception while creating the Stock Order",
					exception);
		}
	}

	private void addOrder(String companyName, Order order)
	{
		List<Order> orderList = orders.get(companyName);

		boolean isOpenSide = false;
		if (orderList == null)
		{
			orderList = new ArrayList<Order>();
			order.setRemainingQuantity(order.getQuantity());
		}
		else
		{
			for (int i = orderList.size() - 1; i <= 0; i--)
			{
				Order order2 = orderList.get(i);
				int remainingQuantity = 0;
				if (order2.getStatus() != Status.CLOSED)
				{
					isOpenSide = true;
					if ((order2 instanceof Buy && order instanceof Buy)
							|| (order2 instanceof Sell && order instanceof Sell))
					{
						remainingQuantity = order2.getRemainingQuantity()
								+ order.getQuantity();
						order.setRemainingQuantity(Math.abs(remainingQuantity));
						order2.setRemainingQuantity(0);
					}
					else
					{

						remainingQuantity = order2.getRemainingQuantity()
								- order.getQuantity();
						if (order2.getRemainingQuantity() > order.getQuantity())
						{
							order2.setRemainingQuantity(Math
									.abs(remainingQuantity));
							order.setRemainingQuantity(0);
						}
						else
						{
							order.setRemainingQuantity(Math
									.abs(remainingQuantity));
							order2.setRemainingQuantity(0);
						}
					}

					break;
				}
			}
		}

		/*
		 * If all the order for the company are closed and there is no open
		 * Order, then add the quantity of this order as Remaining Quantity.
		 */
		if (!isOpenSide)
		{
			order.setRemainingQuantity(order.getQuantity());
		}

		orderList.add(order);
		orders.put(companyName, orderList);

		// List to Hold the Output Values
		orderOutputList.add(order);
	}

	public List<Order> getOrder(String companyName)
	{
		return orders.get(companyName);
	}

	public void printOrderOutput()
	{
		for (Order order : orderOutputList)
		{
			System.out.println(order.toString());
		}
		System.out
				.println("====================--END OF OUTPUT--=========================");
	}
}
