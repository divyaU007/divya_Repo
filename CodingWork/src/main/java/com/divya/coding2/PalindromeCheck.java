package com.divya.coding2;
import java.util.*;
public class PalindromeCheck {

	   public static void main(String args[])
	   {
	      String firstString, reverseStr = "";
	      @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
	 
	      System.out.println("Enter a string you want to check if it is a palindrome");
	      firstString = scanner.nextLine();
	 
	      int len = firstString.length();
	 
	      for ( int i = len - 1; i >= 0; i-- )
	         reverseStr = reverseStr + firstString.charAt(i);
	 
	      if (firstString.equals(reverseStr))
	         System.out.println("the string you entered is a palindrome.");
	      else
	         System.out.println("the string you entered is not a palindrome.");
	 
	   }
	}