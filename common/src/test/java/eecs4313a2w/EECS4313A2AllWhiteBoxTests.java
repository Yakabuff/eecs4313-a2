package eecs4313a2w;

import static org.junit.Assert.*;


import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.borg.common.DateUtil;
import net.sf.borg.common.*;

public class EECS4313A2AllWhiteBoxTests implements SocketHandler
{
	
	private static GregorianCalendar calendar1;
	private static Calendar calendar2;
	
	//Test class setup
	@BeforeClass
	public static void setup()
	{
		calendar1 = new GregorianCalendar();
		calendar2 = new GregorianCalendar();
	}
	
	/*********************************************Method1 Tests***********************************************************/
	
	
	//decision table
	/*
	 * conditions
	 * date 1 is after date 2
	 * date 1 is same as date 2
	 * date 1 is before date 2
	 */
	
	@Test
	public void isAfter_date1BeforeDate2_False()
	{
		calendar1.set(1, 0, 1);
		calendar2.set(1, 0, 2);
		
		boolean result = DateUtil.isAfter(calendar1.getTime(), calendar2.getTime());
		assertFalse("Should be false, date 1 is before date 2!", result);
		
	}
	
	@Test
	public void isAfter_date1SameAsDate2_False()
	{
		
		calendar1.set(1, 0, 1);
		calendar2.set(1, 0, 1);
		
		boolean result = DateUtil.isAfter(calendar1.getTime(), calendar2.getTime());
		assertFalse("Should be false, date 1 is same as date 2!", result);
	}
	
	@Test
	public void isAfter_date1AfterDate2_True()
	{
		calendar1.set(1, 0, 2);
		calendar2.set(1, 0, 1);
		
		boolean result = DateUtil.isAfter(calendar1.getTime(), calendar2.getTime());
		assertTrue("Should be true, date 1 is after as date 2!", result);
	}
	
	/********************Added for Whitebox***************************************/
	
	//null dates
	@Test
	public void isAfter_nullDate_noExceptionThrown()
	{
		try
		{
			DateUtil.isAfter(null, null);
		}
		catch(NullPointerException npe)
		{
			fail("Method should not throw a NullPointerException!");
		}
		
	}
	
	//earliest date
	@Test
	public void isAfter_earliestDateWithNormalDate_False()
	{
		calendar1.setTimeInMillis(Long.MIN_VALUE);//earliest possible date
		calendar2.set(1, 0, 1);
		
		boolean result = DateUtil.isAfter(calendar1.getTime(), calendar2.getTime());
		assertFalse("Should be false, date 1 is before date 2!", result);
	}
	@Test
	public void isAfter_earliestDateWithEarliestDate_True()
	{
		calendar1.setTimeInMillis(Long.MIN_VALUE);//earliest possible date
		calendar2.setTimeInMillis(Long.MIN_VALUE);
		
		boolean result = DateUtil.isAfter(calendar1.getTime(), calendar2.getTime());
		assertFalse("Should be false, date 1 is same as date 2!", result);
	}
	//Latest date
	@Test
	public void isAfter_latestDateWithNormalDate_True()
	{
		calendar1.setTimeInMillis(Long.MAX_VALUE);//Latest possible date
		calendar2.set(1, 0, 1);
		
		boolean result = DateUtil.isAfter(calendar1.getTime(), calendar2.getTime());
		assertTrue("Should be true, date 1 is after date 2!", result);
	}
	@Test
	public void isAfter_latestDateWithLatestDate_False()
	{
		calendar1.setTimeInMillis(Long.MAX_VALUE);//Latest possible date
		calendar2.setTimeInMillis(Long.MAX_VALUE);
		
		boolean result = DateUtil.isAfter(calendar1.getTime(), calendar2.getTime());
		assertFalse("Should be false, date 1 is same as date 2!", result);
	}
	
	
	
	/*********************************************Method2 Tests***********************************************************/
	
	@Override
	public String processMessage(String msg) {
		// TODO Auto-generated method stub
		return msg;
	}
	
