/**
 * 
 */
package com.sahaj;

/**
 * @author Admin
 *
 */
public class StockOrderException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message = null;

	public StockOrderException()
	{
		super();
	}

	public StockOrderException(String errorMessage)
	{
		super(errorMessage);
		message = errorMessage;
		System.out.println(message);
	}

	public StockOrderException(String errorMessage, Throwable throwable)
	{
		super(errorMessage, throwable);
		message = errorMessage;
		System.out.println(message);
	}

	@Override
	public String toString()
	{
		return message;
	}

	@Override
	public String getMessage()
	{
		return message;
	}
}
