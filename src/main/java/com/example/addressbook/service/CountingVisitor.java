package com.example.addressbook.service;

import com.example.addressbook.builder.TBSTNode;
import com.example.addressbook.domain.Gender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by magg on 8/5/18.
 */
public class CountingVisitor implements Visitor {

    private static final Logger log = LogManager.getLogger(CountingVisitor.class.getName());


    int count;
    Gender gender;

    public CountingVisitor(Gender gender) {
        this.count = 0;
        this.gender = gender;
    }

    @Override
    public void start(TBSTNode node) {
        if (node!= null && gender.equals(node.getContact().getGender())){
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}
