package com.example.addressbook;

import com.example.addressbook.builder.Builder;
import com.example.addressbook.builder.TBSTNode;
import com.example.addressbook.builder.ThreadedBinarySearchTree;
import com.example.addressbook.domain.Contact;
import com.example.addressbook.domain.Gender;
import com.example.addressbook.service.CountingVisitor;
import com.example.addressbook.service.FindNodeVisitor;
import com.example.addressbook.service.ListVisitor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Optional;
import java.util.Set;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by magg on 8/5/18.
 */
public class AddressBook {

    private static final Logger log = LogManager.getLogger(AddressBook.class.getName());

    /** The contactBuilder. */
    private Builder contactBuilder = new Builder();
    private ThreadedBinarySearchTree contactsTree = new ThreadedBinarySearchTree();

    public AddressBook(){
        super();
        log.info("Initialized address book builder");
    }


    public AddressBook setContactsFromText(String file){
        this.contactsTree = contactBuilder.setContactsFromText(file);
        return this;
    }

    public Builder getContactBuilder() {
        return contactBuilder;
    }

    public void setContactBuilder(Builder contactBuilder) {
        this.contactBuilder = contactBuilder;
    }

    public ThreadedBinarySearchTree getContactsTree() {
        return contactsTree;
    }


    public Contact getOldestContact(){
        Optional<TBSTNode> node = Optional.ofNullable(this.contactsTree.getOldestContact());
        node.ifPresent(tbstNode -> log.info("Oldest: " + tbstNode.getContact()));
        return node.<Contact>map(TBSTNode::getContact).orElse(null);
    }

    public Contact getYougestContact(){
        Optional<TBSTNode> node = Optional.ofNullable(this.contactsTree.getYoungestContact());
        node.ifPresent(tbstNode -> log.info("Youngest: " + tbstNode.getContact()));
        return node.<Contact>map(TBSTNode::getContact).orElse(null);

    }

    public int countGender( Gender gender){
        CountingVisitor cv = new CountingVisitor(gender);
        this.contactsTree.inOrderTraversal(cv);
        //log.info(gender.toString()+ " count: "+ cv.getCount());
        return cv.getCount();
    }

    public Set<Contact> getContactList(){
        ListVisitor lv = new ListVisitor();
        this.contactsTree.inOrderTraversal(lv);
        return lv.getContactList();
    }

    public Contact findContact (String name){
        FindNodeVisitor f1 = new FindNodeVisitor(name);
        Optional<TBSTNode> c1 =  Optional.ofNullable(this.contactsTree.inOrderTraversal(f1));
        return c1.<Contact>map(TBSTNode::getContact).orElse(null);
    }

    public long daysOlder(Contact c1, Contact c2){
        long daysBetween = DAYS.between(c1.getBirthday(), c2.getBirthday());
        log.info("Days older: " + daysBetween);
        return daysBetween;
    }

}
