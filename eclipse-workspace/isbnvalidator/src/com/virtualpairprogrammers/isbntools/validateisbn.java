package com.virtualpairprogrammers.isbntools;

public class validateisbn {

	private static final int long_isbnmultiplier = 10;
	private static final int short_isbnmultipler = 11;
	private static final int short_isbntype = 10;
	private static final int long_isbntype = 13;

	public boolean checkisbn(String isbn) 
	{
       if (isbn.length() == long_isbntype) 
			{
			return thiisisforlongdigitvalidISBN(isbn);
		}
		else if (isbn.length()== short_isbntype)
		{
		return thisisshortdigitvalidISBN(isbn);
	}
	
    throw new NumberFormatException("ISBN numbers must be 10 or 13 digits long");
	}
	boolean thisisshortdigitvalidISBN(String isbn) {
		int sum = 0;
		
		for (int i = 0; i < short_isbntype ; i++ )
		{
			if (!Character.isDigit(isbn.charAt(i)))throw new NumberFormatException("ISBN numbers must be 10 digits long");
			sum += isbn.charAt(i) * (short_isbntype  -i);
		}
		
		
	return (sum % short_isbnmultipler == 0);
		
	}

	boolean thiisisforlongdigitvalidISBN(String isbn) {
		int sum = 0;
		
		for (int i = 0; i < 13; i++) {
			if (i % 2 == 0) {
				sum += Character.getNumericValue(isbn.charAt(i));
			}
			else {
				sum += Character.getNumericValue(isbn.charAt(i)) * 3;
			}
		}
		return (sum % long_isbnmultiplier == 0);
			
	}
}

	
		
	
