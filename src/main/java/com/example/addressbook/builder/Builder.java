package com.example.addressbook.builder;

import com.example.addressbook.domain.Contact;
import com.example.addressbook.domain.Gender;
import com.example.addressbook.exception.LoadException;
import com.example.addressbook.util.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

/**
 * Created by magg on 8/4/18.
 */
public class Builder implements IBuilder {

    private final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendPattern("d/MM/")
            .appendValueReduced(ChronoField.YEAR_OF_ERA, 2, 2, LocalDate.now().minusYears(100))
            .toFormatter();

    private static final Logger log = LogManager.getLogger(Builder.class.getName());

    private String getContentFromPath(String sourcePath) throws IOException {

        String res = "";

        res = FileUtils.readFileContentToString(sourcePath);

        return res;

    }

    public ThreadedBinarySearchTree getContactsTreeFromString(String content) throws LoadException{

        ThreadedBinarySearchTree tree = new ThreadedBinarySearchTree();

        if ( content == null  ){
            return null;
        }

        if ( content.isEmpty()  ){
            return tree;
        }

        String[] contacts = content.split("[\\r?\\n]");


        for (String s: contacts){
            String[] info = s.split("\\s*,\\s*");

            if (info.length != 3){
                continue;
            }


            try {
                LocalDate localDate = LocalDate.parse(info[2], formatter);

                String genderStr = info[1].toUpperCase();

                Gender gender = Gender.valueOf(genderStr);
                if (localDate != null){
                    Contact c = Contact.newBuilder().birthday(localDate).gender(gender).name(info[0]).build();
                    tree.insert(c);

                }
            } catch (IllegalArgumentException e) {
                throw new LoadException("Illegal gender", e);
            } catch (DateTimeParseException d){
                throw new LoadException("Bad date", d);
            }
        }


        return tree;
    }


    public ThreadedBinarySearchTree setContactsFromText(String sourcePath) throws LoadException {
        ThreadedBinarySearchTree tree;
        try {
            String content = getContentFromPath(sourcePath);
            tree = getContactsTreeFromString(content);
        } catch (IOException e) {
            throw new LoadException("IEException when reading  "
                    + sourcePath, e);
        }

        return tree;
    }

    public Builder() {
    }
}
