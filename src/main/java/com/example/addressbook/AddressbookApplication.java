package com.example.addressbook;

import com.example.addressbook.domain.Gender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class AddressbookApplication {
	private static final Logger log = LogManager.getLogger(AddressbookApplication.class.getName());


	public static void main(String[] args) {

		AddressBook book = new AddressBook();
		book.setContactsFromText("AddressBook");
		book.getOldestContact();
		book.countGender(Gender.MALE);
		book.daysOlder(book.findContact("Bill McKnight"),book.findContact("Paul Robinson") );
	}
}
