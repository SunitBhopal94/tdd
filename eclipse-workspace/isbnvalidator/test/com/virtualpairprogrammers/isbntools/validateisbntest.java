package com.virtualpairprogrammers.isbntools;

import static org.junit.Assert.*;

import org.junit.Test;

public class validateisbntest {

	@Test
	public void checkvalidate10digitisbn() {
		validateisbn validator = new validateisbn();
		boolean result = validator.checkisbn("0412404400");
		assertTrue("first value",result);
		result = validator.checkisbn("1789135990");
		assertTrue("second value",result);
		
	
	}
	
	
	@Test
	public void checkvalidate13digitisbn() {
		validateisbn validator = new validateisbn();
		boolean result = validator.checkisbn("9780412404405");
		assertTrue("first value",result);
		result = validator.checkisbn("9781495296604");
		assertTrue("second value",result);
		
	
	}
	
	
	
	@Test
	public void checknotvalidate10digitisbn() {
		validateisbn validator = new validateisbn();
		boolean result = validator.checkisbn("0412404401");
		//fail();
		assertFalse(result);}
		
	@Test
	public void checknotvalidate13digitisbn() {
		validateisbn validator = new validateisbn();
		boolean result = validator.checkisbn("978041240440");
		assertFalse(result);
		}
		
	
	

	@Test(expected = NumberFormatException.class)
	public void ninedigitisbnarenotallowed()
	{
		validateisbn validator = new validateisbn();
		 validator.checkisbn("123456789");
	}

	@Test(expected = NumberFormatException.class)
	public void OnlyAlphabetsAllowed()
	{
		validateisbn validator = new validateisbn();
		validator.checkisbn("hello world");
	}
	
	}



