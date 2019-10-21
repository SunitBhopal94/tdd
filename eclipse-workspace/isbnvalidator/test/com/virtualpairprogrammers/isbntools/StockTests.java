package com.virtualpairprogrammers.isbntools;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.internal.verification.NoMoreInteractions;

import static org.mockito.Mockito.*;


public class StockTests {

	@Test
	public void testCanGetACorrectLocatorCode() {
		
		Externalisbn testWebService = new Externalisbn() {
			
			@Override
			public Book lookup(String isbn) {
				return new Book(isbn, "Of Mice And Men", "J. Steinbeck");
			}
		};
		
		Externalisbn testDatabaseService = new Externalisbn() {
			@Override
			public Book lookup(String isbn) {
				return null;
			}
		};
		

		StockManager stockManager = new StockManager();
		stockManager.setWebService(testWebService);
		stockManager.setDatabaseService(testDatabaseService);
		
		String isbn = "0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}
	
	@Test
	public void databaseIsUsedIfDataIsPresent() {
		Externalisbn databaseService = mock(Externalisbn.class);
		Externalisbn webService = mock(Externalisbn.class);
		
		when(databaseService.lookup("0140177396")).thenReturn(new Book("0140177396","abc","abc"));
		
		StockManager stockManager = new StockManager();
		stockManager.setWebService(webService);
		stockManager.setDatabaseService(databaseService);
		
		String isbn = "0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		
		verify(databaseService).lookup("0140177396");
		verify(webService, never()).lookup(anyString());
		
	}
	
	@Test
	public void webserviceIsUsedIfDataIsNotPresentInDatabase() {
		Externalisbn databaseService = mock(Externalisbn.class);
		Externalisbn webService = mock(Externalisbn.class);
		
		when(databaseService.lookup("0140177396")).thenReturn(null);
		when(webService.lookup("0140177396")).thenReturn(new Book("0140177396","abc","abc"));
		
		StockManager stockManager = new StockManager();
		stockManager.setWebService(webService);
		stockManager.setDatabaseService(databaseService);
		
		String isbn = "0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		
		verify(databaseService).lookup("0140177396");
		verify(webService).lookup("0140177396");
	}
	
}
