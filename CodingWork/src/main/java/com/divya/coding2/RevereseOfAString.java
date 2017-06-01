package com.divya.coding2;

/**
 * 
 * @author divyauppalapati
 *
 */
public class RevereseOfAString {

	public static void main(String[] args) {

		String string = "Java World";
		String revstring = "";

		for (int num = string.length() - 1; num >= 0; --num) {
			revstring += string.charAt(num);
		}

		System.out.println(revstring);
	}
}