	//boundary test
	@Test
	public void testSendMsg() {
		
		String validHost = "localhost";

		int port_norm = 2929; // x_norm
		int port_min = 0; // x_min
		int port_min_plus = 1; // x_min+
		int port_max = 65535; // x_max
		int port_max_minus = 65534; // x_max-
		
		
		SocketServer ss = new SocketServer(port_norm, this);
		
		try {
			String msg = "port norm";
			String response = SocketClient.sendMsg(validHost, port_norm, msg);
			assertEquals("norm test",msg, response);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try {
			String msg = "port min";
			String response = SocketClient.sendMsg(validHost, port_min, msg);
			assertEquals("min test",msg, response);
			
		}catch(Exception e){
			e.printStackTrace();
		}

		
		try {
			String msg = "port min+";
			String response = SocketClient.sendMsg(validHost, port_min_plus, msg);
			assertEquals("min+ test",msg, response);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try {
			String msg = "port max";
			String response = SocketClient.sendMsg(validHost, port_max, msg);
			assertEquals("max test",msg, response);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try {
			String msg = "port max minus";
			String response = SocketClient.sendMsg(validHost, port_max_minus, msg);
			assertEquals("Max- test", msg, response);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		

	}
	/********************Added for Whitebox***************************************/
	//none to add
	
	/*********************************************Method3 Tests***********************************************************/
	//equivalence class testing
	@Test
	public void testToMonthECT() {
		//partition into classes: hours and minutes	
		//weak normal - only accept valid inputs. 
		
		//Case 1: min%60 = 0 and min/60 >= 0 and min/60 <1
		assertEquals("0 Minutes",DateUtil.minuteString(0));
		//Case 2: min%60 = 0 and min/60 >= 1 and min/60 < 2
		assertEquals("1 Hour",DateUtil.minuteString(60));
		//Case 3: min%60 = 0 and min/60 >= 2 
		assertEquals("3 Hours",DateUtil.minuteString(180));
		//Case 4: min%60 >1 and min/60 >= 0 and min/60 <1
		assertEquals("5 Minutes",DateUtil.minuteString(5));
		//Case 5: min%60 >1 and min/60 >= 1 and min/60 < 2
		assertEquals("1 Hour 5 Minutes",DateUtil.minuteString(65));
		//Case 6: min%60 >1 and min/60 >= 2 
		assertEquals("3 Hours 5 Minutes",DateUtil.minuteString(185));
		//Case 7: min%60 = 1 and min/60 >= 0 and min/60 <1
		assertEquals("1 Minute",DateUtil.minuteString(1));
		//Case 8: min%60 = 1 and min/60 >= 1 and min/60 < 2
		assertEquals("1 Hour 1 Minute",DateUtil.minuteString(61));
		//Case 9:  min%60 = 1 and min/60 >= 2 
		assertEquals("3 Hours 1 Minute",DateUtil.minuteString(181));
		
		
		
	}
	
	
	
	/********************Added for Whitebox***************************************/
	//negative numbers
	
	@Test
	public void minuteString_NegativeValue_Exception()
	{
		try
		{
			final int value = -5;
			DateUtil.minuteString(value);
			fail("Expected exception when inputing negative numbers!");
		}
		catch(Exception e)
		{
			
		}
	}
	
	//over 24 hours
	
	@Test
	public void minuteString_Over24Hours_StringRepresentsOver24Hours()
	{
		final int value = 1441;
		final String expected = "24 Hours 1 Minute";
		assertEquals(expected, DateUtil.minuteString(value));
	}
	
	//int max value
	@Test
	public void minuteString_IntMaxValue_CorrectStringRepresentation()
	{
		final int value = Integer.MAX_VALUE;
		final String expected = "35791394 Hours 7 Minutes";
		assertEquals(expected, DateUtil.minuteString(value));
	}
	
	//int min value
	@Test
	public void minuteString_IntMinValue_Exception()
	{
		try
		{
			final int value = Integer.MIN_VALUE;
			DateUtil.minuteString(value);
			fail("Excpected exception when inputing negative numbers!");
		}
		catch(Exception e)
		{
			
		}
	}

	
	
}
	
