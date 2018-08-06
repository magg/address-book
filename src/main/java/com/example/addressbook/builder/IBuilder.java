package com.example.addressbook.builder;

import com.example.addressbook.exception.LoadException;

import java.util.List;

/**
 * Created by magg on 8/5/18.
 */
public interface IBuilder {

    public ThreadedBinarySearchTree setContactsFromText(String sourcePath) throws LoadException;


}
