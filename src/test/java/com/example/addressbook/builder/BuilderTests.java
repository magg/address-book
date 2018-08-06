package com.example.addressbook.builder;

import com.example.addressbook.exception.LoadException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.*;

/**
 * Created by magg on 8/6/18.
 */

@RunWith(JUnit4.class)
public class BuilderTests {

    String s = String.join(System.getProperty("line.separator")
            ,"Bill McKnight, Male, 16/03/77"
            ,"Paul Robinson, NON, 15/01/85"
            ,"Gemma Lane, Female, 20/11/91"
    );

    String s1 = String.join(System.getProperty("line.separator")
            ,"Bill McKnight, Male, 16/03/2177"
            ,"Paul Robinson, Male, 15/01/85"
            ,"Gemma Lane, Female, 20/11/91"
    );

    String s2 = String.join(System.getProperty("line.separator")
            ,"Bill McKnight, Male, 16/03/77"
            ,"Paul Robinson, Male, 15/01/85"
            ,"Gemma Lane"
    );

    private Builder contactBuilder = new Builder();



    @Test(expected=LoadException.class)
    public void testBadGender(){
        ThreadedBinarySearchTree tree = contactBuilder.getContactsTreeFromString(s);
        assertEquals(null ,tree);
    }

    @Test
    public void testEmtpyString(){
        ThreadedBinarySearchTree tree = contactBuilder.getContactsTreeFromString("");
        assertNotNull(tree);
        ThreadedBinarySearchTree tree1 = contactBuilder.getContactsTreeFromString(null);
        assertNull(tree1);


    }

    @Test(expected=LoadException.class)
    public void testBadDate(){
        ThreadedBinarySearchTree tree = contactBuilder.getContactsTreeFromString(s1);
        assertEquals(null ,tree);
    }

    @Test
    public void testBadLineFormat(){
        ThreadedBinarySearchTree tree = contactBuilder.getContactsTreeFromString(s2);
        assertNotNull(tree);

    }


}
