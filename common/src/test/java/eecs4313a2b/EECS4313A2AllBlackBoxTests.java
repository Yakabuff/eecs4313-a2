package eecs4313a2b;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import net.sf.borg.common.DateUtil;
import net.sf.borg.common.*;
public class EECS4313A2AllBlackBoxTests implements SocketHandler
{
	
	/*********************************************Method1 Tests***********************************************************/
	//decision table
	/*
	 * conditions
	 * date 1 is after date 2
	 * date 1 is same as date 2
	 * date 1 is before date 2
	 */
	@Test
	public void testDateUtilIsAfter()
	{
		Date d1 = new Date(2020,1,1);
		Date d2 = new Date(2020,1,2);
		var result = DateUtil.isAfter(d1, d2);
		
		assertTrue("date 1 is after date 2", result);
		
		d1 = new Date(2020,1,1);
		d2 = new Date(2019,1,1);
		result = DateUtil.isAfter(d1,d2);
		assertFalse("date2 is after date1", result);
		
		d1 = new Date(2020,1,1);
		d2 = new Date(2020,1,1);
		
		result = DateUtil.isAfter(d2,d1);
		assertFalse("date1 is equal to date2", result);
				
	}
	/*********************************************Method2 Tests***********************************************************/
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
	@Override
	public String processMessage(String msg) {
		// TODO Auto-generated method stub
		return msg;
	}


	/*********************************************Method3 Tests***********************************************************/
	//equivalence class testing
	@Test
	public void testToMonthECT() {
		//partition into classes: hours and minutes	
		//weak normal - only accept valid inputs. 
		
		//Case 1: min%60 = 0 and min/60 >= 0 and min/60 <1
		assertEquals("0 Minutes",DateUtil.minuteString(0));
		//Case 2: min%60 = 0 and min/60 >= 1 and min/60 < 2
		assertEquals("1 Minute",DateUtil.minuteString(60));
		//Case 3: min%60 = 0 and min/60 >= 2 
		assertEquals("3 Hours",DateUtil.minuteString(180));
		//Case 4: min%60 >1 and min/60 >= 0 and min/60 <1
		assertEquals("5 Minutes",DateUtil.minuteString(5));
		//Case 5: min%60 >1 and min/60 >= 1 and min/60 < 2
		assertEquals("1 Hour and 5 Minutes",DateUtil.minuteString(65));
		//Case 6: min%60 >1 and min/60 >= 2 
		assertEquals("3 Hours and 5 Minutes",DateUtil.minuteString(185));
		//Case 7: min%60 = 1 and min/60 >= 0 and min/60 <1
		assertEquals("1 Minute",DateUtil.minuteString(1));
		//Case 8: min%60 = 1 and min/60 >= 1 and min/60 < 2
		assertEquals("1 Hour and 1 Minute",DateUtil.minuteString(61));
		//Case 9:  min%60 = 1 and min/60 >= 2 
		assertEquals("3 Hours and 1 Minute",DateUtil.minuteString(181));
		
		
		
	}
}
	