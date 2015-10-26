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

	public void createOrder(String companyName, Order order)
	{
		List<Order> orderList = orders.get(companyName);
		if (orderList == null)
		{
			orderList = new ArrayList<Order>();
			order.setStatus(Status.OPEN);
			order.setRemainingQuantity(order.getQuantity());
		} 
		else
		{
			for (int i = orderList.size() - 1; i < orderList.size(); i--)
			{
				Order order2 = orderList.get(i);
				if (order2.getStatus() != Status.CLOSED
						&& !order.getSide().equalsIgnoreCase(order2.getSide()))
				{
					int value = order2.getRemainingQuantity()
							- order.getQuantity();
					order.setRemainingQuantity(value);
					order.setStatus(Status.OPEN);
					break;
				}
			}
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

	public void printOutput()
	{
		for (Order order : orderOutputList)
		{
			System.out.println(order.toString());
		}
	}
}
