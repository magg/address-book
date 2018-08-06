package com.example.addressbook.service;

import com.example.addressbook.builder.TBSTNode;
import com.example.addressbook.domain.Contact;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by magg on 8/6/18.
 */
public class ListVisitor implements Visitor {

    Set<Contact> contactList = new HashSet<Contact>();

    public Set<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(Set<Contact> contactList) {
        this.contactList = contactList;
    }

    public ListVisitor() {
    }

    @Override
    public void start(TBSTNode node) {
        this.contactList.add(node.getContact());
    }
}
