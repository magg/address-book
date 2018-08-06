package com.example.addressbook.service;

import com.example.addressbook.builder.TBSTNode;

/**
 * Created by magg on 8/5/18.
 */
public interface Visitor {

    void start(TBSTNode node); //visit the starting node of the tree
    //void finish(TBSTNode node); //visit the the last node of the tree


}