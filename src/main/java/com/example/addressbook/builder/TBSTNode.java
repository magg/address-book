package com.example.addressbook.builder;

/**
 * Created by magg on 8/4/18.
 */

import com.example.addressbook.domain.Contact;
import com.example.addressbook.domain.Gender;

import java.time.LocalDate;

/** Class TBSTNode **/

public class TBSTNode {

    Contact contact;
    TBSTNode left, right;
    boolean leftThread, rightThread;

    /** Constructor **/

    public TBSTNode(Contact contact) {
        this(contact, null, null, true, true);
    }

    /** Constructor **/

    public TBSTNode(boolean leftThread, boolean rightThread) {

        LocalDate localDate = LocalDate.MAX;
        Contact c = Contact.newBuilder().birthday(localDate).gender(Gender.MALE).name("Dummy Node").build();
        this.contact = c;
        this.left = this;
        this.right = this;
        this.leftThread = leftThread;
        this.rightThread = rightThread;
    }

    /** Constructor **/

    public TBSTNode(Contact contact, TBSTNode left, TBSTNode right, boolean leftThread, boolean rightThread) {
        this.contact = contact;
        this.left = left;
        this.right = right;
        this.leftThread = leftThread;
        this.rightThread = rightThread;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}