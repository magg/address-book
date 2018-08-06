package com.example.addressbook.service;

import com.example.addressbook.builder.TBSTNode;

/**
 * Created by magg on 8/5/18.
 */
public interface SearchVisitor {

    Signal accept(TBSTNode node);
}
