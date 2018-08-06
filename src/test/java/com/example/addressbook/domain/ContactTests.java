package com.example.addressbook.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by magg on 8/5/18.
 */
@RunWith(JUnit4.class)
public class ContactTests {

    private final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendPattern("d/MM/")
            .appendValueReduced(ChronoField.YEAR_OF_ERA, 2, 2, LocalDate.now().minusYears(100))
            .toFormatter();


    private final DateTimeFormatter badFormatter = DateTimeFormatter.ofPattern("d/MM/yy");


    @Test
    public void createContactSuccess(){
        LocalDate localDate = LocalDate.parse("20/11/91", formatter);
        Contact c = Contact.newBuilder().birthday(localDate).gender(Gender.FEMALE).name("Gemma Lane").build();
        assertEquals(c.getName(), "Gemma Lane");
        assertEquals(c.getGender().toString(), "FEMALE");
        assertTrue(c.getBirthday().toString().contains("1991"));
    }

    @Test
    public void createContactBadDate(){
        LocalDate localDate = LocalDate.parse("20/11/91", badFormatter);
        Contact c = Contact.newBuilder().birthday(localDate).gender(Gender.FEMALE).name("Gemma Lane").build();
        assertEquals(c.getName(), "Gemma Lane");
        assertEquals(c.getGender().toString(), "FEMALE");
        assertFalse(c.getBirthday().toString().contains("1991"));
    }

    @Test(expected=IllegalArgumentException.class)
    public void createContactBadGender(){
        LocalDate localDate = LocalDate.parse("20/11/91", formatter);
        Contact c = Contact.newBuilder().birthday(localDate).gender(Gender.valueOf("female")).name("Gemma Lane").build();
        assertEquals(c.getName(), "Gemma Lane");
    }


}
