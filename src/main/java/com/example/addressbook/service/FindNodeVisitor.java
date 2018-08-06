package com.example.addressbook.service;

import com.example.addressbook.builder.TBSTNode;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by magg on 8/5/18.
 */
public class FindNodeVisitor implements SearchVisitor {

    private static final Logger log = LogManager.getLogger(FindNodeVisitor.class.getName());

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FindNodeVisitor(String name) {
        this.name = name;
    }

    @Override
    public Signal accept(TBSTNode node) {
        if (name != null && node.getContact().getName()!= null && node.getContact().getName().equals(name)){
            return Signal.STOP;
        } else {
            return Signal.CONTINUE;
        }
    }
}
