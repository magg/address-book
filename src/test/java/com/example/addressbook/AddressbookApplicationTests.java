package com.example.addressbook;

import com.example.addressbook.domain.Contact;
import com.example.addressbook.domain.Gender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

@RunWith(JUnit4.class)
public class AddressbookApplicationTests {

	AddressBook book = new AddressBook();
	AddressBook bookEmpty = new AddressBook();
	AddressBook bookMore = new AddressBook();


	@Before
	public void setUp(){
		book.setContactsFromText("AddressBook");
		bookEmpty.setContactsFromText("AddressBookEmpty");
		bookMore.setContactsFromText("AddressBookMore");

	}


	@Test
	public void testGender() {
		assertEquals(3,book.countGender(Gender.MALE));
		assertEquals(2,book.countGender(Gender.FEMALE));

	}

	@Test
	public void testGenderMore() {
		assertEquals(4,bookMore.countGender(Gender.MALE));
		assertEquals(4,bookMore.countGender(Gender.FEMALE));

	}

	@Test
	public void testGenderEmpty() {
		assertEquals(0,bookEmpty.countGender(Gender.MALE));
		assertEquals(0,bookEmpty.countGender(Gender.FEMALE));

	}

	@Test
	public void testOldest() {
		assertEquals("Wes Jackson",book.getOldestContact().getName());
	}
	@Test
	public void testOldestMore() {
		assertEquals("Miros Gonzalez",bookMore.getOldestContact().getName());
	}


	@Test
	public void testOldestEmpty() {
		assertNull(bookEmpty.getOldestContact());
	}

	@Test
	public void testYougestEmpty() {
		assertNull(bookEmpty.getYougestContact());
	}

	@Test
	public void testYoungestMore() {
		assertEquals("Sunny Lane",bookMore.getYougestContact().getName());
	}

	@Test
	public void testYoungest() {
		assertEquals("Gemma Lane",book.getYougestContact().getName());
	}

	@Test
	public void testFindContact() {
		assertEquals("Sarah Stone",book.findContact("Sarah Stone").getName());
	}

	@Test
	public void testFindContactNull() {
		assertNull(book.findContact(null));
	}

	@Test
	public void testFindContactNOTinList() {
		assertNull(book.findContact("Miguel Gonzalez"));
	}

	@Test
	public void testDiffDays() {
		Contact c1 = book.findContact("Bill McKnight");
		Contact c2 = book.findContact("Paul Robinson");
		assertEquals(2862,book.daysOlder(c1,c2));
	}

	@Test
	public void testAllDataInList() {
		assertEquals(5,book.getContactList().size());
	}


}
