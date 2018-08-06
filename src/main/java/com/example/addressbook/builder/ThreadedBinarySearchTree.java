package com.example.addressbook.builder;

import com.example.addressbook.domain.Contact;
import com.example.addressbook.service.SearchVisitor;
import com.example.addressbook.service.Signal;
import com.example.addressbook.service.Visitor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by magg on 8/4/18.
 */
public class ThreadedBinarySearchTree  {

    private TBSTNode root;

    private static final Logger log = LogManager.getLogger(ThreadedBinarySearchTree.class.getName());

    /** Constructor **/
    public ThreadedBinarySearchTree () {
        root = new TBSTNode(true, false);
    }

    /** Function to clear tree **/
    public void clear() {
        root = new TBSTNode(true, false);
    }

    /** Function to insert an element **/
    public void insert(Contact contact) {

        TBSTNode ptr = findNode(root, contact);

        /** element already present **/

        if (ptr == null)
            return;

        if (ptr.contact.getBirthday().isBefore(contact.getBirthday())) {

            TBSTNode nptr = new TBSTNode(contact, ptr, ptr.right, true, true);

            ptr.right = nptr;

            ptr.rightThread = false;

        } else {

            TBSTNode nptr = new TBSTNode(contact, ptr.left, ptr, true, true);

            ptr.left = nptr;

            ptr.leftThread = false;

        }

    }

    /** function to find node **/
    public TBSTNode findNode(TBSTNode r, Contact contact) {
        if (r.contact.getBirthday().isBefore(contact.getBirthday())) {
            if (r.rightThread)
                return r;
            return findNode(r.right, contact);
        } else if (r.contact.getBirthday().isAfter(contact.getBirthday()) ) {
            if (r.leftThread)
                return r;
            return findNode(r.left, contact);
        } else
            return null;
    }

    /** Function to search for an element **/
    public boolean search(Contact contact) {
        return findNode(root, contact) == null;

    }

    /** Function to get inorder successor **/
    public TBSTNode findNextInorder(TBSTNode tree) {

        TBSTNode temp;
        temp = tree.right;
        if (!tree.rightThread)
            while (!temp.leftThread)
                temp = temp.left;
        return temp;

    }

    public void inOrderReverse() {

        TBSTNode temp = root;
        while (true){
            temp = insucc2(temp);
            if (temp == root)
                break;
            log.info(temp.contact +" ");
        }
    }

    public TBSTNode insucc2(TBSTNode tree) {

        TBSTNode temp;

        temp = tree.left;

        if (!tree.leftThread)

            while (!temp.rightThread)

                temp = temp.right;

        return temp;

    }

    public TBSTNode getOldestContact() {

        TBSTNode temp = root;

        while (true) {
            temp = insuccFirst(temp);
            if (temp == root)
                break;
            return temp;
        }

        return null;
    }

    public TBSTNode insuccFirst(TBSTNode tree) {

        TBSTNode temp;

        temp = tree.left;

        if (!tree.rightThread)
            while (!temp.leftThread)
                temp = temp.left;
        return temp;

    }

    public TBSTNode getYoungestContact() {

        TBSTNode temp = root;

        while (true) {
            temp = insuccLast(temp);
            if (temp == root)
                break;
            log.info(temp.contact);
            return temp;
        }

        return null;
    }

    public TBSTNode insuccLast(TBSTNode tree) {

        TBSTNode temp;
        temp = tree.left;
        if (!tree.leftThread)
            while (!temp.rightThread)
                temp = temp.right;
        return temp;

    }

    /** Function to print tree **/
    public void inOrderTraversal(Visitor visitor) {
        TBSTNode temp = root;

        while (true){
            temp = findNextInorder(temp);
            if (temp == root)
                break;
            visitor.start(temp);
        }
    }

    public TBSTNode inOrderTraversal(SearchVisitor visitor) {
        TBSTNode temp = root;

        while (true){
            temp = findNextInorder(temp);
            if (temp == root)
                break;
            Signal s = visitor.accept(temp);
            if (Signal.STOP.equals(s)){
                return temp;
            }
        }
        return null;
    }

}
